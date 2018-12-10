package edu.iit.cs445.StateParking.REST;

import java.util.*;

import com.google.gson.JsonObject;

import edu.iit.cs445.StateParking.Objects.*;

public class AdditionalFunction {
	private MockDatabase DB = MockDatabase.getInstance();
	private static AdditionalFunction instance = null;
	private AdditionalFunction() {
	}
	public static AdditionalFunction getInstance() {
		if(instance == null) {
			instance = new AdditionalFunction();
		}
		return instance;
	}
	
	public ArrayList<String> OneLineParsing(String address){
		StringTokenizer a = new StringTokenizer(address,",");
		ArrayList<String> result = new ArrayList<String>();
		while(a.hasMoreTokens()) {
			result.add(a.nextToken());
		}
		StringTokenizer b = new StringTokenizer(result.get(result.size()-1), " ");
		result.remove(result.size()-1);
		while(b.hasMoreTokens()) {
			result.add(b.nextToken());
		}
		return result;
	}
	
	public boolean EmptyInput(String input) {
		return (input == null)? (input == null): (input.isEmpty() || input.trim().length()==0);
	}
	public String ValidInput(String input) 
			throws RuntimeException {
		if(EmptyInput(input)) {
			throw new RuntimeException("Not Valid Input");
		}
		return input;
	}
	
	public  JsonObject validGetJson(JsonObject input, String field) {
		if (input!=null &&input.has(field) && !EmptyInput(field)) {
			return input.get(field).getAsJsonObject();
		}
		return null;
	}
	
	public  String validGetField(JsonObject input, String field) {
		if (input!=null &&input.has(field) && !EmptyInput(field)) {
			return input.get(field).getAsString();
		}
		return "";
	}
	
	public int validGetIntField(JsonObject input, String field)
			throws RuntimeException{
			if (input!=null && input.has(field)&& !EmptyInput(field)) {
				return input.get(field).getAsInt();
			}
			throw new RuntimeException();
		}
	
	public Double validGetDoubleField(JsonObject input, String field)
		throws RuntimeException{
		if (input!=null && input.has(field)&& !EmptyInput(field)) {
			return input.get(field).getAsDouble();
		}
		throw new RuntimeException();
	}
	
	public boolean PositivePrice(Map<String, ArrayList<Double>> myMap2) {
		boolean result = true;
		Iterator<String> it = myMap2.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			ArrayList<Double> list = myMap2.get(key);
			result = result && (list!=null && list.stream().allMatch(i->i>=0));
		}
		return result;
	}
	
	public ArrayList<ParksNotePresenter> createNotePresenter(ArrayList<Note> note){
		ArrayList<ParksNotePresenter> presenter = new ArrayList<ParksNotePresenter>();
		Iterator <Note> it = note.iterator();
		while(it.hasNext()) {
			Note temp = it.next();
			presenter = AddNotePresenter(presenter, temp);
		}
		return presenter;
	}
	public ArrayList<ParksNotePresenter> AddNotePresenter(ArrayList<ParksNotePresenter> presenter,
		Note note){
		SimpleNotePresenter N  = new SimpleNotePresenter(note);
		int pid = DB.GetKeyfromValue(DB.NoteOfPark, note.getNid());
		Iterator <ParksNotePresenter> it = presenter.iterator();
		boolean HasPid = false;
		while(it.hasNext()) {
			ParksNotePresenter temp = it.next();
			if(Integer.parseInt(temp.pid )== pid) {
				temp.addToNoteList(N);
				HasPid = true;
			}
		}
		if(HasPid == false) {
			ParksNotePresenter temp = new ParksNotePresenter(pid);
			temp.addToNoteList(N);
			presenter.add(temp);
		}
		return presenter;
	}
	
	public VisitorWithDetailsPresenter getVisitorDetail(Visitor visitor) {
		VisitorWithDetailsPresenter result = new VisitorWithDetailsPresenter(visitor);
		int VID = visitor.getVid();
		ArrayList<Integer> OIDs = DB.OrderOfVisitor.get(VID); 
		Iterator<Integer> it1 = OIDs.iterator(); int oid , pid;
		while(it1.hasNext()) {
			oid = it1.next();
			Order order = DB.ViewOrder(oid);
			if(!order.isNull()) {
				pid = DB.GetKeyfromValue(DB.OrderOfPark, oid);
				OrderForVisitorPresenter O = new OrderForVisitorPresenter(order);
				O.setPid(pid);
				result.addOrder(O);
			}
		}
		
		ArrayList<Integer> NIDs = DB.OrderOfVisitor.get(VID); 
		Iterator<Integer> it2 = NIDs.iterator(); int nid ;
		while(it2.hasNext()) {
			nid = it2.next();
			Note note = DB.ViewNote(nid);
			if(!note.isNull()) {
				pid = DB.GetKeyfromValue(DB.NoteOfPark, nid);
				NoteForVisitorPresenter N = new NoteForVisitorPresenter(note);
				N.setPID(pid);
				result.addNote(N);
			}
		}
		return result;
	}
	
	public OrderWithDetailPresenter getOrderDetail(Order order) {
		OrderWithDetailPresenter result = new OrderWithDetailPresenter(order);
		int pid = DB.GetKeyfromValue(DB.OrderOfPark, order.getId());
		int vid = DB.GetKeyfromValue(DB.OrderOfVisitor, order.getId());
		result.setPid(pid); 
		result.setVid(vid);
		Visitor visitor = DB.ViewVisitor(vid);
		result.visitor.setName(visitor.getName());
		result.visitor.setEmail(visitor.getEmail());
		return result;
	}
	
}
