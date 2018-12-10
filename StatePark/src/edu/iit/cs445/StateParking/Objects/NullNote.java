package edu.iit.cs445.StateParking.Objects;

import java.util.Date;

public class NullNote implements Note {
	private static Note instance = null;
	private NullNote() {}
	public static Note getinstance() {
		if(instance == null)
			instance = new NullNote();
		return instance;
	}
	@Override
	public int getNid() {
		return -1;
	}
	@Override
	public Date getNoteDate() {
		return null;
	}

	@Override
	public String getTitle() {
		return null;
	}
	@Override
	public void setTitle(String title) {}
	@Override
	public String getText() {
		return null;
	}
	@Override
	public void setText(String text) {}
	@Override
	public boolean KeywordMatch(String keyword) {
		return false;
	}
	@Override
	public boolean isNull() {
		return true;
	}
}
 