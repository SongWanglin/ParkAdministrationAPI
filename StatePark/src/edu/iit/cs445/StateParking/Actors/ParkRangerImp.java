package edu.iit.cs445.StateParking.Actors;
import java.util.*;

import edu.iit.cs445.StateParking.Objects.*;
public class ParkRangerImp implements ParkRanger {
	private MockDatabase DB = MockDatabase.getInstance();
	public ParkRangerImp() {	
	}
	public int CreateOrder(int pid, int vid, Vehicle bike, CreditCard card) {
		return DB.CreateOrder(pid, vid, bike, card);
	}
	public ArrayList<Order> ViewAllOrder(){
		return DB.ViewAllOrder();
	}
	public Order ViewOrder(int oid) {
		return DB.ViewOrder(oid);
	}
	public ArrayList<Order> SearchOrder(String keyword){
		return DB.SearchOrder(keyword);
	}
	//TO DO, move some methods in MockDatabase to different actors, 
}
