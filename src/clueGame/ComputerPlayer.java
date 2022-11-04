package clueGame;

public class ComputerPlayer extends Player{

	public ComputerPlayer(String name, String color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateHand(Card card) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isHuman() {
		return false;
	}

}
