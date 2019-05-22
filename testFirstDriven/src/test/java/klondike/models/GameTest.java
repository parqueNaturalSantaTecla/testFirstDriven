package klondike.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mocked;

public class GameTest {

	@Mocked private Foundation foundation;
	
	@Test
	public void testClear() {
		Game game = new Game();
		this.testStockClear(game);
		this.testWasteClear(game);
		this.testFoundationsClear(game);
		this.testPilesClear(game);
	}

	private void testWasteClear(Game game) {
		assertTrue(game.getWaste().empty());
	}

	private void testFoundationsClear(Game game) {
		Map<Suit, Foundation> foundations = game.getFoundations();
		for(Suit suit : Suit.values()) {
			assertTrue(foundations.get(suit).empty());
		}
	}
	
	private void testPilesClear(Game game) {
		List<Pile> piles = game.getPiles();
		for(int i=0; i< piles.size(); i++) {
			Pile pile = piles.get(i);
			for(int j= 0; j < i+1; j++) {
				Card card = pile.peek();
				pile.removeTop(1);
				assertTrue(card.isFacedUp());
			}
			assertTrue(pile.empty());
		}
	}
	
	private void testStockClear(Game game) {
		int cardsInPiles = this.cardsInStock(game);
		Stock stock = game.getStock();
		for(int i=0; i< Number.values().length * Suit.values().length - cardsInPiles; i++) {
			Card card = stock.pop();
			assertFalse(card.isFacedUp());
		}
		assertTrue(stock.empty());
	}

	private int cardsInStock(Game game) {
		int pilesSize = game.getPiles().size();
		int cardsInPiles = 0;
		for(int i=1; i<= pilesSize; i++) {
			cardsInPiles += i;
		}
		return cardsInPiles;
	}
	
	@Test
	public void testIsFinished() {
		new Expectations() {{
			foundation.isComplete(); result = true;
		}};
		Game game = new Game();
		assertTrue(game.isFinished());
	}

}
