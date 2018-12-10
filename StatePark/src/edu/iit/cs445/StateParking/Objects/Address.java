package edu.iit.cs445.StateParking.Objects;
import edu.iit.cs445.StateParking.ObjectsEnum.*;

public class Address {
	private String street;
	private String city;
	private State state;
	private String zipcode;
	
	public Address (String street, String city, State state, String zipcode) {		
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public State getState() {
		return state;
	}

	public String getZipcode() {
		return zipcode;
	}
	
	public String toString() {
		return street+","+city+","+state+" "+zipcode;
	}
	public boolean isMatch(String key) {
		if (this.toString().matches("(.*)"+key+"(.*)"))
				return true;
		return false;
	}
}
