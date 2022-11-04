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
		
		//test that players, weapons and rooms have been add to Deck
		Set<Card> deckTestList = board.getDeck();
		assertEquals(deckTestList.size(), 21);
		
	}
	
	@Test
	public void testDeck() {
		
	}
	
	@Test
	public void testPlayersContains() {
		ArrayList<Player> testList = board.getPlayers();
		
		int humans = 0;
		int comps = 0;
		for(int i = 0; i < testList.size(); i++) {
			if(testList.get(i).isHuman()) {
				assertEquals(testList.get(0).getName(), "Bri");
				humans++;
			} else {
				comps ++;
			}
		}
		
		assertEquals(comps, 5);
		assertEquals(humans, 1);
		
		
	}
	
	@Test
	public void testDealing() {
		//make sure the solution is dealt
		assertNotNull(board.getSolution());
	}
}
