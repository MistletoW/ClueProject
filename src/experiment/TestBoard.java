package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	private int boardSize = 4;
	private TestBoardCell[][] board;
//	constructor
	public TestBoard() {
		board = new TestBoardCell[boardSize][boardSize];
		for(int i = 0; i< boardSize; i++) {
			for(int j = 0; j < boardSize; j++) {
				board[i][j] = new TestBoardCell(i, j);
			}
		}
		
	}
//	calculates the movement targets
	public void calcTargets( TestBoardCell startCell, int pathlength) {
		
	}
//	returns set of targets
	public Set<TestBoardCell> getTargets() {
		Set<TestBoardCell> tester = new HashSet<TestBoardCell>();
		tester.add(board[0][0]);
		return tester;
	}
//	returns cell from testBoard
	public TestBoardCell getCell(int row, int col) {
		return board[row][col];
	}
	
}
