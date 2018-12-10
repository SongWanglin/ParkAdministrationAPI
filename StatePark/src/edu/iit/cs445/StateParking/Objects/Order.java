package edu.iit.cs445.StateParking.Objects;

import java.util.Date;

public interface Order {
	public int getId();
	public Date getOrderDate();
	public Vehicle getVehicle();
	public CreditCard getCard();
	public double getFee();
	public void setFee(double fee);
	public boolean KeywordMatch(String keyword);
	public boolean isNull();
}