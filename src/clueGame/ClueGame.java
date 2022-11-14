package clueGame;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClueGame extends JFrame{

	public static void main(String[] args) {
		JPanel mainPanel = new JPanel(new GridLayout(2,0));
		Board boardPanel = new Board();
		GameControlPanel gamePanel = new GameControlPanel();
		KnownCardsPanel cardsPanel = new KnownCardsPanel((boardPanel.getPlayers().get(0)));
		
		mainPanel.add(boardPanel);
		mainPanel.add(cardsPanel);
		mainPanel.add(gamePanel);
		
		ClueGame frame = new ClueGame();  // create the frame
		frame.setContentPane(mainPanel); // put the panel in the frame
		frame.setSize(750,750);  // size the frame
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
