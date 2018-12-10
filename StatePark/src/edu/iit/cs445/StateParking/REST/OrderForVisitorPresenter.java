package edu.iit.cs445.StateParking.REST;

import java.text.SimpleDateFormat;

import edu.iit.cs445.StateParking.Objects.Order;

public class OrderForVisitorPresenter {
	public String oid;
	public String pid;
	public String date;
	public OrderForVisitorPresenter(Order order) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String day = format.format(order.getOrderDate());
		this.oid = Integer.toString(order.getId());
		this.pid = "0";
		this.date = day;
	}
	public void setPid(int Pid) {
		this.pid = Integer.toString(Pid);
	}
}