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
	
	public abstract boolean isHuman();
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean equals(Player o) {
		if(this.name == o.getName() && this.color == o.getColor()) {
			return true;
		}
		return false;
	}
}
