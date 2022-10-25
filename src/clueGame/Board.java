package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;

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
		visited = new HashSet<BoardCell> ();
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
		
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				this.setAdjList(i,j);
				BoardCell c = getCell(i,j);
				roomMap.get(c.getInitial()).setCenterCell(i,j);
				roomMap.get(c.getInitial()).setLabelCell(i,j);
			}
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
		getBoardSize();
		//create our grid
		createGrid();
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
	public void createGrid() {
		grid = new BoardCell[numRows][numColumns];
		for(int i = 0; i< numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
	}
	public void getBoardSize() throws BadConfigFormatException {
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
		targets.clear();
		visited.clear(); //clear lists so that they function properly
		
		visited.add(startCell); //add startcell so that visited isn't null
		
		calcTargetsRecursion(startCell, pathlength); //recursive call startcell
		
	}
	
	public void calcTargetsRecursion(BoardCell startCell, int pathlength) {
		//recursive call helper for CalcTargets
		Set<BoardCell> adjList = startCell.getAdjList(); //get adjList
		Iterator<BoardCell> it = adjList.iterator(); //get iterator for adjList
		BoardCell thisCell;
		while(it.hasNext()) {
			thisCell = it.next(); //set thisCell to next in adjList
			
			if(visited.contains(thisCell) == false) {
				//if already visited then skip
				//if not visited add to visited
				visited.add(thisCell);
				if(pathlength == 1) {
					//if pathlength is 1 and adjCell is not occupied then add to adjCell
					if(thisCell.isOccupied == false) {
						targets.add(thisCell);
					}
					
				} else if(thisCell.isRoomCenter()) {
					targets.add(thisCell);
					calcTargetsRecursion(thisCell, pathlength-1);
				} else {
					if(thisCell.isOccupied == false) {
						//if adjCell isn't occupied then recursive call
						calcTargetsRecursion(thisCell, pathlength-1);
					}
				}
				visited.remove(thisCell);
			}
		}
	}
	
	public Set<BoardCell> getTargets(){
		return targets;
	}
	
	public void setAdjList(int row, int col) {
		BoardCell startCell = this.getCell(row,col);
		
		if (startCell.getCellValue().length() < 2) {
			getAdjCommon(startCell, row, col);
		} else {
			
			//if doorway, determine direction
			if(startCell.isDoorway()) {
				
				getAdjCommon(startCell, row, col); //get common adj cells around door
				DoorDirection doorDir = startCell.getDoorDirection();
				
				getAdjToDoor(startCell, doorDir, row, col);
			}
				
			if(startCell.isRoomCenter()) { //KATHRYN YOU MIGHT HAVE TO LOOK AT THIS
				getSecretPassage(startCell, startCell.getInitial());
			}
		}
	}
	
	public void getAdjCommon(BoardCell startCell, int row, int col) {
		
		if(row > 0) {
			if(startCell.getInitial() == this.getCell(row-1, col).getInitial()) {
				startCell.addAdj(this.getCell(row-1, col));
			}
		}
		if(col > 0) {
			if(startCell.getInitial() == this.getCell(row, col-1).getInitial()) {
				startCell.addAdj(this.getCell(row, col-1));
			}
		}
		if(row < numRows -1) {
			if(startCell.getInitial() == this.getCell(row+1, col).getInitial()) {
				startCell.addAdj(this.getCell(row+1, col));
			}
		}
		if(col < numColumns -1) {
			if(startCell.getInitial() == this.getCell(row, col+1).getInitial()) {
				startCell.addAdj(this.getCell(row, col+1));
			}
		}
	}
	
	public void getAdjToDoor(BoardCell startCell, DoorDirection whichWay, int row, int col) {
		int xOffset = 0;
		int yOffset = 0;
		
		//establish the which direction the door is facing
		if(whichWay == DoorDirection.UP) {
			yOffset = -1;
		}
		if(whichWay == DoorDirection.DOWN) {
			yOffset = 1;
		}
		if(whichWay == DoorDirection.LEFT) {
			xOffset = -1;
		}
		if(whichWay == DoorDirection.RIGHT) {
			xOffset = 1;
		}
		
		//get the room initial
		char initial = this.getCell(row + yOffset, col + xOffset).getInitial();
		
		//find the room center with initial
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				if(this.grid[i][j].getInitial() == initial && this.grid[i][j].isRoomCenter()) {
					startCell.addAdj(grid[i][j]);//door add room center
					grid[i][j].addAdj(startCell);//room center add doorway
				}
			}
		}
	}
	
	public void getSecretPassage(BoardCell startCell, char initial) {
		char secret = ' ';
		char secretRoomInitial = ' ';
		for(int i = 0; i < numRows; i++) {	//for every cell in grid
			for(int j = 0; j < numColumns; j++) {	

				//if room has secret passage way
				if(grid[i][j].getInitial() == initial && 
						grid[i][j].getSecretPassage() != '#' && 
						grid[i][j].getSecretPassage() != '*' &&
						grid[i][j].getSecretPassage() != ' ') {
					secret = grid[i][j].getSecretPassage();

					for(int k = 0; k < numRows; k++) { //for every cell in grid
						for(int l = 0; l < numColumns; l++) {
							
							if(grid[k][l].getInitial() == secret && grid[k][l].isRoomCenter()) {
								//System.out.println(grid[k][l]);
								//System.out.println(startCell);
								startCell.addAdj(grid[k][l]);
							}
						}
					}
				}
			}
		}
	}

}

	
