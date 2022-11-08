package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

class ComputerAITest {
	private static Board board;

	@BeforeAll
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();


	}
	/*
	 * Suggestion Tests
	 */
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
	/*
	 * Targeting Tests 
	 */
	@Test
	void testComputerTargeting() {
		
		//create a computerPlayer AI and give a position
		ComputerPlayer cooper = (ComputerPlayer) board.getPlayers().get(1);
		cooper.setPosition(17,7);
		//calcTargets that does not have room and give list to targets
		board.calcTargets(board.getCell(17, 7), 1);
		Set<BoardCell> targets = board.getTargets();
		//get random target for cooper AI
		BoardCell target = cooper.selectTarget(targets);
		//create a list of all possible random targets AI could pick
		ArrayList<BoardCell> testList = new ArrayList<BoardCell>();
		testList.add(board.getCell(16, 7));
		testList.add(board.getCell(18, 7));
		testList.add(board.getCell(17, 6));
		testList.add(board.getCell(17, 8));
		//assert that the randomly chosen target cell is in the list of possible targets
		assertTrue(testList.contains(target));
		
		//clear testList
		testList.clear();
		//reset cooper to position where two rooms are available
		cooper.setPosition(4, 25);
		board.calcTargets(board.getCell(4,25), 2);
		targets = board.getTargets();
		target = cooper.selectTarget(targets);
		//add rooms to testList
		testList.add(board.getCell(3,21));
		testList.add(board.getCell(12,28));
		//assert comp AI choose one of the rooms
		assertTrue(testList.contains(target));
		
		for(int i = 0; i < board.getRoomDeck().size(); i++) {
			cooper.updateSeen(board.getRoomDeck().get(i));
		}
		
		testList.add(board.getCell(2,25));
		testList.add(board.getCell(6,25));
		board.calcTargets(board.getCell(4,25), 3);
		targets = board.getTargets();
		target = cooper.selectTarget(targets);
		
		assertTrue(testList.contains(target));
	}
}
