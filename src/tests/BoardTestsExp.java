package tests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.*;
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
	
	@Test
	public void testAdjacency() {
		TestBoardCell cell = board.getCell(0,0);
	}
	@Test
	public void testTopLeft() {
		fail("Not Implemented Yet");
	}
	@Test
	public void testBottomRight() {
		fail("Not Implemented Yet");
	}
	@Test
	public void testRightEdge() {
		fail("Not Implemented Yet");
	}
	@Test
	public void testLeftEdge() {
		fail("Not Implemented Yet");
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
