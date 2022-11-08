package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

class SuggestionTests {
	private static Board board;

	@BeforeAll
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();


	}
	@Test
	void suggestionTest1() {

		//get a few players
		ComputerPlayer cooper = (ComputerPlayer) board.getPlayers().get(1);
		cooper.setPosition(12, 3); 
		Solution suggestion1 = cooper.createSuggestion(board);

		ComputerPlayer liv = (ComputerPlayer) board.getPlayers().get(2);
		liv.setPosition(3,3);
		Solution suggestion2 = liv.createSuggestion(board);

		//make sure that the suggestions are not null
		assertNotEquals(suggestion1, null);
		assertNotEquals(suggestion2, null);

		//make sure the suggestion is not in seen cards
		assertFalse(cooper.getCards().contains(suggestion1.getPerson()));
		assertFalse(cooper.getSeenCards().contains(suggestion1.getWeapon()));

		assertFalse(liv.getCards().contains(suggestion2.getPerson()));
		assertFalse(liv.getSeenCards().contains(suggestion2.getWeapon()));

		//make sure the suggestion is not in their hand either
		assertFalse(cooper.getCards().contains(suggestion1.getPerson()));
		assertFalse(cooper.getCards().contains(suggestion1.getWeapon()));

		assertFalse(liv.getCards().contains(suggestion2.getPerson()));
		assertFalse(liv.getCards().contains(suggestion2.getWeapon()));


		//make sure that the room suggested is the room they're currently in
		assertEquals(suggestion1.getRoom().getName(), "Dunkin Donuts");

		assertEquals(suggestion2.getRoom().getName(), "Larry's Giant Subs");
	}
	@Test
	void suggestionTest2() {
		//test to ensure that if there's only one card hasn't been seen, it's the one that's suggested
		ComputerPlayer liv = (ComputerPlayer) board.getPlayers().get(2);
		//Liv can cheat as well. Let's let her see almost all the player cards. 
		ArrayList<Card> playerCards = new ArrayList<Card>();
		for (Card c : board.getPersonDeck()) {
			playerCards.add(c);
		}
		//first let's see which ones she already has so we don't give her two of the same card
		for (Card c : liv.getCards()) {
			if (playerCards.contains(c));
			playerCards.remove(c);
		}
		//and then let's remove one from the deck. (this one will be the one we hopefully suggest!)
		Card suggested = playerCards.get(0);
		playerCards.remove(0);
		//give Liv the remaining cards
		for(Card c : playerCards) {
			liv.updateSeen(c);
		}
		//check to make sure the suggestion matches the missing person!
		Solution suggestion = liv.createSuggestion(board);
		assertEquals(suggestion.getPerson(), suggested);
		
	}
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
		Player bri = (HumanPlayer) board.getPlayers().get(0);
		ComputerPlayer cooper = (ComputerPlayer) board.getPlayers().get(1);
		cooper.setPosition(12, 3); 
		Solution suggestion1 = cooper.createSuggestion(board);
		
		//(bri is allowed to cheat for testing purposes)
		//if bri doesn't have the card, we'll give one to her
		if((bri.getCards().contains(suggestion1.getPerson()) == false) && (bri.getCards().contains(suggestion1.getWeapon()) == false) && (bri.getCards().contains(suggestion1.getRoom()) == false)){
			bri.updateHand(suggestion1.getPerson());
			
			Card b = bri.disproveSuggestion(suggestion1);
			//bri's card is not null
			assertNotEquals(b, null);
			//check to see bri's card is in fact the one we gave her
			assertEquals(b, suggestion1.getPerson());
		}else {
			Card b = bri.disproveSuggestion(suggestion1);
			//bri's card is not null
			assertNotEquals(b, null);
		} 
	}
	@Test 
	void disproveTest3() {
		//test for random selection among two cards
		ComputerPlayer garrett = (ComputerPlayer) board.getPlayers().get(3);
		ComputerPlayer cooper = (ComputerPlayer) board.getPlayers().get(1);
		garrett.setPosition(21, 3);
		Solution suggestion1 = garrett.createSuggestion(board);
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
}
