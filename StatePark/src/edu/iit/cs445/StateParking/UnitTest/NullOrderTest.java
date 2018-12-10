package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;

public class NullOrderTest {
	private Order order = NullOrder.getinstance();
	@Test
	public void test() {
		assertEquals(order.getId(),-1);
		assertEquals(order.getOrderDate(),null);
		assertEquals(order.getVehicle(), null);
		assertEquals(order.getCard(), null);
		order.setFee(100.0);
		assertTrue(order.getFee() ==  -1.0);
		assertFalse(order.KeywordMatch(""));
		assertTrue(order.isNull());
	}

}
