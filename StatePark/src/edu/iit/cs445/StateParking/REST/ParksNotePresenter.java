package edu.iit.cs445.StateParking.REST;
import java.util.*;

public class ParksNotePresenter {
	public String pid;
	public ArrayList <SimpleNotePresenter> notes ;
	public ParksNotePresenter() {
		this.notes = new ArrayList<SimpleNotePresenter>();
	}
	public ParksNotePresenter(int Pid) {
		this.pid = Integer.toString(Pid);
		this.notes =  new ArrayList<SimpleNotePresenter>();
	}
	public void setPid(int PID) {
		this.pid = Integer.toString(PID);
	}
	public void addToNoteList(SimpleNotePresenter note) {
		this.notes.add(note);
	}
}
