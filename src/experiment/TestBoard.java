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
//		if done recursively
		
//		if already in targets don't readd
		if(targets.contains(startCell)) {
			return;
		}
		
//		if occupied don't add
		if(startCell.getOccupied()) {
			return;
		}
		
//		if pathlength is 0 we have reached out target
		if (pathlength == 0) { 
			targets.add(startCell);
			return;
		}

		if(visited.contains(startCell)) {
			return;
		}
		
		if(startCell.getRoom()) {
			targets.add(startCell);
			return;
		}
		
		visited.add(startCell);

//		If cell isn't at left edge
		if(startCell.getRow() > 0) {
//			recursive calculate cell to left			
			calcTargets(this.getCell(startCell.getRow()-1, startCell.getCol()), pathlength -1);

		}
//		if cell isn't at right edge
		if(startCell.getRow() < ROWS-1) {
//			recursive calculate cell to right			
			calcTargets(this.getCell(startCell.getRow()+1, startCell.getCol()), pathlength -1);

		}
//		if cell isn't at top edge
		if(startCell.getCol() > 0) {
//			recursive calculate cell to top
			calcTargets(this.getCell(startCell.getRow(), startCell.getCol()-1), pathlength -1);

		}
//		if cell isn't at bottom edge
		if(startCell.getCol() < COLS-1) {
//			recursive calculate cell to bottom
			calcTargets(this.getCell(startCell.getRow(), startCell.getCol()+1), pathlength -1); 
			
		}

	}
	
	//	returns set of targets
	public Set<TestBoardCell> getTargets() {
		return targets;
	}
	
	public void clearTargets() {
		this.targets.clear();
	}
	
	public void clearVisited() {
		this.visited.clear();
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

