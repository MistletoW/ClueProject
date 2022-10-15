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
	public char getInitial() {
		return initial; 
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
		if(cell.length() > 1) {
			char c = cell.charAt(1);
			DoorDirection d; 
			switch(c) {
			case('^'):
				d = DoorDirection.UP;
				break;
			case('v'):
				d = DoorDirection.DOWN;
				break;
			case('<'):
				d = DoorDirection.LEFT;
				break;
			case('>'):
				d = DoorDirection.RIGHT;
				break;
			default: d = DoorDirection.UP;
				break;
		}
			return d;
		}
		return null;
	}
	public boolean isLabel() {
		if(cell.length() > 1) {
			if(cell.charAt(1) == '#') {
				return true;
			}
		}
		return false;
	}
	public boolean isRoomCenter() {
		if(cell.length() > 1) {
			if(cell.charAt(1) == '*') {
				return true;
			}
		}
		return false;
	}
	public char getSecretPassage() {
		if(cell.length() > 1) {
			return cell.charAt(1);
		}
		return cell.charAt(0);
	}

//	toString
	public String toString() {
		return "Cell: " + cell + "\nAt : " + row + ", " + col ;
	}
	
}
