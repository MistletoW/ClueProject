package experiment;

import java.util.Set;

public class TestBoardCell {
	Set<TestBoardCell> adjacencyList;
	private int row;
	private int col;
//	constructor
	public TestBoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
//	Indicates that the adjacent cell is a room
	public void setRoom(boolean isRoom) {
		
	}
	
//	setter to add cells to adjacency list
	public void addAdjacency( TestBoardCell cell) {
		
	}
	
//	gets our adjList
	public Set<TestBoardCell> getAdjList(){
		return adjacencyList;
	}
	
//	sets if a room is occupied
	public void setOccupied(boolean Occupied) {
		
	}
	
//	gets if a room is occupied
	public boolean getOccupied() {
		return false;
	}
	
}
