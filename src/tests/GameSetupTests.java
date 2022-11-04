package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
		
		assertNotEquals(playerTestList.size(), 0);
		assertEquals(playerTestList.size(), 6);
		
		ArrayList<Card> deckTestList = board.getDeck();
		assertEquals(deckTestList.size(), 21);
		
	}
	
	@Test
	public void testDeck() {
		
	}
	
	@Test
	public void testPlayersContains() {
		ArrayList<Player> testList = board.getPlayers();
		HumanPlayer p = new HumanPlayer("Bri", "Red");
		assertTrue(testList.contains());
	}
	
	@Test
	public void testDealing() {
		
	}
}
