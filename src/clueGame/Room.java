package clueGame;

public class Room {
	private char label;
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public Room(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public BoardCell getLabelCell() {
		BoardCell b = new BoardCell(0,0);
		return b;
	}
	public BoardCell getCenterCell() {
		BoardCell b = new BoardCell(0,0);
		return b;
	}
	
}
