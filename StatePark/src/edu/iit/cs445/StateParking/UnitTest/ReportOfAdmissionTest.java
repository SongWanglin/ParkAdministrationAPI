package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.Report;
import edu.iit.cs445.StateParking.Objects.ReportOfAdmission;

public class ReportOfAdmissionTest {

	private ReportOfAdmission report = new ReportOfAdmission();
	@Test
	public void test() {
		assertEquals(report.getId(), 907);
		assertEquals(report.getName(), "Admission report");
		report.setStartDate("20180901");
		assertEquals(report.getStartDate(), "20180901");
		report.setEndDate("20181115");
		assertEquals(report.getEndDate(), "20181115");
		report.setTotal_admission(1);
		assertEquals(report.getTotal_admission(),1);
		assertEquals(report.toString(),"907 Admission report");
		ArrayList <ReportOfAdmission.ParkAdmDetail> list
		= new ArrayList<ReportOfAdmission.ParkAdmDetail> ();
		assertEquals(report.getAdmission_by_park(), list);
		report.addParkAdmission(2, "Area 51", 3);
		assertEquals(report.admission_by_park.get(0).pid,2);
		assertEquals(report.admission_by_park.get(0).name,"Area 51");
		assertEquals(report.admission_by_park.get(0).AdmCount,3);

	}
}
