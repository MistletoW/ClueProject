package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoard {
	private TestBoardCell[][] grid;
	private Set<TestBoardCell> targets;
	private Set<TestBoardCell> visited;
	final static int COLS = 4;
	final static int ROWS = 4;
	
	
	//	constructor
	public TestBoard() {
		targets = new HashSet<TestBoardCell> ();
		grid = new TestBoardCell[ROWS][COLS];
		for(int i = 0; i< 4; i++) {
			for(int j = 0; j < 4; j++) {
				grid[i][j] = new TestBoardCell(i,j);
			}
		}

	}
	//	calculates the movement targets
	public void calcTargets( TestBoardCell startCell, int pathlength) {
				 
		//if done recursively
		 if (pathlength == 0) { 
			 return;
		 }
		 
		 if(startCell.getRow() > 0) {
			 calcTargets(this.getCell(startCell.getRow()-1, startCell.getCol()), pathlength -1);
		 }
		 
		 if(startCell.getRow() < ROWS-1) {
			 calcTargets(this.getCell(startCell.getRow()+1, startCell.getCol()), pathlength -1);
		 }
		 
		 if(startCell.getCol() > 0) {
			 calcTargets(this.getCell(startCell.getRow(), startCell.getCol()-1), pathlength -1);
		 }
		 
		 if(startCell.getCol() < COLS-1) {
			 calcTargets(this.getCell(startCell.getRow(), startCell.getCol()+1), pathlength -1); 
		 }
		 
		 targets.add(startCell);
		 
	}
	
	//	returns set of targets
	public Set<TestBoardCell> getTargets() {
		return targets;
	}
	//	returns cell from testBoard
	public TestBoardCell getCell(int row, int col) {
		return grid[row][col];
	}
	
	public int getTestBoardSize() {
		return COLS * ROWS;
	}

}
