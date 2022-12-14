package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

public class BoardCell {
	public int xPos;
	public int yPos;
	private int width;
	private int height;
	private String cell;
	private char initial;
	private DoorDirection doorDirection;
	private boolean roomLabel;
	private boolean roomCenter;
	private char secretPassage = ' ';
	private Set<BoardCell> adjList;
	boolean isOccupied;

	public BoardCell(int x, int y) {
		this.xPos = x;
		this.yPos = y;
		adjList = new HashSet<BoardCell> ();
	}
	public void setCell(String cell){
		this.cell = cell;
		initial = cell.charAt(0);
	}
	
	public int getRow() {
		return xPos;
	}
	public int getCol() {
		return yPos;
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
		return "Cell: " + cell + "\nAt : " + xPos + ", " + yPos ;
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
	public Color setColor() {
		Color color = Color.BLACK;
		//walkways are light brown-ish
		if(initial == 'W') {
			color = new Color(195, 151, 110);
		}
		//rooms are gray
		if(initial != 'X' && initial != 'W') {
			color = Color.GRAY;
		}

		return color;
	}
	//draws the cell, given a size, offset, and graphics object
	public void draw(int width, int height, int offset, Graphics g) {
		//store the width and height for mouse purposes
		this.width = width;
		this.height = height;
		//draw the cell
		g.setColor(setColor());
		//have rooms be connected
		if(initial != 'X' && initial != 'W') {
			offset = 0;
		}
		g.fillRect(xPos*width, yPos*height, width - offset, height - offset);

		//if there's a a door, draw it!
			g.setColor(Color.BLUE);
			//for left and right doors, the width of the door is 1/5 the width of the cell
			int doorWidth = width/5;
			//for up and down doors, the height of the door is 1/5 the width of the cell
			int doorHeight = height/5;
			if (doorDirection == DoorDirection.DOWN) {
				//if door is facing down, put the door at the bottom of the cell
				int doorDownOffset = height*4/5;
				g.fillRect(xPos*width, yPos*height+doorDownOffset, width - offset, doorHeight);
			}
			if (doorDirection == DoorDirection.UP) {
				g.fillRect(xPos*width, yPos*height, width - offset, doorHeight);
			}
			if (doorDirection == DoorDirection.LEFT) {
				g.fillRect(xPos*width, yPos*height, doorWidth, height - offset);
			}
			if (doorDirection == DoorDirection.RIGHT) {
				//if door is facing right, put the door on the right side of the cell
				int doorRightOffset = width*4/5;
				g.fillRect(xPos*width+doorRightOffset, yPos*height, doorWidth, height - offset);
			}
			
		}
	public boolean containsClick(int mouseX, int mouseY) {
		Rectangle rect = new Rectangle(xPos*width, yPos*height, width, height);
		if(rect.contains(new Point(mouseX, mouseY))) {
			return true;
		}
		return false;
	}

}
