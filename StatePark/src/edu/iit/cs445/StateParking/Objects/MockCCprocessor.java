package edu.iit.cs445.StateParking.Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.iit.cs445.StateParking.ObjectsEnum.CardType;

public class MockCCprocessor {
	private static MockCCprocessor instance = null;
	private MockCCprocessor() {}
	public static MockCCprocessor getInstance() {
		if(instance == null) {
			instance = new MockCCprocessor();
		}
			return instance;
	}	
	public boolean validate (CreditCard card) {
		String date = card.getExpirationData();
		return (ExpDate(date)!=null&&NotExpired(date)&&validType(card));
	}
	public  Date ExpDate (String day) {
		SimpleDateFormat format = new SimpleDateFormat("MM/yy");
		format.setLenient(false);
		Date date = new Date();
		try {
			date = format.parse(day);
		}catch(ParseException e) { 
			return null;
			}
		return date;
	}
	public boolean NotExpired (String day) {
		Date CurrentDate = new Date();
		Date date = ExpDate(day);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		date = c.getTime();
		boolean valid = date.after(CurrentDate);
		return valid;
	}
	public boolean validType(CreditCard card) {
		return (CardType.detect(card.getCardNumber()) != CardType.UNKNOWN);
	}
}
