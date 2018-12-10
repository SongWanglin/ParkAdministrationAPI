package edu.iit.cs445.StateParking.Objects;

import java.util.*;

public class ListOfVisitor {
	private static ListOfVisitor instance = null;
	private ArrayList<Visitor> Visitors;
	
	private ListOfVisitor() {
		Visitors = new ArrayList<Visitor>();
	}
	
	public static ListOfVisitor getInstance() {
		if(instance == null) {
			instance = new ListOfVisitor();
		}
		return instance;
	}
	
	public ArrayList<Visitor> ViewAll() {
		return Visitors;
	}
	
	public int addVisitor(String name, String email) {
		Iterator<Visitor> it = Visitors.iterator();
		while(it.hasNext()) {
			Visitor temp = it.next();
			if(temp.getName().equals(name)&&temp.getEmail().equals(email)) {
				return temp.getVid();
			}
		}
		Visitor guest = new VisitorObj(name, email);
		Visitors.add(guest);
		return guest.getVid();
	}
	
	public Visitor SearchByID(int id) {
		Iterator<Visitor> it = Visitors.iterator();
		while(it.hasNext()) {
			Visitor visitor = it.next();
			if(visitor.getVid() == id) {
				return visitor;
			}
		}
		return NullVisitor.getinstance();
	}
	
	public ArrayList<Visitor> SearchByKeyWord(String keyword) {
		Iterator<Visitor> it = Visitors.iterator();
		ArrayList<Visitor> matchList = new ArrayList<Visitor>();
		while(it.hasNext()) {
			Visitor visitor = it.next();
			if(visitor.KeywordMatch(keyword) == true) {
				matchList.add(visitor);
			}
		}
		return matchList;
	}
}
