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
		visited = new HashSet<TestBoardCell> ();
		grid = new TestBoardCell[ROWS][COLS];
		for(int i = 0; i< ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				grid[i][j] = new TestBoardCell(i,j);
			}
		}
		
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				 if(i > 0) {
					 grid[i][j].addAdjacency(grid[i-1][j]);
				 }
				 
				 if(i < ROWS -1) {
					 grid[i][j].addAdjacency(grid[i+1][j]);
				 }
				 
				 if(j > 0) {
					 grid[i][j].addAdjacency(grid[i][j-1]);
				 }
				 
				 if(j < COLS -1) {
					 grid[i][j].addAdjacency(grid[i][j+1]);
				 }
				 
			}
		}
		

	}
	//	calculates the movement targets
	public void calcTargets( TestBoardCell startCell, int pathlength) {
		//if done recursively
		if(targets.contains(startCell)) {
			return;
		}
		if (pathlength < 0) { 
			return;
		}

		if(startCell.getOccupied()) {
			return;
		}

		if(startCell.getRow() > 0) {
			if(startCell.getRoom()) {
				calcTargets(this.getCell(startCell.getRow()-1, startCell.getCol()), pathlength -100);
			} else {
				calcTargets(this.getCell(startCell.getRow()-1, startCell.getCol()), pathlength -1);
			}

		}

		if(startCell.getRow() < ROWS-1) {
			if(startCell.getRoom()) {
				calcTargets(this.getCell(startCell.getRow()+1, startCell.getCol()), pathlength -100);
			} else {
				calcTargets(this.getCell(startCell.getRow()+1, startCell.getCol()), pathlength -1);
			}
		}

		if(startCell.getCol() > 0) {
			if(startCell.getRoom()) {
				calcTargets(this.getCell(startCell.getRow(), startCell.getCol()-1), pathlength -100);
			} else {
				calcTargets(this.getCell(startCell.getRow(), startCell.getCol()-1), pathlength -1);
			}
		}

		if(startCell.getCol() < COLS-1) {
			if(startCell.getRoom()) {
				calcTargets(this.getCell(startCell.getRow(), startCell.getCol()+1), pathlength -100);
			} else {
				calcTargets(this.getCell(startCell.getRow(), startCell.getCol()+1), pathlength -1); 
			}
		}

		if(this.visited.contains(startCell) == false) {
			targets.add(startCell);
		}


	}
	
	//	returns set of targets
	public Set<TestBoardCell> getTargets() {
		return targets;
	}
	
	public void clearTargets() {
		this.targets.clear();
	}
	//	returns cell from testBoard
	public TestBoardCell getCell(int row, int col) {
		return grid[row][col];
	}
	
	public int getTestBoardSize() {
		return COLS * ROWS;
	}
	
	public void addToVisited(TestBoardCell cell) {
		this.visited.add(cell);
	}
	
}
