package edu.iit.cs445.StateParking.REST;

import java.util.ArrayList;

import edu.iit.cs445.StateParking.Objects.*;

public class VisitorWithDetailsPresenter {
	public String vid;
	public visitor visitor;
	public ArrayList<NoteForVisitorPresenter> note;
	public ArrayList<OrderForVisitorPresenter> order;
	public void addNote(NoteForVisitorPresenter N ) {
		this.note.add(N);
	}
	public void addOrder(OrderForVisitorPresenter O) {
		this.order.add(O);
	}
	public VisitorWithDetailsPresenter(Visitor a) {
		this.vid = Integer.toString(a.getVid());
		this.visitor = new visitor(a);
		this.note = new ArrayList<NoteForVisitorPresenter>();
		this.order = new ArrayList<OrderForVisitorPresenter>();
	}
	public class visitor {
		public String name;
		public String email;
		public visitor (Visitor a) {
			this.name = a.getName();
			this.email = a.getEmail();
		}
	}


}