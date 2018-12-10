package edu.iit.cs445.StateParking.Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import edu.iit.cs445.StateParking.ObjectsEnum.VehicleType;

public class MockDatabase implements InterfaceDB {
	// A mock database that maps the IDs of visitors and parks 
	// to the ones of notes and orders 
	private ListOfPark parks = ListOfPark.getInstance();
	private ListOfNote notes = ListOfNote.getInstance();
	private ListOfVisitor visitors = ListOfVisitor.getInstance();
	private ListOfOrder orders = ListOfOrder.getInstance(); 
	
	public HashMap<Integer, ArrayList<Integer>> NoteOfPark = new HashMap<Integer, ArrayList<Integer>>();
	public  HashMap<Integer, ArrayList<Integer>> OrderOfPark = new HashMap<Integer, ArrayList<Integer>>();
	public  HashMap<Integer, ArrayList<Integer>> NoteOfVisitor = new HashMap<Integer, ArrayList<Integer>>();
	public  HashMap<Integer, ArrayList<Integer>> OrderOfVisitor = new HashMap<Integer, ArrayList<Integer>>();
	
	private ReportCode[] reportCode = new ReportCode[2];
	private ReportOfAdmission admissionReport = new ReportOfAdmission();
	private RevenueReport revenueReport = new RevenueReport();
	
	//Singleton design pattern
	private static MockDatabase instance = null;
	private MockDatabase() {
		reportCode[0] = new ReportCode(907, "Admission report");
		reportCode[1] = new ReportCode(911, "Revenue report"); 
	}
	public static MockDatabase getInstance() {
		if(instance == null) {
			instance = new MockDatabase();
		}
		return instance;
	}
	
	public int GetKeyfromValue(HashMap<Integer, ArrayList<Integer>> map, int value)
		throws RuntimeException{
		int result = -1;
		for(Integer key : map.keySet()) {
			if(map.get(key).contains(value))
				result = key;
		}
		return result;
	}
	
	public int CreatePark(String name, Address a, String web, geo geo) {
		int PID = parks.createPark(name, a, web, geo);
		return PID;
	}	
	public int CreatePark2(ParkBoundaryInterface park) {
		return parks.createPark2(park);
	}
	public void DeletePark(int PID) {
		parks.removePark(PID);
	}
	public ListOfPark ViewAllParks() {
		return parks;
	}
	public ParkBoundaryInterface ViewParkDetail(int id) {
		return parks.SearchByID(id);
	}
	public ArrayList<ParkBoundaryInterface> SearchPark(String keyword) {
		return parks.SearchByKeyword(keyword);
	}
	
	public ArrayList<Note> ViewAllNotesOfThePark(int pid) {
		ArrayList<Note> result = new ArrayList<Note>();
		ArrayList<Integer> Nids = new ArrayList<Integer> ();
		if(NoteOfPark.containsKey(pid)) {
			Nids = NoteOfPark.get(pid);
		}
		else {
			return result; 
		}
		Iterator<Integer> it = Nids.iterator();
		while(it.hasNext() ) {
				int nid = it.next();
				Note note = notes.SearchByID(nid);
				result.add(note);
			}
	
		return result;
	}
	
