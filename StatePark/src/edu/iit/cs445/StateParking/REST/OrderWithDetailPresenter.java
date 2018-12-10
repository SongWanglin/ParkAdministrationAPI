package edu.iit.cs445.StateParking.REST;

import java.text.SimpleDateFormat;

import edu.iit.cs445.StateParking.Objects.Order;

public class OrderWithDetailPresenter {
	public String oid;
	public String pid;
	public String vid;
	public String date;
	public double amount;
	public visitor visitor;
	public payment_processing payment_processing;
	public OrderWithDetailPresenter(Order order) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String day = format.format(order.getOrderDate());
		this.oid = Integer.toString(order.getId());
		this.pid = "0";
		this.date = day;
		this.amount = order.getFee();
		this.vid = "0";
		this.visitor = new visitor(order);
		this.payment_processing = new payment_processing(order);
	}
	public void setPid(int Pid) {
		this.pid = Integer.toString(Pid);
	}
	public void setVid(int Vid) {
		this.vid = Integer.toString(Vid);
	}
	
}
