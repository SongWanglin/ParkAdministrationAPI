package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.ObjectsEnum.CardType;

public class CardTypeTest {

	@Test
	public void test() {
		assertEquals(CardType.VISA, CardType.detect("4929890169289653"));
		assertFalse(CardType.VISA.equals(CardType.detect("492989")));
		assertEquals(CardType.MASTERCARD, CardType.detect("5213552804229997"));
		assertFalse(CardType.MASTERCARD.equals(CardType.detect("4929890169289653")));
		assertEquals(CardType.AMERICAN_EXPRESS, CardType.detect("340192434539106"));
		assertFalse(CardType.AMERICAN_EXPRESS.equals(CardType.detect("5213552804229997")));
		assertEquals(CardType.UNKNOWN, CardType.detect("1"));
		assertFalse(CardType.UNKNOWN.equals(CardType.detect("5213552804229997")));
	}
}