	public Note ViewSpecifiedNoteOfGivenPark(int pid, int nid){
		if(!NoteOfPark.containsKey(pid))
			return NullNote.getinstance();
		else if(!NoteOfPark.get(pid).contains(nid))
			return NullNote.getinstance();
		return notes.SearchByID(nid);

	}
	
	
	public int CreateOrder(int pid, int vid, Vehicle bike, CreditCard card)
		throws RuntimeException{
		int oid = orders.createOrder(bike, card);
		ParkBoundaryInterface park = parks.SearchByID(pid);
		if(park.isNull()) { throw new RuntimeException();}
		Order order = orders.SearchByID(oid);
		ArrayList<Double> price = park.getAdmFee().get( order.getVehicle().getType() );
		if(park.getAddress().getState().equals(order.getVehicle().getState())) {
			orders.SetFee(oid, price.get(0));
		}	
		else {
			orders.SetFee(oid, price.get(1));
		}
		ArrayList<Integer> oids = new ArrayList<Integer> ();
		if(OrderOfPark.containsKey(pid)) {
		   oids = OrderOfPark.get(pid);
		}
		oids.add(oid);
		OrderOfPark.put(pid, oids);
		ArrayList<Integer> oids2 = new ArrayList<Integer> ();
		if(OrderOfVisitor.containsKey(vid))
			oids2 = OrderOfVisitor.get(vid);
		oids2.add(oid);
		OrderOfVisitor.put(vid, oids2);
		return oid;
	}
	public ArrayList<Order> ViewAllOrder(){
		return orders.viewAll();
	}
	public Order ViewOrder(int oid) {
		return orders.SearchByID(oid);
	}
	public ArrayList<Order> SearchOrder(String keyword){
		return orders.SearchByKeyWord(keyword);
	}
	
	
	public int NewVisitor(String name, String email) {
		return visitors.addVisitor(name, email);
	}
	public ArrayList<Visitor> ViewAllVisitor() {
		return visitors.ViewAll();
	}
	public Visitor ViewVisitor(int vid) {
		return visitors.SearchByID(vid);
	}
	public ArrayList<Visitor> SearchVisitor(String keyword){
		return visitors.SearchByKeyWord(keyword);
	}
	
	
	public int CreateNote(int pid, int vid, String title, String comment)
		throws RuntimeException{
		ArrayList<Integer> oids = new ArrayList<Integer>();
		if(OrderOfVisitor.containsKey(vid))
			 oids = OrderOfVisitor.get(vid);
		ArrayList<Integer> oids2 =new ArrayList<Integer> ();
		if(OrderOfPark.containsKey(pid))
			oids2 = OrderOfPark.get(pid);
		Iterator<Integer> it = oids.iterator();
		boolean paid = false;
		while(it.hasNext()) {
			int OrderID = it.next();
			if(oids2.contains(OrderID)) {
				paid = true;
			}
		}
		if(paid == false) throw new RuntimeException("You may not post a note to a park unless you "
				+ "pay for admission at that park");
		int NID = notes.createNote(title, comment);
		ArrayList<Integer> Notes = new ArrayList<Integer>();
		if (NoteOfPark.containsKey(pid))
			Notes = NoteOfPark.get(pid);
		ArrayList<Integer> Notes2 = new ArrayList<Integer> ();
		if(NoteOfVisitor.containsKey(vid))
			Notes2 = NoteOfVisitor.get(vid);
		Notes.add(NID);		Notes2.add(NID);
		NoteOfPark.put(pid, Notes);
		NoteOfVisitor.put(vid, Notes2);
		return NID;
	}

	public Note ViewNote(int nid) {
		return notes.SearchByID(nid);
	}
	public boolean DeleteNote(int nid) {
		return notes.removeNote(nid);
	}
	public ArrayList<Note> ViewAllNote(){
		ArrayList <Note> result = new ArrayList<Note> ();
		Iterator <Note> it = notes.ViewAll().iterator();
		while (it.hasNext()) {
			Note note = it.next();
			result.add(note);
		}
		return result;
	}
	public ArrayList<Note> SearchNote(String keyword){
		return notes.SearchByKeyword(keyword);
	}
	
	public void UpdateParkLoc(int Pid,String name, Address a, String web, 
			double lat, double lng, String region, String phone) {
		parks.updateParkLoc(Pid,name, a, web, lat, lng, region, phone);
	}
	
	public void UpdatePark(int PID,VehicleType type, double instate, double outstate) 
		throws RuntimeException{
		if(instate<0 || outstate<0 ) {
			throw new RuntimeException();
		}
		parks.updatePark(PID, type, instate, outstate);
	}
	
	public void UpdateNote(int NID, String title, String comment) {
		notes.updateNote(NID, title, comment);
	}
	
	public ReportCode[] getReportCode () {
		return reportCode;
	}
	
