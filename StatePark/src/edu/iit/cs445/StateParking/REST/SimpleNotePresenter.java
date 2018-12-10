package edu.iit.cs445.StateParking.REST;

import java.text.SimpleDateFormat;

import edu.iit.cs445.StateParking.Objects.*;

public class SimpleNotePresenter {
	public String nid;
	public String date;
	public String title;
	public SimpleNotePresenter(Note note) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String day = format.format(note.getNoteDate());
		this.nid = Integer.toString(note.getNid());
		this.title = note.getTitle();
		this.date = day;
	}
}
