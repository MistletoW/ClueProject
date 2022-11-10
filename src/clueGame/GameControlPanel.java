package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class GameControlPanel extends JPanel{
//	private JPanel mainPanel;
	public GameControlPanel() {
		createLayout();
		
	}
		public void createLayout() {
			JPanel subPanel1 = createSubPanel1();
			JPanel subPanel2 = createSubPanel2();
			
			add(subPanel1);
			add(subPanel2);
			
			
		}
		
		public JPanel createSubPanel1() { 
			JPanel whoseTurn = new JPanel(new GridLayout(2,0));
			JLabel prompt = new JLabel("Whose Turn");
			whoseTurn.add(prompt);
			JTextField answer = new JTextField(); //note: we will need to change this!
			whoseTurn.add(answer);
			
			JPanel roll = new JPanel(new GridLayout(0,2));
			JLabel rollLabel = new JLabel("Roll");
			rollLabel.setLayout(new BorderLayout());
			roll.add(rollLabel, BorderLayout.CENTER);
			JTextField rollAnswer = new JTextField();
			roll.add(rollAnswer);
			
			
			JButton makeAccusation = new JButton("Make Accusation");
			makeAccusation.setPreferredSize(new Dimension(160,60));
			JButton next = new JButton("NEXT");
			next.setPreferredSize(new Dimension(180,60));
			JPanel subPanel1 = new JPanel(new GridLayout(1,4));
			subPanel1.add(whoseTurn);
			subPanel1.add(roll);
			subPanel1.add(makeAccusation);
			subPanel1.add(next);
			
			return subPanel1;
		}
		
		public JPanel createSubPanel2() {
			JPanel guessPanel = new JPanel();
			JLabel guess = new JLabel("Guess");
			JTextField playerGuess = new JTextField();
			JPanel resultPanel = new JPanel();
			JLabel resultLabel = new JLabel("Guess Result");
			JTextField resultDisplay = new JTextField("So you have nothing?");
			
			//make guess panel
			guessPanel.add(guess);
			guessPanel.add(playerGuess);
			
			//make result panel
			resultPanel.add(resultLabel);
			resultPanel.add(resultDisplay);
			
			//add these to subPanel2
			
			JPanel subPanel2 = new JPanel(new GridLayout(0,2));
			subPanel2.add(guessPanel);
			subPanel2.add(resultPanel);
			return subPanel2;
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
