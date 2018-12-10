package edu.iit.cs445.StateParking.Objects;
import java.util.*;

public class ReportOfAdmission extends Report {

	public ArrayList<ParkAdmDetail> admission_by_park = new ArrayList<ParkAdmDetail>();
	public ReportOfAdmission() {
		this.id = 907;
		this.name = "Admission report";
		this.StartDate = " ";
		this.EndDate = " ";
		this.total_admission = 0;
	}
	

	public ArrayList<ParkAdmDetail> getAdmission_by_park() {
		return admission_by_park;
	}

	public void addParkAdmission (int id, String name, int AdmCount) {
		ParkAdmDetail P = new ParkAdmDetail(id, name, AdmCount);
		admission_by_park.add(P);
	}

	public class ParkAdmDetail{
		public int pid;
		public String name;
		public int AdmCount;
		public ParkAdmDetail(int id, String name, int AdmCount) {
			this.pid = id;
			this.name = name;
			this.AdmCount = AdmCount;
		}
	}
}
