package klondike.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class StockTest {

	private static final int NUMBER_CARDS = Number.values().length * Suit.values().length;
	@Test
	public void testEmpty() {
		Stock stock = new Stock();
		assertFalse(stock.empty());
		stock.takeTop(StockTest.NUMBER_CARDS);
		assertTrue(stock.empty());
	}

	@Test
	public void testPush() {
		Stock stock = new Stock();
		Card card = new CardBuilder().build();
		stock.push(card);
		assertEquals(card, stock.peek());
	}

	@Test
	public void testPop() {
		Stock stock = new Stock();
		Card card = stock.peek();
		assertEquals(card, stock.pop());
	}
	
	@Test
	public void testStock() {
		Stock stock = new Stock();
		List<Card> cards = stock.takeTop(Number.values().length * Suit.values().length);
		int[] numbers = new int[Number.values().length];
		int[] suites = new int[Suit.values().length];
		for(Card card : cards) {
			numbers[card.getNumber().ordinal()]++;
			suites[card.getSuit().ordinal()]++;
		}
		for(int cont : numbers) {
			assertEquals(Suit.values().length, cont);
		}
		for(int cont : suites) {
			assertEquals(Number.values().length, cont);
		}
	}
	
	@Test
	public void testTakeTop(){
		Stock stock = new Stock();
		List<Card> cardList = stock.takeTop(1);
		assertEquals(1, cardList.size());
		int remainder = StockTest.NUMBER_CARDS -1;
		cardList = stock.takeTop(remainder);
		assertEquals(remainder, cardList.size());
		assertTrue(stock.empty());
	}
	
}
