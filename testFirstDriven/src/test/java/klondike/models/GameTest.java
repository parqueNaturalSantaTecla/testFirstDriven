package klondike.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GameTest {
	
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
		assertTrue(new CompleteGameBuilder().build().isFinished());
	}
	
	@Test
	public void testMoveFromStockToWaste() {
		Game game = new Game();
		Card card = game.getStock().peek();
		assertEquals(null, game.moveFromStockToWaste());
		assertNotEquals(card, game.getStock().peek());
		assertEquals(card, game.getWaste().peek());
	}
	
	@Test
	public void testMoveFromStockToWasteWithError() {
		Game game = new Game();
		Stock stock = game.getStock();
		while (!stock.empty()) {
			stock.pop();
		}
		assertEquals(Error.EMPTY_STOCK, game.moveFromStockToWaste());
	}
	
	@Test
	public void testMoveFromWasteToFoundation() {
		Suit suit = Suit.PIKES;
		Card card = new CardBuilder().number(Number.AS).suit(suit).build();
		Game game = new Game();
		game.getWaste().push(card);
		assertEquals(null, game.moveFromWasteToFoundation(suit));
		assertEquals(card, game.getFoundations().get(suit).peek());
	}
	
	@Test
	public void testMoveFromWasteToFoundationWithEMPTY_WASTE() {
		Suit suit = Suit.PIKES;
		Game game = new Game();
		assertEquals(Error.EMPTY_WASTE, game.moveFromWasteToFoundation(suit));
		assertTrue(game.getWaste().empty());
		assertTrue(game.getFoundations().get(suit).empty());
	}
	
	@Test
	public void testMoveFromWasteToFoundationWithNO_FIT_FOUNDATION() {
		Suit suit = Suit.PIKES;
		Card card = new CardBuilder().number(Number.FIVE).suit(suit).build();
		Game game = new Game();
		game.getWaste().push(card);
		assertEquals(Error.NO_FIT_FOUNDATION, game.moveFromWasteToFoundation(suit));
		assertEquals(card, game.getWaste().peek());
		assertTrue(game.getFoundations().get(suit).empty());
	}
	
	@Test
	public void testMoveFromWasteToPileWithEmptyPile() {
		Card card = new CardBuilder().number(Number.KING).build();
		Game game = new Game();
		game.getWaste().push(card);
		int pileNumber = 0;
		game.getPiles().get(pileNumber).pop();
		assertEquals(null, game.moveFromWasteToPile(pileNumber));
		assertEquals(card, game.getPiles().get(pileNumber).peek());
	}
	
	@Test
	public void testMoveFromWasteToPileWithEmptyWithNO_FIT_PILE() {
		Card card = new CardBuilder().number(Number.SEVEN).build();
		Game game = new Game();
		game.getWaste().push(card);
		int pileNumber = 0;
		game.getPiles().get(pileNumber).pop();
		assertEquals(Error.NO_FIT_PILE, game.moveFromWasteToPile(pileNumber));
		assertEquals(card, game.getWaste().peek());
		assertTrue(game.getPiles().get(pileNumber).empty());
	}
	
	


}
