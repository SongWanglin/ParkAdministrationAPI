package edu.iit.cs445.StateParking.Objects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderObj implements Order {
	private static int count = 0;
	private final int id; 
	private Date OrderDate;
	private Vehicle vehicle;
	private CreditCard card;
	private double fee = 0;
	public OrderObj(Vehicle a, CreditCard b) {
		Date date = new Date();
		this.id = count ++;
		this.OrderDate = date;
		this.vehicle = a;
		this.card = b;
	}
	
	public int getId() {
		return id;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public CreditCard getCard() {
		return card;
	}
	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public boolean KeywordMatch(String keyword) {
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMdd");
		String OrderDay = DateFormat.format(OrderDate);
		boolean DayMatch = OrderDay.matches("(?i)(.*)"+keyword+"(.*)") ;
		boolean CCMatch = (this.card.getName_on_card().matches("(?i)(.*)"+keyword+"(.*)"))||
					(this.card.getCardNumber().matches("(?i)(.*)"+keyword+"(.*)")) ;
		boolean VehicleMatch = vehicle.toString().matches("(?i)(.*)"+keyword+"(.*)");
		return DayMatch||CCMatch||VehicleMatch;
	}
	public boolean isNull() {
		return false;
	}
	
}
