package edu.iit.cs445.StateParking.Objects;

import java.util.ArrayList;
import java.util.HashMap;

import edu.iit.cs445.StateParking.ObjectsEnum.VehicleType;

public class NullPark implements ParkBoundaryInterface {
	private static NullPark instance = null;
	private NullPark() {}
	public static ParkBoundaryInterface getinstance() {
		if(instance == null)
			instance = new NullPark();
		return instance;
	}
	@Override
	public int getPid() {
		return -1;
	}
	@Override
	public String getName() {
		return null;
	}
	@Override
	public void setName(String name) {}
	@Override
	public String getRegion() {return null;}
	@Override
	public void setRegion(String region) {} 
	@Override
	public Address getAddress() {return null;}
	@Override
	public void setAddress(Address address) {}
	@Override
	public String getPhone() {return null;}
	@Override
	public void setPhone(String phone) {}
	@Override
	public String getWeb() {return null;}
	@Override
	public void setWeb(String web) {}
	@Override
	public geo getGeo() {return null;}
	@Override
	public void setGeo(double lat, double lng) {}
	@Override
	public HashMap<VehicleType, ArrayList<Double>> getAdmFee() {return null;}
	@Override
	public void updateExistingFee(VehicleType type, double instate, double outstate) {}
	@Override
	public void removeSupportVehicleType(VehicleType type) {}
	@Override
	public boolean KeywordMatch(String keyword) {return false;};
	@Override
	public boolean isNull() {
		return true;
	}
}
