package edu.iit.cs445.StateParking.Objects;

import java.util.*;

public class ListOfNote {
	private static ListOfNote instance = null;
	private ArrayList<Note> Notes;
	
	private ListOfNote() {
		Notes = new ArrayList<Note>();
	}
	
	public static ListOfNote getInstance() {
		if(instance == null) {
			instance = new ListOfNote();
		}
		return instance;
	}
	
	public List<Note> ViewAll(){
		return Notes;
	}
	
	public Note SearchByID(int id) {
		Iterator<Note> it = Notes.iterator();
		while(it.hasNext()) {
			Note note = it.next();
			if(note.getNid() == id) {
				return note;
			}
		}
		return NullNote.getinstance();
	}
	
	public ArrayList<Note> SearchByKeyword(String keyword){
		Iterator<Note> it = Notes.iterator();
		ArrayList<Note> matchList = new ArrayList<Note>();
		while(it.hasNext()) {
			Note note = it.next();
			if(note.KeywordMatch(keyword) == true) {
				matchList.add(note);
			}
		}
		return matchList;
	}
	
	public int createNote(String title, String comment) {
		Note note = new NoteObj(title, comment);
		Notes.add(note);
		return note.getNid();
	}	
	public boolean removeNote(int nid){
		Iterator<Note> it = Notes.iterator();
		while(it.hasNext()) {
			Note note = it.next();
			if(note.getNid() == nid) {
				Notes.remove(note);
				return true;
			}
		}
		return false;
	}
	public void updateNote(int nid, String title, String comment) {
	Iterator<Note> it = Notes.iterator();
	while(it.hasNext()) {
		Note note = it.next();
		if(note.getNid() == nid) {
			note.setTitle(title);
			note.setText(comment);
			}
		}
	}
	
	public List<Note> GetNoteWithin(Date start, Date end){
		List<Note> output = new ArrayList<Note>();
		Iterator<Note> it = Notes.iterator();
		while(it.hasNext()) {
			Note note = it.next();
			if(note.getNoteDate().before(end) && note.getNoteDate().after(start))
				output.add(note);
		}
		return output;
	}
}
