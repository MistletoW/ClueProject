package tests;

import org.junit.Assert;
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
