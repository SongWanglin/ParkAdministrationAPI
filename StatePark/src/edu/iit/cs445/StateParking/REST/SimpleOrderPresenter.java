package edu.iit.cs445.StateParking.REST;

import java.text.SimpleDateFormat;


import edu.iit.cs445.StateParking.Objects.*;
import edu.iit.cs445.StateParking.ObjectsEnum.VehicleType;

public class SimpleOrderPresenter {
	public String oid;
	public String pid;
	public String date;
	public VehicleType type;
	public double amount;
	public SimpleOrderPresenter(Order order) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String day = format.format(order.getOrderDate());
		this.oid = Integer.toString(order.getId());
		this.pid = "0";
		this.date = day;
		this.type = order.getVehicle().getType();
		this.amount = order.getFee();
	}
	public void setPid(int Pid) {
		this.pid = Integer.toString(Pid);
	}
	
}
