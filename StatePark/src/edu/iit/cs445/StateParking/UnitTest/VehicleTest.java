package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.Vehicle;
import edu.iit.cs445.StateParking.ObjectsEnum.State;
import edu.iit.cs445.StateParking.ObjectsEnum.VehicleType;

public class VehicleTest {
	private Vehicle Batcar = new Vehicle(State.NY, VehicleType.car, "BAT-I GOTHAM CITY");
	@Test
	public void test() {
		assertEquals(Batcar.getLicensePlate(),"BAT-I GOTHAM CITY");
		assertEquals(Batcar.getState(),State.NY);
		assertEquals(Batcar.getType(),VehicleType.car);
		assertEquals(Batcar.toString(),"NY car BAT-I GOTHAM CITY");
	}

}
