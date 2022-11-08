package clueGame;

public class HumanPlayer extends Player{

	public HumanPlayer(String name, String color) {
		super(name, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void updateHand(Card card) {
		for(Card c: hand) {
			if (hand.contains(c) == false){
				hand.add(card);
			}
		}
	}
	
	@Override
	public boolean isHuman() {
		return true;
	}

}
