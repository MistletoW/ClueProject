package clueGame;

import java.util.Set;

public class BoardCell {
	private int row;
	private int col;
	private char initial;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage;
	private Set<BoardCell> adjList;
	
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public void addAdj(BoardCell adj) {
		
	}
	public boolean isDoorway() {
		return false;
	}
	public DoorDirection getDoorDirection() {
		return DoorDirection.UP;
	}
	public boolean isLabel() {
		return false;
	}
	public boolean isRoomCenter() {
		return false;
	}
	public char getSecretPassage() {
		return 'A';
	}
}
