package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.CreditCard;
import edu.iit.cs445.StateParking.Objects.MockCCprocessor;

public class MockCCprocessorTest {
	private MockCCprocessor CC = MockCCprocessor.getInstance();
	private String day1 = "05/23", day2 = "05/13", day3 = "15/13";
	private CreditCard card = new CreditCard("373456789074007","Jane Smith",day1,"94102");
	private CreditCard card2 = new CreditCard("373456789","Jane Smith",day2,"94102");
	@Test
	public void test() {
		assertTrue(CC.ExpDate(day1)!=null);
		assertEquals(CC.ExpDate(day3), null);
		assertTrue(CC.NotExpired(day1));
		assertFalse(CC.NotExpired(day2));
		assertTrue(CC.validType(card));
		assertFalse(CC.validType(card2));
		assertTrue(CC.validate(card));
		assertFalse(CC.validate(card2));
	}

}
