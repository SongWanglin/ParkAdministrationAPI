package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.CreditCard;
import edu.iit.cs445.StateParking.ObjectsEnum.CardType;

public class CreditCardTest {
	private CreditCard card = new CreditCard("373456789074007","Jane Smith","05/23","94102");
	@Test
	public void test() {
		assertTrue("373456789074007".equals(card.getCardNumber()));
		assertFalse("3734569074007".equals(card.getCardNumber()));
		
		assertTrue(card.getType().equals(CardType.AMERICAN_EXPRESS) );
		assertFalse(card.getType().equals(CardType.VISA) );
		
		assertTrue("Jane Smith".equals(card.getName_on_card()));
		assertFalse("Javascript".equals(card.getName_on_card()));
		assertTrue("05/23".equals(card.getExpirationData()));
		assertFalse("15/23".equals(card.getExpirationData()));
		assertTrue("94102".equals(card.getZip()));
		assertFalse("941".equals(card.getZip()));
	}

}
