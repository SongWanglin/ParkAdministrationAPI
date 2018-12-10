package edu.iit.cs445.StateParking.Objects;

public interface Visitor {
	public int getVid();
	public String getName() ;
	public String getEmail();
	public boolean KeywordMatch(String keyword);
	public boolean isNull();
}
