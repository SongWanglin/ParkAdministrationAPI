package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;
import edu.iit.cs445.StateParking.ObjectsEnum.State;
import edu.iit.cs445.StateParking.ObjectsEnum.VehicleType;

public class ListOfNoteAndOrderTest {
	private ListOfNote Nlist = ListOfNote.getInstance();
	private ListOfOrder Olist = ListOfOrder.getInstance();
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	@Test
	public void test() throws ParseException {
		Date date1= format.parse("19910909");
		Date date2 = format.parse("19891226");
		Date date3 = format.parse("20111212");
		Date date4 = format.parse("20201001");
		Nlist.createNote("a", "b");
		Nlist.createNote("c", "d");
		Vehicle Batcar = new Vehicle(State.NY, VehicleType.car, "BAT-I GOTHAM CITY");
		CreditCard card = new CreditCard("373456789074007","Jane Smith","05/23","94102");
		Olist.createOrder(Batcar, card);
		assertTrue(Nlist.GetNoteWithin(date2, date1).isEmpty());
		assertTrue(!Nlist.GetNoteWithin(date3, date4).isEmpty());
		assertTrue(Olist.GetOrderWithin(date2, date1).isEmpty());
		assertTrue(!Olist.GetOrderWithin(date3, date4).isEmpty());
	}

}
