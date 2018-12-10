package edu.iit.cs445.StateParking.REST;
import java.util.*;

import edu.iit.cs445.StateParking.Objects.*;
import edu.iit.cs445.StateParking.ObjectsEnum.*;

public class ParkPresenterWithGeo {
	public String pid;
	public location_info location_info;
	public HashMap<String, ArrayList<Double>> payment_info;
	public ParkPresenterWithGeo(ParkBoundaryInterface park) {
		this.pid = Integer.toString(park.getPid());
		this.location_info = new location_info(park);
		this.payment_info = setPayment(park);
	}
	private HashMap<String, ArrayList<Double>> setPayment(ParkBoundaryInterface park) {
		HashMap<VehicleType, ArrayList<Double>> P = park.getAdmFee();
		HashMap<String, ArrayList<Double>> result = new HashMap<String, ArrayList<Double>>();
		Iterator<VehicleType> it = P.keySet().iterator();
		String type;
		ArrayList<Double> price;
		while(it.hasNext()) {
			VehicleType a = it.next();
			price = P.get(a);
			type = a.toString();
			result.put(type, price);
		}
		return result;
	}
	public class location_info{
		public String name;
		public String address;
		public String phone;
		public String web;
		public String region;
		public geo geo;
		public location_info(ParkBoundaryInterface park) {
			this.name = park.getName();
			String ad = park.getAddress().getStreet()+","+
					park.getAddress().getCity()+", "+park.getAddress().getState().toString()+
					" "+park.getAddress().getZipcode();
			this.address = ad;
			this.phone = park.getPhone();
			this.web = park.getWeb();
			this.geo = park.getGeo();
			this.region = park.getRegion();
		}

	}
}
