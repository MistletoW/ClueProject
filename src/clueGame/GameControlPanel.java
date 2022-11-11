package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class GameControlPanel extends JPanel{
	//	private JPanel mainPanel;
	
	private JPanel subPanel1;
	private JPanel subPanel2;
	private JPanel whoseTurn;
	private JPanel roll;
	private JPanel guessPanel;
	private JPanel resultPanel;

	private JLabel prompt;
	private JLabel rollLabel;
	private JLabel guess;
	private JLabel resultLabel;
	
	private JTextField answer;
	private JTextField rollAnswer;
	private JTextField playerGuess;
	private JTextField resultDisplay;
	
	private JButton makeAccusation;
	private JButton next;
	
	public GameControlPanel() {
		createLayout();

	}
	public void createLayout() {
		subPanel1 = createSubPanel1();
		subPanel2 = createSubPanel2();

		add(subPanel1);
		add(subPanel2);


	}

	public JPanel createSubPanel1() { 
		whoseTurn = new JPanel(new GridLayout(2,0));
		prompt = new JLabel("Whose Turn");
		whoseTurn.add(prompt);
		answer = new JTextField();
		whoseTurn.add(answer);

		roll = new JPanel();
		rollLabel = new JLabel("Roll");
		rollLabel.setLayout(new BorderLayout());
		roll.add(rollLabel, BorderLayout.CENTER);
		rollAnswer = new JTextField();
		rollAnswer.setPreferredSize(new Dimension(60,25));
		roll.add(rollAnswer);


		makeAccusation = new JButton("Make Accusation");
		makeAccusation.setPreferredSize(new Dimension(160,60));
		next = new JButton("NEXT");
		next.setPreferredSize(new Dimension(180,60));
		JPanel tempSubPanel1 = new JPanel(new GridLayout(1,4));
		tempSubPanel1.setPreferredSize(new Dimension(720,70));
		tempSubPanel1.add(whoseTurn);
		tempSubPanel1.add(roll);
		tempSubPanel1.add(makeAccusation);
		tempSubPanel1.add(next);

		return tempSubPanel1;
	}

	public JPanel createSubPanel2() {
		guessPanel = new JPanel(new GridLayout(1,0));
		guessPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		guessPanel.setPreferredSize(new Dimension(360,60));
		guess = new JLabel("Guess");
		playerGuess = new JTextField("I have no guess!");
		resultPanel = new JPanel();
		resultPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		resultPanel.setPreferredSize(new Dimension(360,60));
		resultLabel = new JLabel("Guess Result");
		resultDisplay = new JTextField("So you have nothing?");

		//make guess panel
		guessPanel.add(guess);
		guessPanel.add(playerGuess);

		//make result panel
		resultPanel.add(resultLabel);
		resultPanel.add(resultDisplay);

		//add these to subPanel2

		JPanel tempSubPanel2 = new JPanel(new GridLayout(0,2));
		tempSubPanel2.add(guessPanel);
		tempSubPanel2.add(resultPanel);
		return tempSubPanel2;
	}



	//two smaller ones inside, stacked on top of each other
	//within first panel
	//panel with label and textfield
	//panel with label and textfield
	//jbutton1
	//jbutton2
	//within second panel
	//jpanel with text field
	//jpanel with text field
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameControlPanel panel = new GameControlPanel();
		JFrame frame = new JFrame();  // create the frame
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 180);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible

		// test filling in the data
		//		panel.setTurn(new ComputerPlayer( "Col. Mustard", 0, 0, "orange"), 5);
		//		panel.setGuess( "I have no guess!");
		//		panel.setGuessResult( "So you have nothing?");

	}

}
