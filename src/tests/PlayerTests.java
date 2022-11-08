package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ComputerPlayer;
import clueGame.Player;

class PlayerTests {
	private static Board board;

	@BeforeAll
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
	}

	@Test
	void testNumPlayers() {
		assertEquals(6, board.getPlayers().size());
	}
	@Test
	void testCards() {
		assertEquals(0, board.getDeck().size());
	}
	
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
		board.calcTargets(board.getCell(12,25), 3);
		targets = board.getTargets();
		target = cooper.selectTarget(targets);
		
		assertTrue(testList.contains(target));

	}
}
