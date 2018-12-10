package edu.iit.cs445.StateParking.Objects;
import java.util.*;
import edu.iit.cs445.StateParking.ObjectsEnum.*;

public class Park implements ParkBoundaryInterface{
	private static int count = 0;
	private final int pid; 
	private String name;
	private String region;
	private Address address;
	private String phone;
	private String web;
	private geo geo;
	private HashMap<VehicleType, ArrayList<Double>> AdmFee = DefaultAdmFee();

	public Park(String name, Address a, String web, geo geo) {
		this.pid = count ++;
		this.name = name;
		this.address = a;
		this.web = web;
		this.geo = geo;
	}
	
	public Park(String name, Address a, String web, geo geo, String region, String phone) {
		this.pid = count ++;
		this.name = name;
		this.address = a;
		this.web = web;
		this.geo = geo;
		this.region = region;
		this.phone = phone;
	}
	
	
	private HashMap<VehicleType, ArrayList<Double>> DefaultAdmFee(){
		HashMap<VehicleType, ArrayList<Double>> Adm = new HashMap<VehicleType, ArrayList<Double>>(); 
		ArrayList<Double> a1= new ArrayList<Double>(Arrays.asList(4.0, 5.0)); 
		Adm.put(VehicleType.motorcycle, a1);
		ArrayList<Double> a2= new ArrayList<Double>(Arrays.asList(7.0,9.0));
		Adm.put(VehicleType.car, a2);
		ArrayList<Double> a3= new ArrayList<Double>(Arrays.asList(10.0,13.0));
		Adm.put(VehicleType.rv,a3);
		return Adm;
	}
	
	public int getPid() {
		return pid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public geo getGeo() {
		return geo;
	}

	public void setGeo(double lat, double lng) {
		this.geo = new geo(lat, lng);
	}

	public HashMap<VehicleType, ArrayList<Double>> getAdmFee() {
		return AdmFee;
	}

	public void updateExistingFee(VehicleType type, double instate, double outstate) {
		ArrayList<Double> price= new ArrayList<Double>(Arrays.asList(instate,outstate));
		AdmFee.put(type, price);
	}

	public void removeSupportVehicleType(VehicleType type) {
		AdmFee.remove(type);
	}
	
	public boolean KeywordMatch(String keyword) {
		boolean Name = this.name.matches("(?i)(.*)"+keyword+"(.*)") ;
		boolean Web = this.web.matches("(?i)(.*)"+keyword+"(.*)");
		boolean Region = (this.region == null)?false:(this.region.matches("(?i)(.*)"+keyword+"(.*)"));
		boolean Phone = (this.phone == null)? false: 
			(this.phone.toString().matches("(?i)(.*)"+keyword+"(.*)"));
		boolean Ad = this.address.isMatch(keyword);
		return Name||Web||Region||Phone||Ad;
	}
	
	public boolean isNull() {
		return false;
	}

}
