package klondike.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class PileTest {

	@Test
	public void testEmpty() {
		Card card = new CardBuilder().build();
		Pile pile = new PileBuilder().card(card).build();
		pile.pop();
		assertTrue(pile.empty());
	}

	@Test
	public void testPush() {
		Pile pile = new PileBuilder().card(new CardBuilder().number(Number.SIX).suit(Suit.HEARTS).build()).build();
		Card card = new CardBuilder().number(Number.FIVE).suit(Suit.CLOVERS).build();
		pile.push(card);
		assertEquals(card, pile.peek());
	}

	@Test
	public void testPop() {
		Card card = new CardBuilder().build();
		Pile pile = new PileBuilder().card(card).build();
		assertEquals(card, pile.pop());
	}
	
	@Test
	public void testFitsInEmpty() {
		CardBuilder cardBuilder = new CardBuilder();
		Pile pile = new PileBuilder().card(cardBuilder.build()).build();
		pile.pop();
		assertTrue(pile.fitsIn(cardBuilder.number(Number.KING).build()));
		assertFalse(pile.fitsIn(cardBuilder.number(Number.QUEEN).build()));
	}
	
	@Test
	public void testFitsInNotEmpty() {
		CardBuilder cardBuilder = new CardBuilder();
		Pile pile = new PileBuilder().card(cardBuilder.number(Number.SEVEN).suit(Suit.CLOVERS).build()).build();
		pile.push(cardBuilder.number(Number.SIX).suit(Suit.HEARTS).build());
		assertTrue(pile.fitsIn(cardBuilder.number(Number.FIVE).suit(Suit.PIKES).build()));
		assertFalse(pile.fitsIn(cardBuilder.number(Number.FOUR).suit(Suit.PIKES).build()));
		assertFalse(pile.fitsIn(cardBuilder.number(Number.FIVE).suit(Suit.DIAMONDS).build()));
		assertFalse(pile.fitsIn(cardBuilder.number(Number.SEVEN).suit(Suit.CLOVERS).build()));
	}
	
	@Test
	public void testGetTopOne() {
		CardListBuilder cardListBuilder = new CardListBuilder();
		cardListBuilder.card(new CardBuilder().number(Number.AS).suit(Suit.DIAMONDS).facedUp().build());
		Pile pile = new PileBuilder().card(new CardBuilder().number(Number.TWO).suit(Suit.PIKES).build()).build();
		this.testGetTop(cardListBuilder.build(), pile);
	}
	
	@Test
	public void testGetTopMany() {
		CardListBuilder cardListBuilder = new CardListBuilder();
		cardListBuilder.card(new CardBuilder().number(Number.NINE).suit(Suit.CLOVERS).facedUp().build());
		cardListBuilder.card(new CardBuilder().number(Number.EIGHT).suit(Suit.DIAMONDS).facedUp().build());
		cardListBuilder.card(new CardBuilder().number(Number.SEVEN).suit(Suit.PIKES).facedUp().build());
		Pile pile = new PileBuilder().card(new CardBuilder().number(Number.TEN).suit(Suit.DIAMONDS).build()).build();
		this.testGetTop(cardListBuilder.build(), pile);
	}
	
	private void testGetTop(List<Card> cards, Pile pile) {
		for(Card card : cards) {
			pile.push(card);
		}
		assertEquals(cards, pile.getTop(cards.size()));
	}
	
	@Test
	public void testAddToTop() {
		CardListBuilder cardListBuilder = new CardListBuilder();
		cardListBuilder.card(new CardBuilder().number(Number.NINE).suit(Suit.CLOVERS).facedUp().build());
		cardListBuilder.card(new CardBuilder().number(Number.EIGHT).suit(Suit.DIAMONDS).facedUp().build());
		cardListBuilder.card(new CardBuilder().number(Number.SEVEN).suit(Suit.PIKES).facedUp().build());
		List<Card> cards = cardListBuilder.build();
		Card card = new CardBuilder().number(Number.TEN).suit(Suit.DIAMONDS).build();
		Pile pile = new PileBuilder().card(card).build();
		pile.addToTop(cards);
		List<Card> removed = pile.getTop(cards.size()+1);
		cards.add(0, card);
		assertEquals(cards, removed);
	}
	
	@Test
	public void testRemoveTop() {
		CardListBuilder cardListBuilder = new CardListBuilder();
		cardListBuilder.card(new CardBuilder().number(Number.NINE).suit(Suit.CLOVERS).facedUp().build());
		cardListBuilder.card(new CardBuilder().number(Number.EIGHT).suit(Suit.DIAMONDS).facedUp().build());
		cardListBuilder.card(new CardBuilder().number(Number.SEVEN).suit(Suit.PIKES).facedUp().build());
		List<Card> cards = cardListBuilder.build();
		Card card = new CardBuilder().number(Number.TEN).suit(Suit.DIAMONDS).build();
		Pile pile = new PileBuilder().card(card).build();
		pile.addToTop(cards);
		pile.removeTop(cards.size()+1);
		assertTrue(pile.empty());
	}
	
}
