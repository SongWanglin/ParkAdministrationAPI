package edu.iit.cs445.StateParking.Objects;

public class VisitorObj implements Visitor{
	private static int count = 0;
	private final int vid; 
	private String name;
	private String email;
	
	public VisitorObj(String b, String c) {
		this.vid = count ++;
		this.name = b;
		this.email = c;
	}
	public int getVid() {
		return vid;
	}

	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean KeywordMatch(String keyword) {
		if(	this.name.matches("(?i)(.*)"+keyword+"(.*)") ||
			this.email.matches("(?i)(.*)"+keyword+"(.*)"))
				return true;
		return false;
	}
	public boolean isNull() {
		return false;
	}

}
