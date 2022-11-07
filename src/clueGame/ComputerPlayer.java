package clueGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.function.BooleanSupplier;

public class ComputerPlayer extends Player{


	public ComputerPlayer(String name, String color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateHand(Card card) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isHuman() {
		return false;
	}
	
	public Solution createSuggestion(Board board) {
		char initial = board.getCell(row, column).getInitial();
		//get decks from board
		ArrayList<Card> weaponDeck = board.getWeaponDeck();
		ArrayList<Card> roomDeck = board.getRoomDeck();
		ArrayList<Card> personDeck = board.getPersonDeck();
		//get the room that the comp is currently in
		Card room = null;
		for(int i = 0; i < roomDeck.size(); i++) {
			if(roomDeck.get(i).getName().charAt(0) == initial) {
				room = roomDeck.get(i);
			}
		}
		
		//get a random weapon that we have no seen
		Random rand = new Random();
		int rand_int = rand.nextInt(weaponDeck.size());
		Card weapon = null;
		do {
			if(seenCards.contains(weaponDeck.get(rand_int))) {//if seen get new random
				rand_int = rand.nextInt(weaponDeck.size());
			} else if(hand.contains(weaponDeck.get(rand_int))) { //if in hand get new random
				rand_int = rand.nextInt(weaponDeck.size());
			} else {//else add random weapon to solution
				weapon = weaponDeck.get(rand_int);
			}
		}while(seenCards.contains(weaponDeck.get(rand_int)));
		
		//get a random person we have not seen
		Card person = null;
		do {
			if(seenCards.contains(personDeck.get(rand_int))) { //if seen get new random
				rand_int = rand.nextInt(personDeck.size());
			} else if(hand.contains(personDeck.get(rand_int))) { //if in hand get new random
				rand_int = rand.nextInt(personDeck.size());
			} else {	//else add random person to solution
				person = personDeck.get(rand_int);
			}
		}while(seenCards.contains(personDeck.get(rand_int)));
		
		Solution solution = new Solution(room, person, weapon); //create solution with room and random person and weapon
		
		return solution;
	}
	
	public BoardCell selectTarget(Set<BoardCell> targetList) {
		Iterator<BoardCell> it = targetList.iterator(); //get iterator for targets
		ArrayList<BoardCell> potTargetsWith = new ArrayList<BoardCell>();
		ArrayList<BoardCell> potTargetsWithout = new ArrayList<BoardCell>();
		boolean hasRoom = false; //assume there is no room in target list
		
		while(it.hasNext()) {	//iterate through targetList
			BoardCell tester = it.next();
			it.remove();
			if(tester.isRoomCenter()) {	//if we discover a room in target list add room to potential targets
				hasRoom = true;
				potTargetsWith.add(tester);
			}
			potTargetsWithout.add(tester);
		}
		
		BoardCell target;
		Random rand = new Random();
		int rand_int;
		
		if(hasRoom) {	//if there is a room the prioritize targets that are rooms, select at random
			rand_int = rand.nextInt(potTargetsWith.size());
			target = potTargetsWith.get(rand_int);
		} else {	//if no room, select random target
			rand_int = rand.nextInt(potTargetsWithout.size());
			target = potTargetsWithout.get(rand_int);
		}
		
		return target;
	}
	public Set<Card> getSeenCards(){
		return super.getSeenCards();
	}
}
