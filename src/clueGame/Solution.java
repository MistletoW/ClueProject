package clueGame;

import java.util.ArrayList;

public class Solution {
	private Card room;
	private Card person;
	private Card weapon;
	
	public Solution(Card room, Card person, Card weapon) {
		this.room = room;
		this.person = person;
		this.weapon = weapon;

	}
	
	public Card getPerson() {
		return person;
	}
	public Card getWeapon() {
		return weapon;
	}
	public Card getRoom() {
		return room;
	}
}
