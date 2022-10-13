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
		board.addToVisited(cell);
		Set<TestBoardCell> list = cell.getAdjList();
		Assert.assertTrue(list.contains(board.getCell(1, 0)));
		Assert.assertTrue(list.contains(board.getCell(0, 1)));
	}
//	test adjacencies of (3, 3)
	@Test
	public void testBottomRight() {
		TestBoardCell cell = board.getCell(3, 3);
		board.addToVisited(cell);
		Set<TestBoardCell> list = cell.getAdjList();
		Assert.assertTrue(list.contains(board.getCell(3, 2)));
		Assert.assertTrue(list.contains(board.getCell(2, 3)));
	}
//	test adjacencies of (3, 1) 
	@Test
	public void testRightEdge() {
		TestBoardCell cell = board.getCell(1,3);
		board.addToVisited(cell);
		Set<TestBoardCell> list = cell.getAdjList();
		Assert.assertTrue(list.contains(board.getCell(2, 3)));
		Assert.assertTrue(list.contains(board.getCell(0, 3)));
		Assert.assertTrue(list.contains(board.getCell(1, 2)));
	}
//	test adjacencies of (0, 1)
	@Test
	public void testLeftEdge() {
		TestBoardCell cell = board.getCell(0, 1);
		board.addToVisited(cell);
		Set<TestBoardCell> list = cell.getAdjList();
		Assert.assertTrue(list.contains(board.getCell(0, 0)));
		Assert.assertTrue(list.contains(board.getCell(0, 2)));
		Assert.assertTrue(list.contains(board.getCell(1, 1)));
	}
//	test adjacencies of (2, 2)
	@Test
	public void testCenter() {
		TestBoardCell cell = board.getCell(2, 2);
		board.addToVisited(cell);
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
//		tests if targets is empty if given a roll of zero
		TestBoardCell cell = board.getCell(0, 0);
		board.addToVisited(cell);
		board.calcTargets(cell, 0);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(0, targets.size()); //targets.size() will be zero so given 10 to fail
	}
	
	@Test
	public void testOccupied() {
		//tests if cell sets to occupied
		TestBoardCell cell = board.getCell(0, 0);
		board.addToVisited(cell);
		cell.setOccupied(true);
		board.calcTargets(board.getCell(1,1), 6);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertFalse(targets.contains(cell));
	}
	
	
	@Test
	public void testRoom() {
		//tests if cell sets to room
		TestBoardCell cell = board.getCell(1, 1);
		board.addToVisited(cell);
		cell.setRoom(true);
		Assert.assertTrue(cell.getRoom());
		cell = board.getCell(2,2);
		board.calcTargets(cell, 2);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertFalse(targets.contains(board.getCell(1,1)));
		
	}
	
	@Test
	public void testAllMovementUsed() {
		//tests if targets are player roll distance from origin
		//first test if any targets are given if roll is greater than zero
		TestBoardCell cell = board.getCell(0, 0);
		board.addToVisited(cell);
		board.calcTargets(cell, 2);
		Set<TestBoardCell> targets = board.getTargets();
		
		Assert.assertNotEquals(0, targets.size());
		
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(0, 2)));
		Assert.assertTrue(targets.contains(board.getCell(2, 0)));
		Assert.assertTrue(targets.contains(board.getCell(1, 1)));
		
		Assert.assertFalse(targets.contains(board.getCell(0, 0)));
		Assert.assertFalse(targets.contains(board.getCell(3, 3)));
	}
	
	@Test
	public void testOccupiedNotTarget() {
		//First tests if board gets a cell
		TestBoardCell cell = board.getCell(0, 1);
		board.addToVisited(cell);
		board.calcTargets(cell, 1);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCell(0, 0)));
		//then sets cell to occupied and retests if cell gets occupied
		board.getCell(0,0).setOccupied(true);
		board.calcTargets(cell, 1);
		targets = board.getTargets();
		Assert.assertTrue(targets.contains(board.getCell(0, 0)));
		
	}
	
	@Test
	public void testTargetsDifferentOrigin() {
//		Tests if targets are different if origins are different
		TestBoardCell cell = board.getCell(0, 1); 
		board.addToVisited(cell);
		board.calcTargets(cell, 1);
		Set<TestBoardCell> targets = board.getTargets();
		board.clearTargets();
		TestBoardCell cell2 = board.getCell(3,3); 
		board.calcTargets(cell2, 1);
		Set<TestBoardCell> targets2 = board.getTargets();
		Assert.assertFalse(targets2.contains(targets));
	}
	
	@Test
	public void testMaxRoll() {
		//tests if targets contains more elements than min roll
		TestBoardCell cell = board.getCell(0, 1);
		board.addToVisited(cell);
		board.calcTargets(cell, 6);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertTrue(6 < targets.size());
		
		
	}
}