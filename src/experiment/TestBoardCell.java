package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoardCell {
	private Set<TestBoardCell> adjacencyList;
	private boolean occupied;
	private boolean isRoom;
//	constructor
	public TestBoardCell(int row, int col) {
		occupied = false;
		isRoom = false;
		adjacencyList = new HashSet<TestBoardCell> ();
	}
	
//	setter to add cells to adjacency list
	public void addAdjacency( TestBoardCell cell) {
		adjacencyList.add(cell);
	}
	
//	gets our adjList
	public Set<TestBoardCell> getAdjList(){
		return adjacencyList;
	}
	
//	sets if a room is occupied
	public void setOccupied(boolean Occupied) {
		this.occupied = Occupied;
	}
	
//	gets if a room is occupied
	public boolean getOccupied() {
		return this.occupied;
	}
	
//	sets if a space is room
	public void setRoom(boolean room) {
		this.isRoom = room;
	}
	
//	gets if a space is room
	public boolean getRoom() {
		return this.occupied;
	}
	
	
}
