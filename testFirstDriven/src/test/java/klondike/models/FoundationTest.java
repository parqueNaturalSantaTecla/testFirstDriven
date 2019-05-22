package klondike.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FoundationTest {

	@Test
	public void testEmpty() {
		assertTrue(new FoundationBuilder().build().empty());
	}

	@Test
	public void testNotEmpty() {
		Foundation foundation = new FoundationBuilder().suit(Suit.HEARTS)
				.card(new CardBuilder().suit(Suit.HEARTS).build()).build();
		assertFalse(foundation.empty());
	}

	@Test
	public void testPush() {
		Waste waste = new Waste();
		Card card = new CardBuilder().build();
		waste.push(card);
		assertEquals(card, waste.peek());
	}

	@Test
	public void testPop() {
		Waste waste = new Waste();
		Card card = new CardBuilder().build();
		waste.push(card);
		assertEquals(card, waste.pop());
		assertTrue(waste.empty());
	}
	
	@Test 
	public void testIsCompleted() {
		assertTrue(new FilledFoundationBuilder().number(Number.KING).build().isComplete());
	}
	
	@Test 
	public void testIsNotCompleted() {
		Foundation foundation = new FilledFoundationBuilder().number(Number.KING).build();
		foundation.pop();
		assertFalse(foundation.isComplete());
	}

	@Test 
	public void testFitsInEmptyWithAs() {
		Suit suit = Suit.DIAMONDS;
		Foundation foundation = new FoundationBuilder().suit(suit).build();
		assertTrue(foundation.fitsIn(new CardBuilder().number(Number.AS).suit(suit).build()));
	}
	
	@Test 
	public void testFitsInEmptyWithAsWrongSuit() {
		Foundation foundation = new FoundationBuilder().suit(Suit.DIAMONDS).build();
		assertFalse(foundation.fitsIn(new CardBuilder().number(Number.AS).suit(Suit.CLOVERS).build()));
	}
	
	@Test 
	public void testFitsInEmptyWithWrongNumber() {
		Suit suit = Suit.DIAMONDS;
		Foundation foundation = new FoundationBuilder().suit(suit).build();
		assertFalse(foundation.fitsIn(new CardBuilder().number(Number.QUEEN).suit(suit).build()));
	}
	
	@Test 
	public void testFitsInNotEmptyWithWrongNumber() {
		Suit suit = Suit.DIAMONDS;
		Foundation foundation = new FoundationBuilder().suit(suit).build();
		foundation.push(new CardBuilder().number(Number.AS).suit(suit).build());
		foundation.push(new CardBuilder().number(Number.TWO).suit(suit).build());
		assertFalse(foundation.fitsIn(new CardBuilder().number(Number.FIVE).suit(suit).build()));
	}
	
	@Test 
	public void testFitsInNotEmptyWithWrongSuit() {
		Suit suit = Suit.DIAMONDS;
		Foundation foundation = new FoundationBuilder().suit(suit).build();
		foundation.push(new CardBuilder().number(Number.AS).suit(suit).build());
		foundation.push(new CardBuilder().number(Number.TWO).suit(suit).build());
		assertFalse(foundation.fitsIn(new CardBuilder().number(Number.THREE).suit(Suit.CLOVERS).build()));
	}
	
	@Test 
	public void testFitsInWithNotEmpty() {
		Suit suit = Suit.DIAMONDS;
		Foundation foundation = new FoundationBuilder().suit(suit).build();
		foundation.push(new CardBuilder().number(Number.AS).suit(suit).build());
		foundation.push(new CardBuilder().number(Number.TWO).suit(suit).build());
		assertTrue(foundation.fitsIn(new CardBuilder().number(Number.THREE).suit(suit).build()));
	}
}
