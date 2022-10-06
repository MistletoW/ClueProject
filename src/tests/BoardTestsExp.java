package tests;

import experiment.TestBoard;

public class BoardTestsExp {
	TestBoard board;
	
	@BeforeEach
	public void setUp() {
//		Initialize testboard
//		testboard 
		board = new TestBoard();
	}
	
	@Test
	public void testAdjacency() {
		TestBoardCell cell = board.getCell(0,0)
	}
	
}
