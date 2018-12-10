package edu.iit.cs445.StateParking.Objects;

import java.util.*;

import edu.iit.cs445.StateParking.ObjectsEnum.VehicleType;

public interface ParkBoundaryInterface {
	public int getPid();	
	public String getName();
	public void setName(String name) ;
	public String getRegion();
	public void setRegion(String region) ;
	public Address getAddress();
	public void setAddress(Address address);
	public String getPhone() ;
	public void setPhone(String phone);
	public String getWeb() ;
	public void setWeb(String web);
	public geo getGeo();
	public void setGeo(double lat, double lng) ;
	public HashMap<VehicleType, ArrayList<Double>> getAdmFee() ;
	public void updateExistingFee(VehicleType type, double instate, double outstate) ;
	public void removeSupportVehicleType(VehicleType type);
	public boolean KeywordMatch(String keyword);
	public boolean isNull() ;
}
 