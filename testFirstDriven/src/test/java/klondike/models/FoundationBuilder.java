package klondike.models;

import java.util.ArrayList;
import java.util.List;

public class FoundationBuilder {

	private Suit suit;
	
	private List<Card> cards;
	
	public FoundationBuilder() {
		this.suit = Suit.CLOVERS;
		this.cards = new ArrayList<Card>();
	}
	
	public FoundationBuilder suit(Suit suit) {
		this.suit = suit;
		return this;
	}
	
	public FoundationBuilder card(Card card) {
		this.cards.add(card);
		return this;
	}
	
	public Foundation build() {
		Foundation foundation = new Foundation(this.suit);
		for(Card card: this.cards) {
			foundation.push(card);
		}
		return foundation;
	}
	
}
