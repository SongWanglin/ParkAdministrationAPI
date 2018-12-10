package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;
import edu.iit.cs445.StateParking.ObjectsEnum.*;
public class OrderObjTest {
	private Vehicle Batcar = new Vehicle(State.NY, VehicleType.car, "BAT-I GOTHAM CITY");
	private CreditCard card = new CreditCard("373456789074007","Jane Smith","05/23","94102");
	private Order order = new OrderObj(Batcar, card);
	private Order order2 = new OrderObj(Batcar, card);
	Date date = new Date();
	@Test
	public void test() {
		assertEquals(order.getId(), order2.getId()-1);
		assertTrue(order.getOrderDate().equals(date));
		assertTrue(order.getVehicle().equals(Batcar));
		assertTrue(order.getCard().equals(card));
		order.setFee(10);
		assertTrue(order.getFee() == 10);
		assertFalse(order.isNull());
		assertTrue(order.KeywordMatch("Jane"));
		assertFalse(order.KeywordMatch("Joker"));
	}

}
