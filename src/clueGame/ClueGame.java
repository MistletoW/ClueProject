package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	public static int newRoll = 1 + (int)(Math.random() * 6);
	public static int gameTurn = 0;
	public static Solution accusation = new Solution(new Card("None", CardType.ROOM),new Card("None", CardType.PERSON),new Card("None", CardType.WEAPON));
	

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
		
		String welcomeMessage = "You are " + board.getPlayers().get(0).getName() + ".\n";
		welcomeMessage += "Can you find the solution\nbefore the Computer players?";
		JOptionPane.showMessageDialog(frame, welcomeMessage,"Welcome to Clue", JOptionPane.DEFAULT_OPTION);
		
//		for(BoardCell cell: board.getCell(0,7).getAdjList()) {
//			System.out.println(cell);
//		}
	}
	
	public static void setNextTurn() {
		//when next is hit, increase turn and get new roll
		if(board.playerWasMoved == true) {
			gameTurn += 1;
			newRoll = 1 + (int)(Math.random() * 6);
			//set turn to new player and new roll
			gamePanel.setTurn(board.getPlayers().get(gameTurn % board.getPlayers().size()), newRoll);
			
			//define new target and calculates
			board.calcTargets(board.getPlayers().get(gameTurn % board.getPlayers().size()).getCell(), newRoll);
			//if it's the player's turn again, allow them to move again
			if((gameTurn%6) == 0) {
				board.playerWasMoved = false;
				board.repaint();
			} else {
				accusation = new Solution(new Card("None", CardType.ROOM),new Card("None", CardType.PERSON),new Card("None", CardType.WEAPON));
				ComputerPlayer player = (ComputerPlayer) board.getPlayers().get(gameTurn %6);
				player.makeAccusation(board);
				BoardCell cell = player.selectTarget(board.getTargets());
				player.setPosition(cell.getRow(), cell.getCol());
				accusation = player.createSuggestion(board);
				board.repaint();
				for(Player accused: board.getPlayers()) {
					if(accused.getName() == accusation.getPerson().getName()) {
						accused.setPosition(player.getRow(), player.getCol());
						board.repaint();
					}
				}
				gamePanel.setGuess(accusation.getPerson().getName() + ", " + accusation.getWeapon().getName());
				for(Player tester: board.getPlayers()) {
					board.repaint();
					Card disprover = tester.disproveSuggestion(accusation);
					if(disprover != null) {
						tester.updateSeen(disprover);
						gamePanel.setGuessResult("Disproven by " + disprover.getName());
					} else {
						gamePanel.setGuessResult("Not Disproven");

					}
				}
			}
			board.repaint();
		}
	}
	
	

}
