package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

import edu.iit.cs445.StateParking.Objects.*;
import edu.iit.cs445.StateParking.ObjectsEnum.*;

public class NullParkTest {
	private ParkBoundaryInterface park = NullPark.getinstance();


	@Test
	public void test() {
		assertEquals(park.getPid(),-1);
		park.setName("Area 51");
		assertEquals(park.getName(), null);
		park.setRegion("North");
		assertEquals(park.getRegion(),null);
		Address address = new Address("8763 E. Canyon Rd", "Apple River", State.IL, "61001");
		park.setAddress(address);
		assertEquals(park.getAddress(),null);
		park.setWeb("https://www.dnr.illinois.gov/Parks/Pages/AppleRiverCanyon.aspx");
		assertEquals(park.getWeb(),null);
		park.setPhone("123");
		assertEquals(park.getPhone(),null);
		 park.setGeo(30, 30);
		 assertEquals(park.getGeo(), null);
		 park.updateExistingFee(VehicleType.car, 2.5, 4.0);
		 park.removeSupportVehicleType(VehicleType.motorcycle);
		 assertEquals(park.getAdmFee(),null);
		 assertFalse(park.KeywordMatch(" "));
		 assertTrue(park.isNull());
	}
}
