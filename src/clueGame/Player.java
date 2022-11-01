package clueGame;

public abstract class Player {
	private String name;
	private String color;
	private int row, column;
	
	public abstract void updateHand(Card card);
	
}
