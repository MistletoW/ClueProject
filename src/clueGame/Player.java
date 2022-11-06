package clueGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Player {
	private String name;
	private String color;
	protected int row, column;
	private ArrayList<Card> hand = new ArrayList<Card>();
	private Set<Card> seenCards = new HashSet<Card>();
	
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
	
	public void updateSeen(Card seenCard) {
		
	}
	
	public Card disproveSuggestion(Card suggestion) {
		Card ret = new Card("Vrei", CardType.PERSON);
		return ret;
	}
}
