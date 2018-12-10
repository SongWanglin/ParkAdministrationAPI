package edu.iit.cs445.StateParking.REST;


import edu.iit.cs445.StateParking.Objects.Order;

public class visitor {
	public String name;
	public String email;
	public payment_info payment_info;
	public visitor(Order order) {
		this.name = "";
		this.email = "";
		this.payment_info = new payment_info(order);
	}
	public void setName (String name) {
		this.name = name;
	}
	public void setEmail (String email) {
		this.email = email;
	}
	public class payment_info{
		public String card;
		public String name_on_card;
		public String expiration_date;
		public int zip;
		public payment_info(Order order) {
			String number = order.getCard().getCardNumber();
			String mosaic = new String(new char[number.length()-4]).replace("\0", "x");
			this.card =  mosaic + number.substring(number.length()-4, number.length());
			this.name_on_card = order.getCard().getName_on_card();
			this.expiration_date = order.getCard().getExpirationData();
			this.zip = Integer.parseInt(order.getCard().getZip());
		}
	}
}
