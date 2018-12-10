package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.Address;
import edu.iit.cs445.StateParking.ObjectsEnum.State;

public class AddressTest {
	private Address address = new Address("8763 E. Canyon Rd", "Apple River", State.IL, "61001");
	@Test
	public void test() {
		assertEquals("8763 E. Canyon Rd",address.getStreet());
		assertEquals("Apple River",address.getCity());
		assertEquals(State.IL,address.getState());
		assertEquals("61001",address.getZipcode());
		assertEquals("8763 E. Canyon Rd,Apple River,IL 61001",address.toString());
		assertTrue(address.isMatch("Apple River"));
		assertFalse(address.isMatch("Object Oriented Design"));
	}
}
