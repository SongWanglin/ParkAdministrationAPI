package edu.iit.cs445.StateParking.REST;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.iit.cs445.StateParking.Objects.Order;

public class payment_processing {
	public String card_transaction_id;
	public String date_and_time;
	public payment_processing(Order order) {
		card_transaction_id = Integer.toString(order.getId()+123456789);
		Date date = order.getOrderDate();
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'.MSZ'");
		this.date_and_time = format.format(date);
	}
}
