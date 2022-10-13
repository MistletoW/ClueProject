package clueGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import experiment.TestBoardCell;


public class Board {
	private BoardCell[][] grid;

	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;

	// THESE TWO GUYS ARE UNFINISHED
	private int numRows;
	private int numColumns;
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
		setupConfigFile = txt;
		layoutConfigFile = csv;

	}
	//	load setup
	//THIS IS NOT FINISHED YET. 
	public void loadSetupConfig() throws FileNotFoundException{
		try {
			FileReader reader = new FileReader(setupConfigFile);
			Scanner in = new Scanner(reader);
			while(in.hasNextLine()) {
				String data = in.nextLine();
				//something something this is where we get the room data
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found");
			e.printStackTrace();
		}
	}
	//	load layout ALSO NOT FINISHED
	public void loadLayoutConfig() throws FileNotFoundException{
		// reading in the file to get the file size. yay. IMPLEMENT A WAY TO GET THE NUMBER OF COLUMNS, YOU SHOULD ONLY NEED TO DO THIS ONCE
		try {
			FileReader reader = new FileReader(layoutConfigFile);
			Scanner in = new Scanner(reader);
			numRows = 0;
			numColumns = 0;
				while(in.hasNextLine()) {
					in.nextLine();
					numRows++;
				}
				in.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		//using our newly found board sizes, pass in the data to each board cell :D
		try {
			FileReader reader = new FileReader(layoutConfigFile);
			Scanner in = new Scanner(reader);
			int row = 0;
			int col = 0;
			
					while (col < numColumns && row < numRows){
						String line = in.nextLine();
						while(col < numColumns) {
							String cells[] = line.split(",");

							String cell = cells[col];
							//this is where we define the boardcell katie. do that
							grid[row][col].setInitial(cell.charAt(0));

							col++;
						}
						if(col == numColumns) {
							row++;
						}
					} 
			in.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
