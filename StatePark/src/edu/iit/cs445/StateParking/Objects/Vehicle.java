package edu.iit.cs445.StateParking.Objects;

import edu.iit.cs445.StateParking.ObjectsEnum.*;

public class Vehicle {
	private State state;
	private VehicleType type;
	private String LicensePlate;
	public Vehicle(State a, VehicleType b, String c) {
		this.state = a;
		this.type = b;
		this.LicensePlate = c;
	}
	public State getState() {
		return state;
	}

	public VehicleType getType() {
		return type;
	}

	public String getLicensePlate() {
		return LicensePlate;
	}
	public String toString() {
		return this.state.toString()+" "+this.type.toString()+" "+this.LicensePlate;
	}
}
