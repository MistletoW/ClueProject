package tests;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import experiment.TestBoard;
import experiment.TestBoardCell;

public class BoardTestsExp {
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
	
	public boolean testTOPLEFT() {
		return false;
	}
	
	public boolean testBOTTOMRIGHT() {
		return false;
	}
	
	public boolean testRIGHTEDGE() {
		return false;
	}
	
	public boolean testLEFTEDGE() {
		return false;
	}
	
	
//	Testing target creation on a 4x4 board
	public boolean testEmpty() {
		return false;
	}
	
	public boolean testOccupied() {
		return false;
	}
	public boolean testOccupied2() {
		return false;
	}
	
	public boolean testRoom() {
		return false;
	}
	public boolean testNotDiagonal() {
		return false;
	}
}