	public ReportOfAdmission getAdmissionReport(String startdate, String enddate) {
		admissionReport = new ReportOfAdmission();
		ArrayList<ParkBoundaryInterface> AllPark = parks.ViewAll();
		Iterator<ParkBoundaryInterface> it = AllPark.iterator();
		int Pid, AdmCount, totalCount = 0; 
		String ParkName;
		while(it.hasNext()) {
			ParkBoundaryInterface park = it.next();
				ParkName = park.getName();
				Pid = park.getPid();
			ArrayList<Integer> ParksOIDs = new ArrayList<Integer>();
			if(OrderOfPark.containsKey(Pid)) {
				ParksOIDs = OrderOfPark.get(Pid);
			}
			ArrayList<Order> ParksOrder = OID_to_Order(ParksOIDs);
			ParksOrder = OrderWithin(ParksOrder, startdate, enddate);
				AdmCount = ParksOrder.size();	
			admissionReport.addParkAdmission(Pid, ParkName, AdmCount);
			totalCount += AdmCount;
		}
		admissionReport.setTotal_admission(totalCount);
		return admissionReport;
	}

	public RevenueReport getRevenueReport(String startdate, String enddate) {
		revenueReport = new RevenueReport();
		ArrayList<ParkBoundaryInterface> AllPark = parks.ViewAll();
		Iterator<ParkBoundaryInterface> it = AllPark.iterator();
		int Pid, OrderCount, totalOrder = 0; 
		String ParkName;
		double revenue = 0, totalRevenue = 0;
		while(it.hasNext()) {
			ParkBoundaryInterface park = it.next();
				ParkName = park.getName();
				Pid = park.getPid();
			ArrayList<Integer> ParksOIDs = new ArrayList<Integer>();
			if(OrderOfPark.containsKey(Pid)) {
				ParksOIDs = OrderOfPark.get(Pid);
			}
			ArrayList<Order> ParksOrder = OID_to_Order(ParksOIDs);
			ParksOrder = OrderWithin(ParksOrder, startdate, enddate);
				OrderCount = ParksOrder.size();
				revenue = RevenueSum(ParksOrder);
			revenueReport.addDetail_by_park(Pid, ParkName, OrderCount, revenue);
			totalOrder += OrderCount;
			totalRevenue += revenue;
		}
		revenueReport.setTotal_admission(totalOrder);
		revenueReport.setRevenue(totalRevenue);
		return revenueReport;
	}
		private boolean DateWithin(Date date, String start, String end) throws RuntimeException {
			Date S = new Date();
			Date E = new Date();
			if(start!=null) {
				S = ValidDate(start);
			}
			if(end!=null) {
				E = ValidDate(end);
			}
			if(E.before(S) && start!=null) {
				throw new RuntimeException();
			}
			return (start==null && end!=null)? (date.before(E)): 
				(start!=null&&end==null)? (date.after(S)):
				(start!=null&&end!=null)? (date.after(S)&&date.before(E)) :true;
		}
		private Date ValidDate(String day) throws RuntimeException {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			format.setLenient(false);
			Date date;
			try {
				date = format.parse(day);
			}catch(ParseException e) { 
				throw new RuntimeException();
				}
			return date;
		}
		private ArrayList<Order> OID_to_Order( ArrayList<Integer> oids){
			ArrayList<Order> result = new ArrayList<Order>();
			Iterator<Integer> it = oids.iterator(); 
			int oid;
			while(it.hasNext()) {
				oid = it.next();
				Order order = orders.SearchByID(oid);
				if (order.isNull() == false) {
					result.add(order);
				}	
			}
			return result;
		}
	
		public double RevenueSum(ArrayList<Order> Orders) {
			Iterator<Order> it = Orders.iterator();
			Order order; double Revenue = 0;
			while(it.hasNext()) {
				order=it.next();
				Revenue = Revenue + order.getFee();
			}
			return Revenue;
		}
		

	public ArrayList<Order> OrderWithin(ArrayList<Order> O, String start, String end){
		ArrayList<Order> output = new ArrayList<Order>();
		Iterator<Order> it = O.iterator();
			while(it.hasNext()) {
				Order order = it.next();
				if(DateWithin(order.getOrderDate(), start, end))
					output.add(order);
			}
		return output;
	}
		
	public ArrayList<Note> NoteWithin(ArrayList<Note> N, String start, String end){
		ArrayList<Note> output = new ArrayList<Note>();
		Iterator<Note> it = N.iterator();
		while(it.hasNext()) {
			Note note = it.next();
			if(DateWithin(note.getNoteDate(), start, end))
				output.add(note);
		}
		return output;
	}
}
