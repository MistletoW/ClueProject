package clueGame;

public class Room {
	private char label;
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;	
	
//	Constructor
	public Room(String name, char label) {
		this.name = name;
		this.label = label;
	}
	
	public String getName() {
		return name;
	}
//	returns the cell with the room label
	public BoardCell getLabelCell() {
		for (int i = 0; i < Board.getInstance().getNumRows(); i++) {
			for (int j = 0; j < Board.getInstance().getNumColumns(); j++) {
				//iterates through board, looks for label cell for the specific room label #
				if(Board.getInstance().getCell(i,j).getInitial() == label) {
					if(Board.getInstance().getCell(i,j).isLabel() == true){
						return Board.getInstance().getCell(i,j);
					}
				}
			}
			
		}
		return null;
	}
//	returns center cell 
	public BoardCell getCenterCell() {
		for (int i = 0; i < Board.getInstance().getNumRows(); i++) {
			for (int j = 0; j < Board.getInstance().getNumColumns(); j++) {
				//iterates through board, looks for label cell with center label *
				if(Board.getInstance().getCell(i,j).getInitial() == label) {
					if(Board.getInstance().getCell(i,j).isRoomCenter() == true){
						return Board.getInstance().getCell(i,j);
					}
				}
			}
			
		}
		return null;
	}
	
}
