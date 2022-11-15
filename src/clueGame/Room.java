package clueGame;

import java.awt.Graphics;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;	

	//	Constructor
	public Room(String name, char label) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	//	returns the cell with the room label
	public BoardCell getLabelCell() {

		return labelCell;
	}
	//	returns center cell 
	public BoardCell getCenterCell() {
		return centerCell;
	}
	public void setLabelCell(int row, int col) {
		if(Board.getInstance().getCell(row,col).isLabel() == true){
			labelCell = Board.getInstance().getCell(row,col);
		}


	}
	public void setCenterCell(int row, int col) {
		if(Board.getInstance().getCell(row,col).isRoomCenter() == true){
			centerCell = Board.getInstance().getCell(row,col);

		}
	}
	public void draw(int x, int y, Graphics g) {
		g.drawString(name, x, y);
	}
}
