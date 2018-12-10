package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;
import edu.iit.cs445.StateParking.ObjectsEnum.*;

public class ParkTest {
	Address address = new Address("8763 E. Canyon Rd", "Apple River", State.IL, "61001");
	geo geo = new geo(42.01, 72.88);
	private ParkBoundaryInterface park = new Park("ASCII", address, "https://", geo);
	private ParkBoundaryInterface park2 = new Park("ASCII", address, "https://", geo);
	@Test
	public void test() {
		assertEquals(park2.getPid()-1,park.getPid());
		park.setName("Area 51");
		assertEquals(park.getName(), "Area 51");
		park.setRegion("North");
		assertEquals(park.getRegion(),"North");
		park.setAddress(address);
		assertTrue(park.getAddress().equals(address));
		park.setWeb("https://www.dnr.illinois.gov/Parks/Pages/AppleRiverCanyon.aspx");
		assertEquals(park.getWeb(),"https://www.dnr.illinois.gov/Parks/Pages/AppleRiverCanyon.aspx");
		park.setPhone("3121231");
		park.setGeo(20, 40);
		assertEquals(park.getGeo().getLat(), 20, 0.0000000001);
		assertEquals(park.getGeo().getLng(), 40, 0.0000000001);
		assertEquals(park.getPhone(),"3121231");
		 assertTrue(park.KeywordMatch("Apple"));
		 assertFalse(park.isNull());	
		park.updateExistingFee(VehicleType.motorcycle, 2.5, 4.0);
		double p1 = park.getAdmFee().get(VehicleType.motorcycle).get(0);
		assertEquals(p1, 2.5,0.00000000001);
		double p2 = park.getAdmFee().get(VehicleType.motorcycle).get(1);
		assertEquals(p2,4.0,0.00000000001);
		double p3 = park.getAdmFee().get(VehicleType.car).get(0);
		assertEquals(p3,7.0,0.00000000001);
		double p4 = park.getAdmFee().get(VehicleType.car).get(1);
		assertEquals(p4,9.0,0.00000000001);
		double p5 = park.getAdmFee().get(VehicleType.rv).get(0);
		assertEquals(p5,10.0,0.00000000001);
		double p6 = park.getAdmFee().get(VehicleType.rv).get(1);
		assertEquals(p6,13.0,0.00000000001);
		park.removeSupportVehicleType(VehicleType.motorcycle);
		assertFalse(park.getAdmFee().containsKey(VehicleType.motorcycle));
	}
	
}
