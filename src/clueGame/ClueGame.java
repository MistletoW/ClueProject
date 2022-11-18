package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClueGame extends JFrame{
	public static final int HEIGHT = 750;
	public static final int WIDTH = 850;
	public static Board board;
	public static GameControlPanel gamePanel;
	public static KnownCardsPanel cardsPanel;
	public static int newRoll = 1 + (int)(Math.random() * 7);
	public static int gameTurn = 0;
	

	public ClueGame() {
		super();
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		board.initialize();
		gamePanel = new GameControlPanel();
		gamePanel.setTurn(board.getPlayers().get(0), newRoll);
		cardsPanel = new KnownCardsPanel((board.getPlayers().get(0)));
		add(board, BorderLayout.CENTER);
		add(cardsPanel, BorderLayout.EAST);
		add(gamePanel, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		//JPanel mainPanel = new JPanel(new GridLayout(2,0))
		ClueGame frame = new ClueGame();  // create the frame
		frame.setSize(WIDTH,HEIGHT);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		
		String welcomeMessage = "You are " + board.getPlayers().get(0).getName() + "\n";
		welcomeMessage += "Can you find the solution\nbefore the Computer players?";
		JOptionPane.showMessageDialog(frame, welcomeMessage,"Welcome to Clue", JOptionPane.DEFAULT_OPTION);
		
		
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
	
	public static void setNextTurn() {
		gameTurn += 1;
		newRoll = 1 + (int)(Math.random() * 7);
		gamePanel.setTurn(board.getPlayers().get(gameTurn % board.getPlayers().size()), newRoll);

	}
}
