package klondike.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WasteTest {

	@Test
	public void testEmpty() {
		Waste waste = new Waste();
		assertTrue(waste.empty());
		waste.push(new CardBuilder().build());
		assertFalse(waste.empty());
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
	
}
