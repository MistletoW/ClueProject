package clueGame;

import java.util.Set;

public class BoardCell {
	private int row;
	private int col;
	private String cell;
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
	public void setCell(String cell){
		this.cell = cell;
	}
	public void setInitial() {
		initial = cell.charAt(0);
	}
	public void addAdj(BoardCell adj) {
		
	}
//	passing in the string of the cell, define whether or not the cell is a doorway. 
	public boolean isDoorway() {
		if (cell.length() > 1) {
			if(cell.charAt(1) == '^' || cell.charAt(1) == 'v' || cell.charAt(1) == '<' || cell.charAt(1) == '>') {
				return true;
			}
		}
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
