package edu.iit.cs445.StateParking.Objects;

import java.util.Date;

public interface Note {
	public int getNid();
	public Date getNoteDate();
	public String getTitle();
	public void setTitle(String title) ;
	public String getText();
	public void setText(String text);
	public boolean KeywordMatch(String keyword);
	public boolean isNull();
}
