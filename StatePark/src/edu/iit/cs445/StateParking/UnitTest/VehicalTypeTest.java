package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.ObjectsEnum.CardType;
import edu.iit.cs445.StateParking.ObjectsEnum.VehicleType;

public class VehicalTypeTest {

	@Test
	public void test() {
		assertEquals(VehicleType.motorcycle, VehicleType.valueOf("motorcycle"));
		assertEquals(VehicleType.bus, VehicleType.valueOf("bus"));
		assertEquals(VehicleType.car, VehicleType.valueOf("car"));
		assertEquals(VehicleType.van, VehicleType.valueOf("van"));
		assertEquals(VehicleType.rv, VehicleType.valueOf("rv"));
	}

}
