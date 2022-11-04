package clueGame;

import java.util.ArrayList;

public class Solution {
	private Card room;
	private Card person;
	private Card weapon;
	private ArrayList<Card> theCards = new ArrayList<Card>();
	
	public Solution(Card room, Card person, Card weapon) {
		this.room = room;
		this.person = person;
		this.weapon = weapon;
		theCards.add(room);
		theCards.add(person);
		theCards.add(weapon);
	}
	
	public ArrayList<Card> getSolution() {
		return theCards;
	}
}
