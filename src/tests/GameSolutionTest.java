package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

class GameSolutionTest {
	private static Board board;
	@BeforeAll
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();


	}
	/*
	 * Accusation Tests
	 */
	@Test
	void accusationTests() {
		Solution answer = board.getSolution();
		Card p = answer.getPerson();
		Card w = answer.getWeapon();
		Card r = answer.getRoom();
		//if all three are correct, should return true
		assertTrue(board.checkAccusation(r, p, w));
		//if not, return false

		//playerCard is wrong
		ArrayList<Card> tempPlayerCards = new ArrayList<Card>();
		for (Card c : board.getPersonDeck()) {
			tempPlayerCards.add(c);
		}
		tempPlayerCards.remove(p);
		assertFalse(board.checkAccusation(r, tempPlayerCards.get(0), w));

		//roomCard is wrong
		ArrayList<Card> tempRoomCards = new ArrayList<Card>();
		for (Card c : board.getRoomDeck()) {
			tempRoomCards.add(c);
		}
		tempRoomCards.remove(r);
		assertFalse(board.checkAccusation(tempRoomCards.get(0), p, w));

		//weaponCard is wrong
		ArrayList<Card> tempWeaponCards = new ArrayList<Card>();
		for (Card c : board.getWeaponDeck()) {
			tempWeaponCards.add(c);
		}
		tempWeaponCards.remove(w);
		assertFalse(board.checkAccusation(r, p, tempWeaponCards.get(0)));

	}
	/*
	 * Handle Suggestion Tests
	 */

	//make sure a suggestion that nobody can disprove returns null
	@Test
	void handleSuggestionTest1() {
		//get a player
		Player cooper = (ComputerPlayer) board.getPlayers().get(1);
		//get the answer
		Solution answer = board.getSolution();
		//then have someone suggest the answer
		Card a = board.handleSuggestion(cooper, answer);
		assertEquals(a, null);
	}

	//make sure a suggestion only the suggesting player can disprove returns null
	@Test
	void handleSuggestionTest2() {
		//get a player
		Player cooper = (ComputerPlayer) board.getPlayers().get(1);

		//store temp card variables
		Card tempPerson = null;
		Card tempWeapon = null;
		Card tempRoom = null;
		//store card values from his hand
		for (Card c : cooper.getCards()) {
			if(c.getType() == CardType.PERSON) {
				tempPerson = c;
			} else if(c.getType() == CardType.WEAPON) {
				tempWeapon = c;
			} else {
				tempRoom = c;
			}
		}
		//if he doesn't have one of each type, make it the solution instead
		if (tempPerson == null) {
			tempPerson = board.getSolution().getPerson();
		}
		if (tempWeapon == null) {
			tempWeapon = board.getSolution().getWeapon();
		}
		if (tempRoom == null) {
			tempRoom = board.getSolution().getRoom();
		}
		
		//handle that suggestion
		Solution s = new Solution(tempRoom, tempPerson, tempWeapon);
		Card newSuggestion = board.handleSuggestion(cooper, s);
		assertEquals(newSuggestion, null);
	}
	//make sure that suggestion only human can disprove returns answer
	@Test
	void handleSuggestionTest3() {
		//store temp card variables
		Card tempPerson = null;
		Card tempWeapon = null;
		Card tempRoom = null;
		//get the human player, as well as a computerPlayer to make the suggestion
		Player bri = (HumanPlayer) board.getPlayers().get(0);
		Player cooper = (ComputerPlayer) board.getPlayers().get(1);
		//get Bri's cards
		for (Card c : bri.getCards()) {
			if(c.getType() == CardType.PERSON) {
				tempPerson = c;
			} else if(c.getType() == CardType.WEAPON) {
				tempWeapon = c;
			} else {
				tempRoom = c;
			}
		}
		//if she doesn't have one of each type, make it the solution instead
		if (tempPerson == null) {
			tempPerson = board.getSolution().getPerson();
		}
		if (tempWeapon == null) {
			tempWeapon = board.getSolution().getWeapon();
		}
		if (tempRoom == null) {
			tempRoom = board.getSolution().getRoom();
		}
		//handle that suggestion
		Solution s = new Solution(tempRoom, tempPerson, tempWeapon);
		Card newSuggestion = board.handleSuggestion(cooper, s);
		assertTrue(bri.getCards().contains(newSuggestion));

	}


	//Suggestion that two players can disprove, correct player (based on starting with next player in list) returns answer

	/*
	 * Disprove Suggestion Tests
	 */

	@Test
	void disproveTest() {
		//general disprove test, generates randomly each time
		//get a few players
		ComputerPlayer cooper = (ComputerPlayer) board.getPlayers().get(1);
		cooper.setPosition(12, 3); 
		Solution suggestion1 = cooper.createSuggestion(board);
		//ensure that a computerplayer can disprove a suggestion
		ComputerPlayer liv = (ComputerPlayer) board.getPlayers().get(2);
		liv.setPosition(3,3);
		Card l = liv.disproveSuggestion(suggestion1);
		//if Liv has the card in her hand, she can disprove, therefore not returning a null
		if(liv.getCards().contains(l)) {
			assertNotEquals(l, null);
			//if so, ensure that the card is actually in the suggestion
			ArrayList<Card> suggestionCards = new ArrayList<Card>();
			suggestionCards.add(suggestion1.getPerson());
			suggestionCards.add(suggestion1.getRoom());
			suggestionCards.add(suggestion1.getWeapon());
			assertTrue(suggestionCards.contains(l));
		}else {
			assertEquals(l, null);
		}
	}
	@Test
	void disproveTest2() {
		//testing to check if just the one card needed is returned
		Player tempBri = new HumanPlayer("Bri", "red");
		ComputerPlayer cooper = (ComputerPlayer) board.getPlayers().get(1);
		cooper.setPosition(12, 3); 
		Solution suggestion1 = cooper.createSuggestion(board);
		//(bri is allowed to cheat for testing purposes)
		//if bri doesn't have the card, we'll give one to her
		if((tempBri.getCards().contains(suggestion1.getPerson()) == false) && (tempBri.getCards().contains(suggestion1.getWeapon()) == false) && (tempBri.getCards().contains(suggestion1.getRoom()) == false)){
			tempBri.updateHand(suggestion1.getPerson());

			Card b = tempBri.disproveSuggestion(suggestion1);
			//bri's card is not null
			assertNotEquals(b, null);
			//check to see bri's card is in fact the one we gave her
			assertEquals(b, suggestion1.getPerson());
		}else {
			Card b = tempBri.disproveSuggestion(suggestion1);
			//bri's card is not null
			assertNotEquals(b, null);
		} 
	}
	//@Test 
	void disproveTest3() {
		//test for random selection among two cards
		ComputerPlayer tempGarrett = new ComputerPlayer("Garrett", "green");
		ComputerPlayer cooper = new ComputerPlayer("Cooper", "orange");
		cooper = (ComputerPlayer) board.getPlayers().get(1);
		tempGarrett.setPosition(21, 3);
		Solution suggestion1 = tempGarrett.createSuggestion(board);
		//cooper is also allowed to cheat for testing purposes
		//if he has none of the cards he needs to make a suggestion, we'll give him two
		if((cooper.getCards().contains(suggestion1.getPerson()) == false) && (cooper.getCards().contains(suggestion1.getWeapon()) == false) && (cooper.getCards().contains(suggestion1.getRoom()) == false)){
			cooper.updateHand(suggestion1.getPerson());
			cooper.updateHand(suggestion1.getWeapon());
			Card c = cooper.disproveSuggestion(suggestion1);
			//cooper's card is not null
			assertNotEquals(c, null);
			//check to see cooper's card is in fact the one we gave him
			if(c.equals(suggestion1.getPerson())) {
				assertEquals(c, suggestion1.getPerson());
			}else {
				assertEquals(c, suggestion1.getWeapon());
			}
		}else {
			Card c = cooper.disproveSuggestion(suggestion1);
			//cooper's card is not null
			assertNotEquals(c, null);
		} 
	}


}
