package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	private TestBoardCell[][] board;
	private Set<TestBoardCell> targets;
	//	constructor
	public TestBoard() {
		targets = new HashSet<TestBoardCell> ();
		board = new TestBoardCell[4][4];
		for(int i = 0; i< 4; i++) {
			for(int j = 0; j < 4; j++) {
				board[i][j] = new TestBoardCell(i,j);
			}
		}

	}
	//	calculates the movement targets
	public void calcTargets( TestBoardCell startCell, int pathlength) {
		 
	}
	//	returns set of targets
	public Set<TestBoardCell> getTargets() {
		return targets;
	}
	//	returns cell from testBoard
	public TestBoardCell getCell(int row, int col) {
		return board[row][col];
	}
	
	public int getTestBoardSize() {
		return 4;
	}

}
