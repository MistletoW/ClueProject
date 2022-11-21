package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener{
	private BoardCell[][] grid;

	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap = new HashMap<Character, Room>();
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private int numRows;
	private int numColumns;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Card> personDeck = new ArrayList<Card>();
	private ArrayList<Card> weaponDeck = new ArrayList<Card>();
	private ArrayList<Card> roomDeck = new ArrayList<Card>();
	private ArrayList<Card> deck = new ArrayList<Card>();
	private Solution theAnswer;
	boolean playerWasMoved = false;

	private final static int[] initialPlayerRow = {8,16,0,0,8,16};
	private final static int[] initialPlayerCol = {0,0,8,16,25,24};
	/*
	 * variable and methods used for singleton pattern
	 */
	private static Board theInstance = new Board();
	//	constructor is private to ensure only one can be created
	Board() {
		super();
		setBackground(Color.black);
		targets = new HashSet<BoardCell> ();
		visited = new HashSet<BoardCell> ();
		addMouseListener(this);
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
		setPlayerPositions();
		for(int i = 0; i < numColumns; i++) {
			for(int j = 0; j < numRows; j++) {
				this.setAdjList(i,j);
				BoardCell c = getCell(i,j);
				roomMap.get(c.getInitial()).setCenterCell(i,j);
				roomMap.get(c.getInitial()).setLabelCell(i,j);
			}
		}
		loadDeck();
		dealSolution();
		dealCards();

	}

	int i = 0;
	private void setPlayerPositions() {
		for (Player p : players) {
			p.setPosition(initialPlayerRow[i],initialPlayerCol[i]);
			i++;
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
						if(splitData[0].equals("Room")) {
							//note: there should be a way to make this code more efficient! I'm just lazy
							Card rc = new Card(splitData[1], CardType.ROOM);
							roomDeck.add(rc);
						}
					} else if(splitData[0].equals("ComputerPlayer")) {
						//create player
						Player p = new ComputerPlayer(splitData[1], splitData[2]);
						players.add(p);
						//create player Card
						Card cc = new Card(splitData[1], CardType.PERSON);
						personDeck.add(cc);
					} else if(splitData[0].equals("HumanPlayer")) {
						//create player
						Player p = new HumanPlayer(splitData[1], splitData[2]);
						players.add(p);
						//create player Card 
						Card pc = new Card(splitData[1], CardType.PERSON);
						personDeck.add(pc);
					} else if(splitData[0].equals("Weapon")) {
						//create weapon card
						Card w = new Card(splitData[1], CardType.WEAPON);
						weaponDeck.add(w);
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
						grid[col][row].setCell(cell);
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
		grid = new BoardCell[numColumns][numRows];
		for(int i = 0; i< numColumns; i++) {
			for(int j = 0; j < numRows; j++) {
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

	public BoardCell getCell(int col, int row) {
		return grid[col][row];
	}

	public Set<BoardCell> getAdjList(int row, int col){
		return this.getCell(row,col).getAdjList();
	}

	public void calcTargets( BoardCell startCell, int pathlength) {
		targets.clear();
		visited.clear(); //clear lists so that they function properly

		visited.add(startCell); //add startcell so that visited isn't null
		calcTargetsRecursion(startCell, pathlength); //recursive call startcell
		for(BoardCell target:targets) {
			System.out.println(target);
		}
	}

	public void calcTargetsRecursion(BoardCell startCell, int pathlength) {
		//recursive call helper for CalcTargets
		Set<BoardCell> adjList = startCell.getAdjList(); //get adjList

		for(BoardCell cell: adjList) {
			if(visited.contains(cell) == false) {
				visited.add(cell);
				if(pathlength == 1) {
					//if pathlength is 1 and adjCell is not occupied then add to adjCell
					if(cell.isOccupied == false) {
						targets.add(cell);
					}

				} else if(cell.isRoomCenter()) {
					targets.add(cell);
					calcTargetsRecursion(cell, pathlength-1);
				} else {
					if(cell.isOccupied == false) {
						//if adjCell isn't occupied then recursive call
						calcTargetsRecursion(cell, pathlength-1);
					}
				}
				visited.remove(cell);
			}
		}
	}

	public Set<BoardCell> getTargets(){
		return targets;
	}

	public void setAdjList(int col, int row) {
		BoardCell startCell = this.getCell(col,row);

		if (startCell.getCellValue().length() < 2) {
			getAdjCommon(startCell, col, row);
		} else {

			//if doorway, determine direction
			if(startCell.isDoorway()) {

				getAdjCommon(startCell, col, row); //get common adj cells around door
				DoorDirection doorDir = startCell.getDoorDirection();

				getAdjToDoor(startCell, doorDir, col, row);
			}

			if(startCell.isRoomCenter()) { 
				getSecretPassage(startCell, startCell.getInitial());
			}
		}
	}

	public void getAdjCommon(BoardCell startCell, int col, int row) {

		if(col > 0) {
			if(startCell.getInitial() == this.getCell(col-1, row).getInitial()) {
				startCell.addAdj(this.getCell(col-1, row));
			}
		}
		if(row > 0) {
			if(startCell.getInitial() == this.getCell(col, row-1).getInitial()) {
				startCell.addAdj(this.getCell(col, row-1));
			}
		}
		if(col < numColumns -1) {
			if(startCell.getInitial() == this.getCell(col+1, row).getInitial()) {
				startCell.addAdj(this.getCell(col+1, row));
			}
		}
		if(row < numRows -1) {
			if(startCell.getInitial() == this.getCell(col, row+1).getInitial()) {
				startCell.addAdj(this.getCell(col, row+1));
			}
		}
	}

	public void getAdjToDoor(BoardCell startCell, DoorDirection whichWay, int row, int col) {
		int xOffset = 0;
		int yOffset = 0;

		//establish the which direction the door is facing
		if(whichWay == DoorDirection.UP) {
			xOffset = -1;
		}
		if(whichWay == DoorDirection.DOWN) {
			xOffset = 1;
		}
		if(whichWay == DoorDirection.LEFT) {
			yOffset = -1;
		}
		if(whichWay == DoorDirection.RIGHT) {
			yOffset = 1;
		}

		//get the room initial
		char initial = this.getCell(row + yOffset, col + xOffset).getInitial();

		//find the room center with initial
		for(int i = 0; i < numColumns; i++) {
			for(int j = 0; j < numRows; j++) {
				if(this.grid[i][j].getInitial() == initial && this.grid[i][j].isRoomCenter()) {
					startCell.addAdj(grid[i][j]);//door add room center
					grid[i][j].addAdj(startCell);//room center add doorway
				}
			}
		}
	}

	public void getSecretPassage(BoardCell startCell, char initial) {
		char secret = ' ';
		for(int i = 0; i < numColumns; i++) {	//for every cell in grid
			for(int j = 0; j < numRows; j++) {	

				//if room has secret passage way
				if(grid[i][j].getInitial() == initial && 
						grid[i][j].getSecretPassage() != '#' && 
						grid[i][j].getSecretPassage() != '*' &&
						grid[i][j].getSecretPassage() != ' ') {
					secret = grid[i][j].getSecretPassage();

					for(int k = 0; k < numColumns; k++) { //for every cell in grid
						for(int l = 0; l < numRows; l++) {

							if(grid[k][1].getInitial() == secret && grid[k][l].isRoomCenter()) {
								startCell.addAdj(grid[k][l]);
							}
						}
					}
				}
			}
		}
	}
	//returns the players 
	public ArrayList<Player> getPlayers() {
		return players;
	}
	//returns the cards
	public ArrayList<Card> getDeck() {
		return deck;
	}
	//combine separate decks into one deck for later dealing 
	public void loadDeck() {
		for(Card i : personDeck) {
			deck.add(i);
		}
		for(Card i : roomDeck) {
			deck.add(i);
		}
		for(Card i : weaponDeck) {
			deck.add(i);
		}
	}
	public void dealSolution() {
		//pick a random card from each deck
		Random randomPerson = new Random();
		int p = randomPerson.nextInt(personDeck.size());
		Card person = personDeck.get(p);

		Random randomWeapon = new Random();
		int w = randomPerson.nextInt(weaponDeck.size());
		Card weapon = weaponDeck.get(w);

		Random randomRoom = new Random();
		int r = randomPerson.nextInt(roomDeck.size());
		Card room = roomDeck.get(r);

		theAnswer = new Solution(room, person, weapon);

		deck.remove(person);
		deck.remove(room);
		deck.remove(weapon);
	}

	public Solution getSolution() {
		return theAnswer;
	}

	public void dealCards() {
		int counter = 0;
		while(deck.size() > 0) {
			if(counter == players.size()) {
				counter = 0;
			}
			Random rand = new Random();
			int r = rand.nextInt(deck.size());
			Card c = deck.get(r);
			deck.remove(r);
			players.get(counter).addCard(c);
			counter++;
		}
	}

	public boolean checkAccusation(Card roomAcc, Card personAcc, Card weaponAcc) {
		if(roomAcc == theAnswer.getRoom()) { //check if room matches
			if(personAcc == theAnswer.getPerson()) { //check if person matches
				if(weaponAcc == theAnswer.getWeapon()) { // check if weapon matches
					return true; //if all match, return true
				}
			}
		}
		//if one does not match return false
		return false;
	}

	public Card handleSuggestion(Player p, Solution suggestion) {
		Card tester = null; //create card and set to null 
		int i = 0; //create iterator for players
		do {
			if((players.get(i).equals(p))) {
				i++;
			} else {

				tester = players.get(i).disproveSuggestion(suggestion); //go through players, disprove if can

				i++; //iterate through players
			}

		}while(tester == null && i < players.size()); //if we find a suggestion that disproves or run out of players exit loop

		return tester;//return 
	}

	//getters for decks
	public ArrayList<Card> getWeaponDeck(){
		return weaponDeck;
	}

	public ArrayList<Card> getPersonDeck(){
		return personDeck;
	}

	public ArrayList<Card> getRoomDeck() {
		return roomDeck;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int xSize = getWidth()/numColumns;
		int ySize = getHeight()/numRows;

		//draw cells
		for(int i = 0; i < grid.length; ++i) {
			for(int j = 0; j< grid[i].length; ++j) {
				grid[i][j].draw(xSize, ySize, 1, g);

			}
		}


		//draw labels
		for (Room currentRoom : roomMap.values()) {
			BoardCell b = currentRoom.getLabelCell();
			if(b!= null) {
				currentRoom.draw(b.row*xSize, b.col*ySize, g);
			}			
		}
		//draw players
		for (Player p : players) {
			p.draw(xSize, ySize, g);
		}

		if(ClueGame.gameTurn%players.size() == 0) {
			calcTargets(getPlayers().get(ClueGame.gameTurn % getPlayers().size()).getCell(), ClueGame.newRoll);

			for(BoardCell target : targets) {
				//System.out.println(target);
				g.setColor(Color.BLACK);
				g.fillOval(target.getRow()*xSize, target.getCol()*ySize, xSize, ySize);
			}
		}
	}

	//mouselistener methods
	@Override
	public void mousePressed(MouseEvent e) {
		// If it is the human player's turn, get the cell that was clicked on
		if((ClueGame.gameTurn%6) == 0) {
			BoardCell clickedCell = null;
			for(int i = 0; i < numColumns; i++) {
				for(int j = 0; j < numRows; j++) {
					if(grid[i][j].containsClick(e.getX(), e.getY())) {
						clickedCell = grid[i][j];
					}
				}
			}
			//if the clicked cell is in our targets list, move the player there ONCE
			if (!playerWasMoved) {
				if (clickedCell != null) {
					if(targets.contains(clickedCell)) {
						players.get(0).setPosition(clickedCell.getRow(), clickedCell.getCol());
						playerWasMoved = true;
						repaint();
					}
					else {
						String errorMessage = ("Please click on a target!");
						JOptionPane.showMessageDialog(null, errorMessage,"Error", JOptionPane.DEFAULT_OPTION);

					}
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	// 
}




