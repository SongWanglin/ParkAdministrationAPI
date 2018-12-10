package edu.iit.cs445.StateParking.Objects;

public class ReportCode {
	private int rid;
	private String name;
	public ReportCode(int ID, String name) {
		this.rid=ID;
		this.name=name;
	}
	public int getRid() {
		return rid;
	}
	public String getName() {
		return name;
	}
}
