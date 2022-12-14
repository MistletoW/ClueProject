package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Player {
	private String name;
	private String color;
	protected int row, column;
	protected ArrayList<Card> hand = new ArrayList<Card>();
	protected Set<Card> seenCards = new HashSet<Card>();
	public Player(String name, String color){
		this.name = name;
		this.color = color;
	}
	public abstract void updateHand(Card card);
	
	public abstract boolean isHuman();
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean equals(Player o) {
		if(this.name == o.getName() && this.color == o.getColor()) {
			return true;
		}
		return false;
	}
	
	public void addCard(Card c) {
		hand.add(c);
	}
	public ArrayList<Card> getCards(){
		return hand;
	}
	public Set<Card> getSeenCards() {
		return seenCards;
	}
	public void updateSeen(Card seenCard) {
		seenCards.add(seenCard);
	}
	
	public void setPosition(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public Dimension getPosition() {
		Dimension d = new Dimension(row, column);
		return(d);
	}
	
	public BoardCell getCell() {
		return ClueGame.board.getCell(row,column);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return column;
	}
	
	public Card disproveSuggestion(Solution suggestion) {
		ArrayList<Card> retCards = new ArrayList<Card>(); // list to choose from randomly if cards disprove suggestion
		
		//check person
		Card disprove = suggestion.getPerson();
		if(hand.contains(disprove)) {
			retCards.add(disprove);
		}
		
		//check weapon
		disprove = suggestion.getWeapon();
		if(hand.contains(disprove)) {
			retCards.add(disprove);
		}
		
		//check room
		disprove = suggestion.getRoom();
		if(hand.contains(disprove)) {
			retCards.add(disprove);
		}
		
		//choose random card to disprove
		if(retCards.size() == 1) {
			Card ret = retCards.get(0);
			return ret;
		}
		else if(retCards.size() != 0) {
			//get random card
			Random rand = new Random();
			int rand_int = rand.nextInt(retCards.size());
			Card ret = retCards.get(rand_int);
			//return card
			return ret;
		}
		
		//else if no cards match, return null
		return null;
	}
	//get player colors
	public Color getPlayerColor() {
		if(color.equals("Red")) return Color.RED;
		if(color.equals("Orange")) return Color.ORANGE;
		if(color.equals("Yellow")) return Color.YELLOW;
		if(color.equals("Green")) return Color.GREEN;
		if(color.equals("Blue")) return Color.BLUE;
		if(color.equals("Purple")) return Color.MAGENTA;
		else return Color.BLACK;
		
		
		
		
		
	}
	//draw the players
	public void draw(int width, int height, Graphics g) {
		g.setColor(getPlayerColor());
		g.fillOval(row*width, column*height, width, height);
	}
	
	public abstract Solution makeAccusation(Board board);
}
