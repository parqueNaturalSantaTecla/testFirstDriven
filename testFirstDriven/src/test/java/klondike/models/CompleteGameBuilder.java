package klondike.models;

import java.util.Map;

public class CompleteGameBuilder {

	public Game build() {
		Game game = new Game();
		Map<Suit, Foundation> foundations = game.getFoundations();
		for(int i=0; i< Suit.values().length; i++) {
			Foundation foundation = foundations.get(Suit.values()[i]);
			for(int j=0; j<Number.values().length; j++) {
				foundation.push(new CardBuilder().number(Number.values()[i]).build());
			}
		}
		return game;
	}
	
}
