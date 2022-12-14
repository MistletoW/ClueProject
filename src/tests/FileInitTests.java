package tests;

/*
 * This program tests that config files are loaded properly.
 */

// Doing a static import allows me to write assertEquals rather than
// Assert.assertEquals
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;
import clueGame.Room;

public class FileInitTests {
	// Constants that I will use to test whether the file was loaded correctly
	public static final int NUM_ROWS = 26;
	public static final int NUM_COLUMNS = 31;

	// NOTE: I made Board static because I only want to set it up one
	// time (using @BeforeAll), no need to do setup before each test.
	private static Board board;

	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
	}

	@Test
	public void testRoomLabels() {
		// To ensure data is correctly loaded, test retrieving a few rooms
		// from the hash, including the first and last in the file and a few others
		assertEquals("Subway", board.getRoom('S').getName() );
		assertEquals("Larry's Giant Subs", board.getRoom('L').getName() );
		assertEquals("Dunkin Donuts", board.getRoom('D').getName() );
		assertEquals("Olive Garden", board.getRoom('O').getName() );
		assertEquals("Walkway", board.getRoom('W').getName() );
	}

	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}

	// Test a doorway in each direction (RIGHT/LEFT/UP/DOWN), plus
	// two cells that are not a doorway.
	// These cells are white on the planning spreadsheet
	@Test
	public void FourDoorDirections() {
		BoardCell cell = board.getCell(7, 5);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.LEFT, cell.getDoorDirection());
		cell = board.getCell(8, 3);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.RIGHT, cell.getDoorDirection());
		cell = board.getCell(11, 17);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.DOWN, cell.getDoorDirection());
		cell = board.getCell(12, 7);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.UP, cell.getDoorDirection());
		// Test that walkways are not doors
		cell = board.getCell(14, 12);
		assertFalse(cell.isDoorway());
	}
	

	// Test that we have the correct number of doors
	@Test
	public void testNumberOfDoorways() {
		int numDoors = 0;
		for (int x = 0; x < board.getNumColumns(); x++)
			for (int y = 0; y < board.getNumRows(); y++) {
				BoardCell cell = board.getCell(x, y);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(18, numDoors);
	}

	// Test a few room cells to ensure the room initial is correct.
	@Test
	public void testRooms() {
		// just test a standard room location
		BoardCell cell = board.getCell( 23, 23);
		Room room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Golden Corral" ) ;
		assertFalse( cell.isLabel() );
		assertFalse( cell.isRoomCenter() ) ;
		assertFalse( cell.isDoorway()) ;

		// this is a label cell to test
		cell = board.getCell(10,2);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Baskin Robins" ) ;
		assertTrue( cell.isLabel() );
		assertTrue( room.getLabelCell() == cell );
		
		// this is a room center cell to test
		cell = board.getCell(21, 12);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Chipotle" ) ;
		assertTrue( cell.isRoomCenter() );
		assertTrue( room.getCenterCell() == cell );
		
		// this is a secret passage test
		cell = board.getCell(18, 24);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Golden Corral" ) ;
		assertTrue( cell.getSecretPassage() == 'O' );
		
		// test a walkway
		cell = board.getCell(7, 0);
		room = board.getRoom( cell ) ;
		// Note for our purposes, walkways and closets are rooms
		assertTrue( room != null );
		assertEquals( room.getName(), "Walkway" ) ;
		assertFalse( cell.isRoomCenter() );
		assertFalse( cell.isLabel() );
		
		// test a closet
		cell = board.getCell(0, 7);
		room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Unused" ) ;
		assertFalse( cell.isRoomCenter() );
		assertFalse( cell.isLabel() );
		
	}

}
