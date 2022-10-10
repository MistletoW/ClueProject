package experiment;

import java.util.HashSet;
import java.util.Set;

public class TestBoardCell {
	private Set<TestBoardCell> adjList;
	private boolean isRoom, isOccupied;
	private int row, col;
//	constructor
	public TestBoardCell(int row, int col) {
		isOccupied = false;
		isRoom = false;
		adjList = new HashSet<TestBoardCell> ();
	}
	
//	setter to add cells to adjacency list
	public void addAdjacency( TestBoardCell cell) {
		adjList.add(cell);
	}
	
//	gets our adjList
	public Set<TestBoardCell> getAdjList(){
		return adjList;
	}
	
//	sets if a room is occupied
	public void setOccupied(boolean Occupied) {
		this.isOccupied = Occupied;
	}
	
//	gets if a room is occupied
	public boolean getOccupied() {
		return this.isOccupied;
	}
	
//	sets if a space is room
	public void setRoom(boolean room) {
		this.isRoom = room;
	}
	
//	gets if a space is room
	public boolean getRoom() {
		return this.isRoom;
	}
	
	
}
