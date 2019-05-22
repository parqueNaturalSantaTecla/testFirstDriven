package klondike.models;

public class CardBuilder {

    private Suit suit;

    private Number number;

    private boolean facedUp;
    
    CardBuilder() {
    	this.suit = Suit.CLOVERS;
    	this.number = Number.AS;
    	this.facedUp = false;
    }
    
    CardBuilder suit(Suit suit) {
    	this.suit = suit;
    	return this;
    }
    
    CardBuilder number(Number number) {
    	this.number = number;
    	return this;
    }
    
    CardBuilder facedUp() {
    	this.facedUp = true;
    	return this;
    }
    
    Card build() {
    	Card card = new Card(this.suit, this.number);
    	if (this.facedUp) {
    		card.flip();
    	}
    	return card;
    }

}
