package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTest {
	
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
	
	// Test a variety of walkway scenarios
	// These tests are Dark Orange on the planning spreadsheet
	@Test
	public void testAdjWalkways() {
		//
		Set<BoardCell> testList = board.getAdjList(8,7);
		assertEquals(4, testList.size());

		assertTrue(testList.contains(board.getCell(8,6)));
		assertTrue(testList.contains(board.getCell(8,8)));
		assertTrue(testList.contains(board.getCell(7,7)));
		assertTrue(testList.contains(board.getCell(9,7)));
	}
	
	@Test
	public void testAdjNotCenterRooms() {
		Set<BoardCell> testList = board.getAdjList(13,1);
		
		assertEquals(4, testList.size()); //size changed for now to make tests fail
		
		assertTrue(testList.contains(board.getCell(13,2)));
		assertTrue(testList.contains(board.getCell(13,0)));
		assertTrue(testList.contains(board.getCell(12,1)));
		assertTrue(testList.contains(board.getCell(14,1)));
	}
	
	// Test a variety of walkway scenarios
	// These tests are Dark Orange on the planning spreadsheet
	@Test
	public void testAdjEdge() {
		// test adjList at edge
		Set<BoardCell> testList = board.getAdjList(0,8);
		assertEquals(1, testList.size());

		assertFalse(testList.contains(board.getCell(0,9)));

	}
	
	// Test a variety of walkway scenarios
	// These tests are Dark Orange on the planning spreadsheet
	@Test
	public void testAdjRoomWall() {
		// test adjList at wall
		Set<BoardCell> testList = board.getAdjList(8,21);
		assertEquals(4, testList.size());
		
		assertFalse(testList.contains(board.getCell(9,21)));
		
		assertTrue(testList.contains(board.getCell(7, 21)));
		assertTrue(testList.contains(board.getCell(8, 20)));
		assertTrue(testList.contains(board.getCell(8, 22)));
	}
	
	// Ensure door locations include their rooms and also additional walkways
	// These cells are LIGHT ORANGE on the planning spreadsheet
	@Test
	public void testAdjRoomDoors() {
		// test adjList at door
		Set<BoardCell> testList = board.getAdjList(11,17);
		assertEquals(4, testList.size());
		
		assertTrue(testList.contains(board.getCell(12,21)));
		assertTrue(testList.contains(board.getCell(12,17)));
		assertTrue(testList.contains(board.getCell(10, 17)));
		assertTrue(testList.contains(board.getCell(11,16)));
	}
	
//	Test Room with secret Passage adjList to make sure it include other room
	@Test
	public void testAdjSecretPass() {
		Set<BoardCell> testList = board.getAdjList(3,3);
		assertEquals(4, testList.size());
		
	}
	
	//Test targets in walkway
	@Test
	public void testTargetsWalkway() {
		//test roll 2
		board.calcTargets(board.getCell(17,7), 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCell(17,5)));
		assertTrue(targets.contains(board.getCell(17,9)));
		assertTrue(targets.contains(board.getCell(15,7)));
		assertTrue(targets.contains(board.getCell(19,7)));
	}
	
//	Test targets can enter a room
	@Test
	public void testTargetsEnterRoom() {
		//test roll 2
		board.calcTargets(board.getCell(17,10), 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCell(21,12)));
		assertTrue(targets.contains(board.getCell(17,12)));
		assertTrue(targets.contains(board.getCell(16,11)));
		assertTrue(targets.contains(board.getCell(16,9)));
	}
	
//	Test targets can leave a room without a secret passage
	@Test
	public void testTargetsLeaveRoom() {
		board.calcTargets(board.getCell(17,10), 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCell(21,12)));
		assertTrue(targets.contains(board.getCell(17,12)));
		assertTrue(targets.contains(board.getCell(16,11)));
		assertTrue(targets.contains(board.getCell(16,9)));
	}
	
//	Test targets can leave a room with a secret passage
	@Test
	public void testTargetsLeaveRoomSecret() {
		//test roll 2
		board.calcTargets(board.getCell(3,3), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		
	}
	
//	Test targets avoid occupied spaces
	@Test
	public void testTargetsOccupied() {
		//test roll 2
		board.getCell(17,16).setOccupied(true);
		board.calcTargets(board.getCell(15,16), 2);
		Set<BoardCell> targets= board.getTargets();
		
		assertFalse(targets.contains(board.getCell(17,16)));
		assertTrue(targets.contains(board.getCell(13,16)));
		assertTrue(targets.contains(board.getCell(14,17)));
		assertTrue(targets.contains(board.getCell(16,17)));
		assertTrue(targets.contains(board.getCell(16,15)));
		
		//test roll 2
		board.getCell(17,3).setOccupied(true);
		board.calcTargets(board.getCell(17,7), 4);
		targets= board.getTargets();

		assertFalse(targets.contains(board.getCell(17,3)));
		assertTrue(targets.contains(board.getCell(14,8)));
		assertTrue(targets.contains(board.getCell(17,11)));
		assertTrue(targets.contains(board.getCell(21,7)));
		assertTrue(targets.contains(board.getCell(16,4)));
	}
}
