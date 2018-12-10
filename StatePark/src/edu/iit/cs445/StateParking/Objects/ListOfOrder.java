package edu.iit.cs445.StateParking.Objects;

import java.util.*;

public class ListOfOrder {
	private static ListOfOrder instance = null;
	private ArrayList<Order> Orders;
	
	private ListOfOrder() {
		Orders = new ArrayList<Order>();
	}
	
	public static ListOfOrder getInstance() {
		if(instance == null) {
			instance = new ListOfOrder();
		}
		return instance;
	}
	
	public ArrayList<Order> viewAll(){
		return Orders;
	}
	
	public int createOrder(Vehicle bike, CreditCard card) 
		throws RuntimeException {
		MockCCprocessor CCvalidate = MockCCprocessor.getInstance();
		if(CCvalidate.validate(card) == false) {
			throw new RuntimeException("Invalid Credit Card!");
		}
		Order order = new OrderObj(bike, card);	
		Orders.add(order);
		return order.getId();
	}
	
	public Order SearchByID(int id) {
		Iterator<Order> it = Orders.iterator();
		while(it.hasNext()) {
			Order order = it.next();
			if(order.getId() == id) {
				return order;
			}
		}
		return NullOrder.getinstance();
	}
	
	public void SetFee(int id, Double double1) {
		Iterator<Order> it = Orders.iterator();
		int index;
		while(it.hasNext()) {
			Order order = it.next();
			if(order.getId() == id) {
				index = Orders.indexOf(order);
				order.setFee(double1);
				Orders.set(index, order);
			}
		}
	}
	
	public ArrayList<Order> SearchByKeyWord(String keyword) {
		Iterator<Order> it = Orders.iterator();
		ArrayList<Order> matchList = new ArrayList<Order>();
		while(it.hasNext()) {
			Order order = it.next();
			if(order.KeywordMatch(keyword) == true) {
				matchList.add(order);
			}
		}
		return matchList;
	}
	
	public List<Order> GetOrderWithin(Date start, Date end){
		List<Order> output = new ArrayList<Order>();
		Iterator<Order> it = Orders.iterator();
		while(it.hasNext()) {
			Order order = it.next();
			if(order.getOrderDate().after(start) && order.getOrderDate().before(end))
				output.add(order);
		}
		return output;
	}
}
