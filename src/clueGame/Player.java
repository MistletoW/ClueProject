package clueGame;

public abstract class Player {
	private String name;
	private String color;
	private int row, column;
	
	public Player(String name, String color){
		this.name = name;
		this.color = color;
	}
	public abstract void updateHand(Card card);
	
}
