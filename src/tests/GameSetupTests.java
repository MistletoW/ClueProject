package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Board;

public class GameSetupTests {
	
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load config files 
		board.initialize();
	}
	
	@Test
	public void testLoadSetup() {
		ArrayList<Player> playerTestList = board.getPlayers();
		//test that players have loaded in
		assertNotEquals(playerTestList.size(), 0);
		assertEquals(playerTestList.size(), 6);
		
		//test that players, weapons and rooms have been added to Deck
		ArrayList<Card> deckTestList = board.getDeck();
		/*
		 * note!! once the solution has been dealt, the cards are taken out of the deck! 
		 * this is why the deck size is now 0.
		 * was 18 after solution was dealt, was 21 when initialized, so we know it works 
		 * */ 
		assertEquals(deckTestList.size(), 0);
		
	}
	
	@Test
	public void testPlayersContains() {
		ArrayList<Player> testList = board.getPlayers();
		
		int humans = 0;
		int comps = 0;
		for(int i = 0; i < testList.size(); i++) {
			if(testList.get(i).isHuman()) {
//				test that human player is 'Bri' which was in our setup
				assertEquals(testList.get(0).getName(), "Bri");
				humans++;
			} else {
				comps ++;
			}
		}
//		test that the players contains 5 computer players
		assertEquals(comps, 5);
//		test that the players contains one human player
		assertEquals(humans, 1);
		
		
	}
	
	@Test
	public void testDealing() {
		//make sure the solution is dealt
		assertNotNull(board.getSolution());
		assertNotNull(board.getSolution().getPerson());
		
		//make sure solution cards are not in the deck after the solution has been dealt
		assertFalse(board.getDeck().contains(board.getSolution().getPerson()));
		assertFalse(board.getDeck().contains(board.getSolution().getWeapon()));
		assertFalse(board.getDeck().contains(board.getSolution().getRoom()));
		
		//make sure that cards are dealt to each player
		for(Player p : board.getPlayers()) {
			assertNotEquals(p.getCards().size(), 0);
		}
		
		int weapons = 0;
		int rooms = 0;
		int humans = 0;
		
		for(int i = 0; i < board.getPlayers().size(); i++) {
//			for each person
			ArrayList<Card> cards = board.getPlayers().get(i).getCards();
//			get their hand/cards
			for(int j = 0; j < cards.size(); j++) {
//				for each card, check its type 
				if(cards.get(j).getType() == CardType.ROOM) {
					rooms++;
				}
				if(cards.get(j).getType() == CardType.PERSON) {
					humans++;
				}
				if(cards.get(j).getType() == CardType.WEAPON) {
					weapons++;
				}
			}
		}
		
//		get solutions cards for their types
		if(board.getSolution().getPerson().getType() == CardType.PERSON) {
			humans++;
		}
		if(board.getSolution().getWeapon().getType() == CardType.WEAPON) {
			weapons++;
		}
		if(board.getSolution().getRoom().getType() == CardType.ROOM) {
			rooms++;
		}
		
//		assert that there are the appropriate amount of cards in play
		assertEquals(weapons, 6);
		assertEquals(rooms, 9);
		assertEquals(humans, 6);
		
	}
}
