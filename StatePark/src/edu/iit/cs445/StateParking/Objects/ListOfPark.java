package edu.iit.cs445.StateParking.Objects;

import java.util.*;

import edu.iit.cs445.StateParking.ObjectsEnum.VehicleType;

public class ListOfPark {
	private static ListOfPark instance = null;
	private ArrayList<ParkBoundaryInterface> Parks;
	
	private ListOfPark() {
		Parks = new ArrayList<ParkBoundaryInterface>();
	}
	
	public static ListOfPark getInstance() {
		if(instance == null) {
			instance = new ListOfPark();
		}
		return instance;
	}
	
	public ArrayList<ParkBoundaryInterface> ViewAll() {
		return Parks;
	}
	
	public int createPark(String name, Address a, String web, geo geo) {
		ParkBoundaryInterface park = new Park(name, a, web, geo);
		Parks.add(park);
		return park.getPid();
	}
	
	public int createPark2(ParkBoundaryInterface park) {
		Parks.add(park);
		return park.getPid();
	}
	
	public void updateParkLoc(int Pid,String name, Address a, String web, 
			double lat, double lng, String region, String phone) {
		Iterator<ParkBoundaryInterface> it = Parks.iterator();

		while(it.hasNext()) {
			ParkBoundaryInterface park = it.next();

			if(park.getPid() == Pid) {
				park.setName(name);
				park.setAddress(a);
				park.setWeb(web);
				park.setRegion(region);
				park.setPhone(phone);
				park.setGeo(lat, lng);

			}
		}
	}
	
	public void updatePark(int Pid,VehicleType type, double instate, double outstate) {
		Iterator<ParkBoundaryInterface> it = Parks.iterator();

		while(it.hasNext()) {
			ParkBoundaryInterface park = it.next();

			if(park.getPid() == Pid) {
				park.updateExistingFee(type, instate, outstate);

			}
		}
	}
	
	public boolean removePark(int Pid) throws RuntimeException{
		Iterator<ParkBoundaryInterface> it = Parks.iterator();
		while(it.hasNext()) {
			ParkBoundaryInterface park = it.next();
			if(park.getPid() == Pid) {
				Parks.remove(park);
				return true;
			}
		}
		throw new RuntimeException("Park not found with given id");
	}
	
	public ParkBoundaryInterface SearchByID(int id) {
		Iterator<ParkBoundaryInterface> it = Parks.iterator();
		while(it.hasNext()) {
			ParkBoundaryInterface park = it.next();
			if(park.getPid() == id) {
				return park;
			}
		}
		return NullPark.getinstance();
	}
	
	public ArrayList<ParkBoundaryInterface> SearchByKeyword(String keyword) {
		Iterator<ParkBoundaryInterface> it = Parks.iterator();
		ArrayList<ParkBoundaryInterface> matchList = new ArrayList<ParkBoundaryInterface>();
		while(it.hasNext()) {
			ParkBoundaryInterface park = it.next();
			if(park.KeywordMatch(keyword)==true) {
				matchList.add(park);
			}
		}
		return matchList;
	}
}
