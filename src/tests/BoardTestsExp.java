package tests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import experiment.TestBoard;
import experiment.TestBoardCell;

public class BoardTestsExp  {
	TestBoard board;
	
	@BeforeEach
	public void setUp() {
//		Initialize testboard
//		testboard 
		board = new TestBoard();
	}
	
//	Testing adjacency
//	create a set of tiles adjacent to board[0][0], compare if they're equal
	@Test
	public void testTopLeft() {
		TestBoardCell cell = board.getCell(0,0);
		Set<TestBoardCell> set = new HashSet<TestBoardCell>();
		set.add(board.getCell(0,1));
		set.add(board.getCell(1,0));
		assertEquals(cell.getAdjList(), set);
	}
//	create a set of tiles adjacent to board[3][3], compare if they're equal
	@Test
	public void testBottomRight() {
		TestBoardCell cell = board.getCell(3, 3);
		Set<TestBoardCell> set = new HashSet<TestBoardCell>();
		set.add(board.getCell(2,3));
		set.add(board.getCell(3,2));
		assertEquals(cell.getAdjList(), set);
	}
//	create a set of tiles adjacent to board[1][3], compare if they're equal
	@Test
	public void testRightEdge() {
		TestBoardCell cell = board.getCell(1, 3);
		Set<TestBoardCell> set = new HashSet<TestBoardCell>();
		set.add(board.getCell(2, 3));
		set.add(board.getCell(0, 3));
		set.add(board.getCell(1, 2));
		assertEquals(cell.getAdjList(), set);
	}
//	create a set of tiles adjacent to board[1][0], compare if they're equal
	@Test
	public void testLeftEdge() {
		TestBoardCell cell = board.getCell(1, 0);
		Set<TestBoardCell> set = new HashSet<TestBoardCell>();
		set.add(board.getCell(0, 0));
		set.add(board.getCell(1, 1));
		set.add(board.getCell(2, 0));
		assertEquals(cell.getAdjList(), set);
	}
	
	
//	Testing target creation on a 4x4 board
	@Test
	public void testEmpty() {
		fail("Not Implemented Yet");
	}
	@Test
	public void testOccupied() {
		fail("Not Implemented Yet");
	}
	@Test
	public void testOccupied2() {
		fail("Not Implemented Yet");
	}
	@Test
	public void testRoom() {
		fail("Not Implemented Yet");
	}
	@Test
	public void testNotDiagonal() {
		fail("Not Implemented Yet");
	}
}
