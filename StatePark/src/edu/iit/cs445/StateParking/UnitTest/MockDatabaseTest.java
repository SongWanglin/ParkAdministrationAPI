package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;
import edu.iit.cs445.StateParking.ObjectsEnum.*;

public class MockDatabaseTest {
	private MockDatabase DB = MockDatabase.getInstance();

	@Test
	public void CreateParkTest() {
		Address address = new Address("8763 E. Canyon Rd", "Apple River", State.IL, "61001");
		geo geo = new geo(42.01, 72.88);
		int pid = DB.CreatePark("ASCII", address, "https://", geo);
		int pid2 = DB.CreatePark("ASCII", address, "https://", geo);
		assertEquals(pid, pid2-1);
		DB.DeletePark(pid);
		DB.DeletePark(pid2);
	}
	@Test
	public void GetKeyfromValueTest() {
		HashMap<Integer, ArrayList<Integer>> A = new HashMap<Integer, ArrayList<Integer>>();
		assertEquals(DB.GetKeyfromValue(A, 1), -1);
		ArrayList<Integer> a1 = new ArrayList<Integer>(); a1.add(2); a1.add(3);
		ArrayList<Integer> a2 = new ArrayList<Integer>(); a2.add(5); a1.add(6);
		A.put(1, a1); A.put(4,a2);
		assertEquals(DB.GetKeyfromValue(A, 2), 1);
		assertEquals(DB.GetKeyfromValue(A, 5), 4);
	}
	@Test
	public void DeleteParkTest() {
		Address address = new Address("8763 E. Canyon Rd", "Apple River", State.IL, "61001");
		geo geo = new geo(42.01, 72.88);
		int pid = DB.CreatePark("ASCII", address, "https://", geo);
		DB.DeletePark(pid);
		assertTrue(DB.ViewParkDetail(pid).equals(NullPark.getinstance()));
	}

	@Test
	public void ViewAllParkTest() {
		ListOfPark list = ListOfPark.getInstance();
		assertTrue(DB.ViewAllParks().equals(list));
	}
	@Test
	public void SearchParkTest() {
		assertTrue(DB.SearchPark("Apple").isEmpty());
		Address address = new Address("8763 E. Canyon Rd", "Apple River", State.IL, "61001");
		Address address2 = new Address("8763 E. Canyon Rd", "Apple County", State.IL, "61001");
		geo geo = new geo(42.01, 72.88);
		DB.CreatePark("ASCII", address, "https://orange", geo);
		DB.CreatePark("API", address2, "http://Apple", geo);
		assertEquals(DB.SearchPark("Apple").size(), 2);
	}
	
