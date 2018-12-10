package edu.iit.cs445.StateParking.Actors;

import java.util.*;

import edu.iit.cs445.StateParking.Objects.*;

public class Admin implements AdminInterface {
	private MockDatabase DB = MockDatabase.getInstance();
	public Admin() {};
	public int CreatePark(String name, Address a, String web, geo geo) {
		int PID =DB.CreatePark(name, a, web, geo);
		return PID;
	}
	public void DeletePark(int PID) {
		DB.DeletePark(PID);
	}
	public ListOfPark ViewAllParks() {
		return DB.ViewAllParks();
	}
	public ParkBoundaryInterface ViewParkDetail(int id) {
		return DB.ViewParkDetail(id);
	}
	public ArrayList<ParkBoundaryInterface> SearchPark(String keyword) {
		return DB.SearchPark(keyword);
	}
	//To Do, move some methods in MockDatabase to different actors
}
