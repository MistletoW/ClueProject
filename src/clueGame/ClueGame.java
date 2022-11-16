package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClueGame extends JFrame{
	public static final int HEIGHT = 750;
	public static final int WIDTH = 750;
	private static Board board;
	public static void main(String[] args) {
		JPanel mainPanel = new JPanel(new BorderLayout());
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		GameControlPanel gamePanel = new GameControlPanel();
		KnownCardsPanel cardsPanel = new KnownCardsPanel((board.getPlayers().get(0)));
		
//		board.setLayout(new BorderLayout());
//		cardsPanel.Panel.setLayout(new BorderLayout());
//		gamePanel.setLayout(new BorderLayout());
		mainPanel.add(board, BorderLayout.CENTER);
		mainPanel.add(cardsPanel, BorderLayout.EAST);
		mainPanel.add(gamePanel, BorderLayout.SOUTH);
		
		ClueGame frame = new ClueGame();  // create the frame
		frame.setContentPane(mainPanel); // put the panel in the frame
		frame.setSize(HEIGHT,WIDTH);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		
//		// test filling in the data
//		panel.setTurn(new ComputerPlayer( "Col. Mustard", 0, 0, "orange"), 5);
//		panel.setGuess( "I have no guess!");
//		panel.setGuessResult( "So you have nothing?");
//		
//		HumanPlayer john = new HumanPlayer("John", "red");
//		john.updateHand(new Card("Wrench", CardType.WEAPON));
//		john.updateHand(new Card("Library", CardType.ROOM));
//		john.updateHand(new Card("George", CardType.PERSON));
//		john.updateHand(new Card("Fred", CardType.PERSON));
//		john.updateHand(new Card("Velma", CardType.PERSON));
//		john.updateHand(new Card("Daphne", CardType.PERSON));
//		john.updateSeen(new Card("Subway", CardType.ROOM));
//		KnownCardsPanel cardsPanel = new KnownCardsPanel(john);
//		frame.setContentPane(cardsPanel);
//		frame.setSize(250,800);

	}

}
