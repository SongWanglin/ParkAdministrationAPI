package edu.iit.cs445.StateParking.Objects;

import java.util.Date;

public class NullOrder implements Order{
	private static NullOrder instance = null;
	private NullOrder() {}
	public static Order getinstance() {
		if(instance == null)
			instance = new NullOrder();
		return instance;
	}
	@Override
	public int getId() {
		return -1;
	}
	@Override
	public Date getOrderDate() {
		return null;
	}
	@Override
	public Vehicle getVehicle() {
		return null;
	}
	@Override
	public CreditCard getCard() {
		return null;
	}
	@Override
	public double getFee() {
		return -1;
	}
	@Override
	public void setFee(double fee) {
		
	}
	@Override
	public boolean KeywordMatch(String keyword) {
		return false;
	}
	@Override
	public boolean isNull() {
		return true;
	}
}
