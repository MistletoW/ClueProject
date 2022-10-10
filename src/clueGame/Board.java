package clueGame;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import experiment.TestBoardCell;


public class Board {
	private BoardCell[][] grid;
	
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	
// NOTE: THIS IS HARD-CODED TO ALLOW TESTS TO RUN WITHOUT ERROR AND SHOULD BE CHANGED.
	private int numRows = 26;
	private int numColumns = 31;
/*
 * variable and methods used for singleton pattern
 */
	private static Board theInstance = new Board();
//	constructor is private to ensure only one can be created
	private Board() {
		super();
	}
//	this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
/*
 * 	initialize the board (since we are using singleton pattern)
 */
	public void initialize() {
		grid = new BoardCell[numRows][numColumns];
		for(int i = 0; i< numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
		
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				 if(i > 0) {
					 grid[i][j].addAdj(grid[i-1][j]);
				 }
				 
				 if(i < numRows -1) {
					 grid[i][j].addAdj(grid[i+1][j]);
				 }
				 
				 if(j > 0) {
					 grid[i][j].addAdj(grid[i][j-1]);
				 }
				 
				 if(j < numColumns -1) {
					 grid[i][j].addAdj(grid[i][j+1]);
				 }
				 
			}
		}
		
	}
//	set the config files
	public void setConfigFiles(String csv, String txt) {
		
	}
//	load setup
	public void loadSetupConfig() {
		
	}
//	load layout
	public void loadLayoutConfig() {
		
	}
// load room based on char
	public Room getRoom(char roomChar) {
		Room r = new Room();
		return r;
	}
// load room based on cell 
	public Room getRoom(BoardCell c) {
		Room r = new Room();
		return r;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public BoardCell getCell(int row, int col) {
		return grid[row][col];
	}
	
	
}
