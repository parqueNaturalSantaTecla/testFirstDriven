package klondike.models;

import java.util.ArrayList;
import java.util.List;

public class PileBuilder {

	private List<Card> cardList;
	
	public PileBuilder() {
		this.cardList = new ArrayList<Card>();
	}
	
	public PileBuilder card(Card card) {
		this.cardList.add(card);
		return this;
	}
	
	public Pile build() {
		return new Pile(cardList.size(), this.cardList);
	}
	
	
}
