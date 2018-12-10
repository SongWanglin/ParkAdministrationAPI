package edu.iit.cs445.StateParking.Actors;

import java.util.ArrayList;

import edu.iit.cs445.StateParking.Objects.*;

public interface ParkRanger {
	public int CreateOrder(int pid, int vid, Vehicle bike, CreditCard card);
	public ArrayList<Order> ViewAllOrder();
	public Order ViewOrder(int oid) ;
	public ArrayList<Order> SearchOrder(String keyword);
	//To Do, move some methods in MockDatabase to different actors, 
}
