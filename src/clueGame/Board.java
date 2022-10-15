package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import experiment.TestBoardCell;

import java.util.*;

public class Board {
	private BoardCell[][] grid;

	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap = new HashMap<Character, Room>();
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private int numRows;
	private int numColumns;
	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board theInstance = new Board();
	//	constructor is private to ensure only one can be created
	private Board() {
		super();
		targets = new HashSet<BoardCell> ();
	}
	//	this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	/*
	 * 	initialize the board (since we are using singleton pattern)
	 */
	public void initialize() {
		try {
			loadSetupConfig();
		} catch (FileNotFoundException | BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadLayoutConfig();
		} catch (FileNotFoundException | BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//	set the config files
	public void setConfigFiles(String csv, String txt) {
		this.setupConfigFile = "data/" + txt;
		this.layoutConfigFile = "data/" + csv;

	}
	//	load setup
	public void loadSetupConfig() throws FileNotFoundException, BadConfigFormatException{
		try {
			FileReader reader = new FileReader(setupConfigFile);
			Scanner in = new Scanner(reader);
			while(in.hasNextLine()) {
				String data = in.nextLine();
				String splitData[] = data.split(", ");
				for(int i = 0; i < splitData.length; i++) {
				}
				//ignore comments
				if(!((splitData[0].substring(0, 2)).equals("//"))) {
					//make sure format is correct, we will treat both actual rooms and walkway/empty space as rooms
					if(splitData[0].equals("Room") || splitData[0].equals("Space")) {
						//load room data 
						Room r = new Room(splitData[1], splitData[2].charAt(0));
						roomMap.put(splitData[2].charAt(0) ,r);

					}else {
						throw new BadConfigFormatException();
					}
				} 

			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found");
			e.printStackTrace();
		}
	}
	//	load layout
	public void loadLayoutConfig() throws FileNotFoundException, BadConfigFormatException{
		// reading in the file to get the file size, check to make sure column length is consistent
		try {
			FileReader reader = new FileReader(layoutConfigFile);
			Scanner in = new Scanner(reader);
			numRows = 0;
			numColumns = 0;
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String cells[] = line.split(",");
				if(numColumns == 0) {
					numColumns = cells.length;
				} else if(numColumns != cells.length && cells.length != 0) {
					System.out.println();
					throw new BadConfigFormatException();
				}
				if(cells.length != 0) {
					numRows++;
				}
			}
			in.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		//create our grid
		grid = new BoardCell[numRows][numColumns];
		for(int i = 0; i< numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
		//pass in the data to each board cell
		try {
			FileReader reader = new FileReader(layoutConfigFile);
			Scanner in = new Scanner(reader);
			int row = 0;
			while (row < numRows){
				String line = in.nextLine();
				String cells[] = line.split(",");
				for(int col = 0; col < cells.length; col++) {
					String cell = cells[col];
					//define characteristics about our board cell
					if(roomMap.get(cell.charAt(0)) != null){
						grid[row][col].setCell(cell);
					}else {
						throw new BadConfigFormatException();
					}
				}

				row++;
			}
			in.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	// load room based on char
	public Room getRoom(char roomChar) {
		Room r = roomMap.get(roomChar);
		return r;
	}
	// load room based on cell 
	public Room getRoom(BoardCell c) {
		char r = c.getInitial();
		Room room = roomMap.get(r);
		return room;
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
	
	public Set<BoardCell> getAdjList(int row, int col){
		return this.getCell(row,col).getAdjList();
	}

	public void calcTargets( BoardCell startCell, int pathlength) {
		
	}
	
	public Set<BoardCell> getTargets(){
		return targets;
	}
	
	
}
