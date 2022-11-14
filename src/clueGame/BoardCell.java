package clueGame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

public class BoardCell {
	private int row;
	private int col;
	private String cell;
	private char initial;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage = ' ';
	private Set<BoardCell> adjList;
	boolean isOccupied;
	
	public BoardCell(int row, int col) {
		this.row = row;
		this.col = col;
		adjList = new HashSet<BoardCell> ();
	}
	public void setCell(String cell){
		this.cell = cell;
		initial = cell.charAt(0);
	}
	public void setRoomLabel() {
		if(cell.length() > 1) {
			if(cell.charAt(1) == '#') {
				roomLabel = true;
			}
		}
	}
	public char getInitial() {
		return initial; 
	}
	public void addAdj(BoardCell adj) {
		adjList.add(adj);
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
		if(this.isDoorway() == true) {
			char c = cell.charAt(1); 
			switch(c) {
			case('^'):
				doorDirection = DoorDirection.UP;
				break;
			case('v'):
				doorDirection = DoorDirection.DOWN;
				break;
			case('<'):
				doorDirection = DoorDirection.LEFT;
				break;
			case('>'):
				doorDirection = DoorDirection.RIGHT;
				break;
			default: doorDirection = DoorDirection.NONE;
				break;
			}
			return doorDirection;
		}
		return null;
	}
	public boolean isLabel() {
		if(cell.length() > 1) {
			if(cell.charAt(1) == '#') {
				roomLabel = true;
			}
		}
		return roomLabel;
	}
	public boolean isRoomCenter() {
		 if(cell.length() > 1) {
			if(cell.charAt(1) == '*') {
				roomCenter = true;
			}
		}
		return roomCenter;
	}
	public char getSecretPassage() {
		if(cell.length() > 1) {
			secretPassage = cell.charAt(1);
		}
		return secretPassage;
	}

//	toString
	public String toString() {
		return "Cell: " + cell + "\nAt : " + row + ", " + col ;
	}
	
	public Set<BoardCell> getAdjList(){
		return adjList;
	}
	
	public void setOccupied(boolean value) {
		this.isOccupied = value;
	}
	
	public String getCellValue() {
		return cell;
	}
	//draws the cell, given a size, offset, and graphics object
	public void draw(Dimension cellSize, int offset, Graphics g) {
		//draw the cell
		//draw the room names (we can do this here or in the room class!)
	}
}
