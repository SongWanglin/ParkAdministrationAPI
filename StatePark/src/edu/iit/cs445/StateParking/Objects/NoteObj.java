package edu.iit.cs445.StateParking.Objects;

import java.text.SimpleDateFormat;
import java.util.*;

public class NoteObj implements Note {
	private static int count = 0;
	private final int nid; 
	private Date CreateDate;
	private String title;
	private String text;
	
	public NoteObj(String title, String comment) {
		Date date = new Date();
		this.nid = count ++;
		this.CreateDate = date;
		this.title  = title;
		this.text = comment;
	}
	
	public int getNid() {
		return nid;
	}
	
	public Date getNoteDate() {
		return CreateDate;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean KeywordMatch(String keyword) {
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMdd");
		String CommentDay = DateFormat.format(CreateDate);
		boolean DayMatch = CommentDay.matches("(?i)(.*)"+keyword+"(.*)") ;
		boolean CommentMatch = (this.title.matches("(?i)(.*)"+keyword+"(.*)"))||
					(this.text.matches("(?i)(.*)"+keyword+"(.*)"));
		return DayMatch||CommentMatch;
	}
	
	public boolean isNull() {
		return false;
	}
}
 