package klondike.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SuitTest {

	private String initials = "HDCP";

	@Test
	public void testInitials() {
		char[] initials = Suit.initials();
		for (int i = 0; i < this.initials.length(); i++) {
			assertEquals(this.initials.charAt(i), initials[i]);
		}
	}

	@Test
	public void testFind() {
		for (int i = 0; i < this.initials.length(); i++) {
			assertEquals(Suit.values()[i], Suit.find(this.initials.charAt(i)));
		}
	}
	
	@Test
	public void testFindNull() {
		assertEquals(null, Suit.find(' '));
	}

}
