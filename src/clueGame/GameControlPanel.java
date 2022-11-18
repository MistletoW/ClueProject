package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameControlPanel extends JPanel{
	//	private JPanel mainPanel;
	
	
	//create all JPanels/Labels/TextFields/Buttons so we can access later
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
	
	private JTextField whoseTurnAnswer;
	private JTextField rollAnswer;
	private JTextField playerGuess;
	private JTextField resultDisplay;
	
	private JButton makeAccusation;
	private JButton next;
	
	//constructor calls createLayout
	public GameControlPanel() {
		//make two subPanels which each have there own items
		setLayout(new BorderLayout());
		
		subPanel1 = createSubPanel1();
		subPanel2 = createSubPanel2();

		//add subpanels to GameControlPanel
		add(subPanel1, BorderLayout.NORTH);
		add(subPanel2, BorderLayout.SOUTH);

	}

	public JPanel createSubPanel1() { 
		//create turn panel, give label and text box which contains whose turn it is
		whoseTurn = new JPanel(new GridLayout(2,0));
		prompt = new JLabel("Whose Turn");
		whoseTurn.add(prompt);
		whoseTurnAnswer = new JTextField();
		whoseTurn.add(whoseTurnAnswer);

		//create roll panel, give it label and text box for roll
		roll = new JPanel();
		rollLabel = new JLabel("Roll");
		rollLabel.setLayout(new BorderLayout());
		roll.add(rollLabel, BorderLayout.CENTER);
		rollAnswer = new JTextField();
		rollAnswer.setPreferredSize(new Dimension(60,25));
		roll.add(rollAnswer);

		//create accusation panel, give it two buttons, next and make accusation
		makeAccusation = new JButton("Make Accusation");
		makeAccusation.setPreferredSize(new Dimension(160,60));
		
		next = new JButton("NEXT");
		next.setPreferredSize(new Dimension(180,60));
		//give next button a listener
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//when next is hit, increase turn and get new roll
				//set turn to new player and new roll
				ClueGame.setNextTurn();
			}
		});
		
		//create temp panel, add all created panels and return tempPanel
		JPanel tempSubPanel1 = new JPanel(new GridLayout(1,4));
		tempSubPanel1.setPreferredSize(new Dimension(720,70));
		tempSubPanel1.add(whoseTurn);
		tempSubPanel1.add(roll);
		tempSubPanel1.add(makeAccusation);
		tempSubPanel1.add(next);

		return tempSubPanel1;
	}

	public JPanel createSubPanel2() {
		//create guess panel, give it guess label and what the player guessed
		guessPanel = new JPanel(new GridLayout(1,0));
		guessPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		guessPanel.setPreferredSize(new Dimension(360,60));
		guess = new JLabel("Guess");
		playerGuess = new JTextField();
		
		//create result panel, give it resultlabel and whatever the result is
		resultPanel = new JPanel(new GridLayout(1,0));
		resultPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		resultPanel.setPreferredSize(new Dimension(360,60));
		resultLabel = new JLabel("Guess Result");
		resultDisplay = new JTextField();

		//make guess panel
		guessPanel.add(guess);
		guessPanel.add(playerGuess);

		//make result panel
		resultPanel.add(resultLabel);
		resultPanel.add(resultDisplay);

		//add these to  temp Panel2

		JPanel tempSubPanel2 = new JPanel(new GridLayout(0,2));
		tempSubPanel2.add(guessPanel);
		tempSubPanel2.add(resultPanel);
		return tempSubPanel2;
	}
	
	public void setTurn(Player player, int roll) {
		//simple setter giving player name and roll value to display
		whoseTurnAnswer.setText(player.getName());
		rollAnswer.setText(String.valueOf(roll));
	}
	
	public void setGuess(String guess) {
		//simple setter giving guess to display
		playerGuess.setText(guess);
	}
	
	public void setGuessResult(String guess) {
		//simple setter giving results of guess to display
		resultDisplay.setText(guess);
	}


}
