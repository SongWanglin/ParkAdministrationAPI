package edu.iit.cs445.StateParking.REST;

import java.text.SimpleDateFormat;

import edu.iit.cs445.StateParking.Objects.*;

public class NotePresenterWithDetails {
	public String nid;
	public String date;
	public String title;
	public String text;
	public String pid;
	public String vid;
	public NotePresenterWithDetails(Note note) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String day = format.format(note.getNoteDate());
		this.nid = Integer.toString(note.getNid());
		this.title = note.getTitle();
		this.date = day;
		this.pid = "0";
		this.vid = "0";
		this.text = note.getText();
	}
	public void setPid(int PID) {
		this.pid = Integer.toString(PID);
	}
	public void setVid(int VID) {
		this.vid = Integer.toString(VID);
	}
}
