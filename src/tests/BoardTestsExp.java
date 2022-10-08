package tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
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
//	test adjacencies of (0, 0)
	@Test
	public void testTopLeft() {
		
		TestBoardCell cell = board.getCell(0, 0);
		Set<TestBoardCell> list = cell.getAdjList();
		Assert.assertTrue(list.contains(board.getCell(1, 0)));
		Assert.assertTrue(list.contains(board.getCell(0, 1)));
	}
//	test adjacencies of (3, 3)
	@Test
	public void testBottomRight() {
		TestBoardCell cell = board.getCell(3, 3);
		Set<TestBoardCell> list = cell.getAdjList();
		Assert.assertTrue(list.contains(board.getCell(3, 2)));
		Assert.assertTrue(list.contains(board.getCell(2, 3)));
	}
//	test adjacencies of (3, 1) 
	@Test
	public void testRightEdge() {
		TestBoardCell cell = board.getCell(3, 1);
		Set<TestBoardCell> list = cell.getAdjList();
		Assert.assertTrue(list.contains(board.getCell(3, 0)));
		Assert.assertTrue(list.contains(board.getCell(3, 2)));
		Assert.assertTrue(list.contains(board.getCell(2, 1)));
	}
//	test adjacencies of (0, 1)
	@Test
	public void testLeftEdge() {
		TestBoardCell cell = board.getCell(0, 1);
		Set<TestBoardCell> list = cell.getAdjList();
		Assert.assertTrue(list.contains(board.getCell(0, 0)));
		Assert.assertTrue(list.contains(board.getCell(0, 2)));
		Assert.assertTrue(list.contains(board.getCell(1, 1)));
	}
//	test adjacencies of (2, 2)
	@Test
	public void testCenter() {
		TestBoardCell cell = board.getCell(2, 2);
		Set<TestBoardCell> list = cell.getAdjList();
		Assert.assertTrue(list.contains(board.getCell(2, 1)));
		Assert.assertTrue(list.contains(board.getCell(2, 3)));
		Assert.assertTrue(list.contains(board.getCell(1, 2)));
		Assert.assertTrue(list.contains(board.getCell(3, 2)));
	}
//	create a set 
	
	
//	Testing target creation on a 4x4 board
	@Test
	public void testTargetsEmpty() {
		TestBoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 0);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size());
	}
	@Test
	public void testOccupied() {
		TestBoardCell cell = board.getCell(0, 0);
		cell.setOccupied(false);
		Assert.assertTrue(cell.getOccupied());
	}
	@Test
	public void testOccupied2() {
		TestBoardCell cell = board.getCell(1, 1);
		cell.setOccupied(false);
		Assert.assertTrue(cell.getOccupied());
	}
	@Test
	public void testRoom() {
		TestBoardCell cell = board.getCell(1, 1);
		cell.setRoom(false);
		Assert.assertTrue(cell.getRoom());
	}
	@Test
	public void testAllMovementUsed() {
		TestBoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 0);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size());
	}
}
