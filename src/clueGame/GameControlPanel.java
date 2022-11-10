package clueGame;

import javax.swing.*;

public class GameControlPanel extends JPanel{
	private JPanel mainPanel;
	public GameControlPanel() {
		//Big jpanel - gamecontrolpanel
		
		
		
	}
		public void createLayout() {
			mainPanel = new JPanel();
			JPanel subPanel1 = createSubPanel1();
			JPanel subPanel2 = new JPanel();
			mainPanel.add(subPanel1);
			mainPanel.add(subPanel2);
			
		}
		
		public JPanel createSubPanel1() {
			JPanel whoseTurn = new JPanel();
			JLabel prompt = new JLabel("Whose Turn");
			whoseTurn.add(prompt);
			JTextField answer = new JTextField(); //note: we will need to change this!
			whoseTurn.add(answer);
			
			JPanel roll = new JPanel();
			JLabel rollLabel = new JLabel("Roll");
			roll.add(rollLabel);
			JTextField rollAnswer = new JTextField();
			roll.add(rollAnswer);
			
			
			JButton makeAccusation = new JButton("Make Accusation");
			JButton next = new JButton("NEXT");
			JPanel subPanel1 = new JPanel();
			subPanel1.add(whoseTurn);
			subPanel1.add(roll);
			subPanel1.add(makeAccusation);
			subPanel1.add(next);
			
			return subPanel1;
		}
		
		public JPanel createSubPanel2() {
			JPanel whoseTurn = new JPanel();
			JLabel prompt = new JLabel("Whose Turn");
			whoseTurn.add(prompt);
			JTextField answer = new JTextField(); //note: we will need to change this!
			whoseTurn.add(answer);
			
			JPanel roll = new JPanel();
			JLabel rollLabel = new JLabel("Roll");
			roll.add(rollLabel);
			JTextField rollAnswer = new JTextField();
			roll.add(rollAnswer);
			
			JButton makeAccusation = new JButton("Make Accusation");
			JButton next = new JButton("NEXT");
			JPanel subPanel1 = new JPanel();
			subPanel1.add(whoseTurn);
			subPanel1.add(roll);
			subPanel1.add(makeAccusation);
			subPanel1.add(next);
			return subPanel1;
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
		panel.add(panel.createSubPanel1());
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