	@Test
	public void UpdateParkTest() {
		Address address = new Address("8763 E. Canyon Rd", "Apple River", State.IL, "61001");
		geo geo = new geo(42.01, 72.88);
		int pid = DB.CreatePark("Area 51", address, "https://orange", geo);
		DB.UpdatePark(pid, VehicleType.motorcycle, 2.5, 4.0);
		assertEquals(DB.ViewParkDetail(pid).getAdmFee().get(VehicleType.motorcycle).get(0),2.5,0.0000001);
		assertEquals(DB.ViewParkDetail(pid).getAdmFee().get(VehicleType.motorcycle).get(1),4.0,0.0000001);
	}
	@Test
	public void UpdateParkTest2() {
		Address address = new Address("8763 E. Canyon Rd", "Apple River", State.IL, "61001");
		geo geo = new geo(42.01, 72.88);
		int pid = DB.CreatePark("Area 51", address, "https://orange", geo);
		try {
			DB.UpdatePark(pid, VehicleType.motorcycle, -2.5, 4.0);
			fail("Should throw exception");
		}catch(RuntimeException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void VisitorActionTest() {
		Date date = new Date();
		
		Address address = new Address("8763 E. Canyon Rd", "Apple River", State.IL, "61001");
		geo geo = new geo(42.01, 72.88);
		int pid = DB.CreatePark("Area 51", address, "https://orange", geo);
		assertTrue(DB.ViewAllVisitor().isEmpty());
		assertTrue(DB.ViewAllOrder().isEmpty());
		assertTrue(DB.ViewAllNotesOfThePark(pid).isEmpty());
		
		int vid1 = DB.NewVisitor("John Doe","john.doe@example.com");
		int vid2 = DB.NewVisitor("Tom cat","TomAndJerry123@gmail.com");
		assertEquals(vid1, vid2-1);
		
		assertTrue(DB.ViewAllVisitor().get(1).getName().equals("Tom cat"));
		assertTrue(DB.ViewVisitor(vid2).getName().equals("Tom cat"));
		assertTrue(DB.ViewVisitor(vid2).getEmail().equals("TomAndJerry123@gmail.com"));
		
		Vehicle Batcar = new Vehicle(State.NY, VehicleType.car, "BAT-I GOTHAM CITY");
		CreditCard card = new CreditCard("373456789074007","Jane Smith","05/23","94102");
		int oid1 = DB.CreateOrder(pid, vid1, Batcar, card);
		int oid2 = DB.CreateOrder(pid, vid1, Batcar, card);
		assertEquals(oid1, oid2-1);
		
		assertEquals(DB.ViewAllOrder().size(),2);
		double fee1 = DB.ViewOrder(oid1).getFee(), 
				fee2 = DB.ViewParkDetail(pid).getAdmFee().get(VehicleType.car).get(1);
		assertEquals(fee1, fee2, 0.00000000001);
		
		int nid1 = DB.CreateNote(pid, vid1, "Good place", "Only here for 5 minutes");
		int nid2 = DB.CreateNote(pid, vid1, "2nd Visit", "Just reveal the terrible side of this place");
		assertEquals(nid1,nid2-1);
		assertEquals(DB.ViewNote(nid2).getTitle(), "2nd Visit");
		assertEquals(DB.ViewNote(nid2).getText(), "Just reveal the terrible side of this place");
		assertEquals(DB.ViewSpecifiedNoteOfGivenPark(pid, nid2).getTitle(), "2nd Visit");
		assertEquals(DB.ViewSpecifiedNoteOfGivenPark(pid, nid2).getText(), 
				"Just reveal the terrible side of this place");
		assertTrue(DB.ViewSpecifiedNoteOfGivenPark(1023123, nid2).equals(NullNote.getinstance()));
		assertTrue(DB.ViewSpecifiedNoteOfGivenPark(1023123, 12121024).equals(NullNote.getinstance()));
		
		assertTrue(DB.SearchNote("asdfqraqwe").isEmpty());
		assertEquals(DB.SearchNote("terrible").get(0).getTitle(),"2nd Visit");
		assertEquals(DB.SearchNote("terrible").get(0).getText(),
				"Just reveal the terrible side of this place");
		assertEquals(DB.ViewAllNotesOfThePark(pid).size(),2);
		
		DB.UpdateNote(nid2, "Note deleted", "Censored by administrator");
		assertEquals(DB.ViewNote(nid2).getTitle(), "Note deleted");
		assertEquals(DB.ViewNote(nid2).getText(), "Censored by administrator");
		
		DB.DeleteNote(nid2);
		assertTrue(DB.ViewNote(nid2).equals(NullNote.getinstance()));
		
		Vehicle Batcar2 = new Vehicle(State.NY, VehicleType.car, "BAT-II GOTHAM CITY");
		CreditCard card2 = new CreditCard("374007","Jane Smith","05/23","94102");
		try {
			DB.CreateOrder(pid, vid2, Batcar2, card2);
			fail("Should throw exception");
		}catch(RuntimeException e) {
			assertTrue(true);
		}
		
		try {
			DB.CreateNote(pid, vid2, "Hi", "I will just pretend I've visited this place");
			fail("Should throw exception");
		}catch(RuntimeException e) {
			assertTrue(true);
		}
		
		//Report Test
		int NumberOfAdmission = DB.getAdmissionReport(null, null).getTotal_admission();
		assertEquals(2, NumberOfAdmission);
		int NumberOfAdmission2 = DB.getRevenueReport("19990202", "21221212").getTotal_admission();
		assertEquals(2, NumberOfAdmission2);
		assertEquals(18, DB.getRevenueReport("19990202", "21221212").getRevenue(),0.0000001);
		
		try {
			DB.getRevenueReport("20111412", "19990999");
			fail("Should throw exception");
		}catch(RuntimeException e) {
			assertTrue(true);
		}
	}
	@Test
	public void SearchTest() {
		assertEquals(DB.SearchOrder("GOTHAM").size(),2);
		assertEquals(DB.SearchVisitor("John").size(),1);
		assertFalse(DB.SearchVisitor("john.doe@example.com").isEmpty());
	}

}
