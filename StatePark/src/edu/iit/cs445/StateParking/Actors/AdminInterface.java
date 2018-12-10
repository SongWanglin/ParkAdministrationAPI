package edu.iit.cs445.StateParking.Actors;

import java.util.ArrayList;

import edu.iit.cs445.StateParking.Objects.Address;
import edu.iit.cs445.StateParking.Objects.ListOfPark;
import edu.iit.cs445.StateParking.Objects.ParkBoundaryInterface;
import edu.iit.cs445.StateParking.Objects.geo;

public interface AdminInterface {
	public int CreatePark(String name, Address a, String web, geo geo);
	public void DeletePark(int PID);
	public ListOfPark ViewAllParks();
	public ParkBoundaryInterface ViewParkDetail(int id);
	public ArrayList<ParkBoundaryInterface> SearchPark(String keyword);

// TO DO: Refactor some methods from mock database to different actors
}
