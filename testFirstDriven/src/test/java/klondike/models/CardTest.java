package klondike.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CardTest {

	@Test
	public void testFlip() {
		Card card = new CardBuilder().build();
		assertFalse(card.isFacedUp());
		card.flip();
		assertTrue(card.isFacedUp());
		card.flip();
		assertFalse(card.isFacedUp());
	}

	@Test
	public void testIsNextTo() {
		this.isNextTo(Number.TWO , Number.AS);
		this.isNextTo(Number.KING, Number.QUEEN);
	}

	void isNextTo(Number firstNumber, Number secondNumber) {
		Card firstCard = new CardBuilder().number(firstNumber).build();
		Card secondCard = new CardBuilder().number(secondNumber).build();
		assertTrue(firstCard.isNextTo(secondCard));
	}
	
	@Test
	public void testIsNotNextTo() {
		this.isNotNextTo(Number.AS, Number.TWO);
		this.isNotNextTo(Number.AS, Number.KING);
	}

	void isNotNextTo(Number firstNumber, Number secondNumber) {
		Card firstCard = new CardBuilder().number(firstNumber).build();
		Card secondCard = new CardBuilder().number(secondNumber).build();
		assertFalse(firstCard.isNextTo(secondCard));
	}

}
