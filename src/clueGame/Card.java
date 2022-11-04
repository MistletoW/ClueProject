package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public Card(String name, CardType type) {
		this.cardName = name;
		this.cardType = type;
	}
	
	public String getName() {
		return this.cardName;
	}
	
	public CardType getType() {
		return this.cardType;
	}
	
	public boolean equals(Card target) {
		if(this.cardType == target.getType() && this.cardName == target.getName()) {
			return true;
		}
		
		return false;
		
	}
}
