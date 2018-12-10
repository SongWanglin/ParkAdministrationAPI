package edu.iit.cs445.StateParking.Objects;

import java.util.ArrayList;

public class RevenueReport extends Report {
	private double revenue;
	private ArrayList<ParkRevDetail> detail_by_park = new ArrayList<ParkRevDetail>();
	public RevenueReport() {
		this.id = 911;
		this.name = "Revenue report";
		this.StartDate = " ";
		this.EndDate = " ";
		this.total_admission = 0;
		this.revenue = 0;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	

	public ArrayList<ParkRevDetail> getDetail_by_park() {
		return detail_by_park;
	}

	public void addDetail_by_park(int id, String name, int OrderN, double revenue) {
		ParkRevDetail R = new ParkRevDetail(id, name, OrderN, revenue);
		detail_by_park.add(R);
	}

	public class ParkRevDetail{
		public int pid;
		public String name;
		public int OrderCount;
		public double rev;
		public ParkRevDetail(int id, String name, int OrderN, double revenue) {
			this.pid = id;
			this.name = name;
			this.OrderCount = OrderN;
			this.rev = revenue;
		}
	}
	
}
