package edu.iit.cs445.StateParking.Objects;

public abstract class Report {
	protected int id;
	protected String name; 
	protected  String StartDate;
	protected  String EndDate;
	protected  int total_admission;
	
	public int getId() {
		return id;
	}
	
	public String getStartDate() {
		return StartDate;
	}
	public void setStartDate(String startDate) {
		StartDate = startDate;
	}
	public String getEndDate() {
		return EndDate;
	}
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}
	
	public int getTotal_admission() {
		return total_admission;
	}

	public void setTotal_admission(int total_admission) {
		this.total_admission = total_admission;
	}
	public String getName() {
		return name;
	}
	public String toString() {
		return this.id + " "+this.name;
	}
}
