package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.Report;
import edu.iit.cs445.StateParking.Objects.ReportOfAdmission;
import edu.iit.cs445.StateParking.Objects.RevenueReport;

public class RevenueReportTest {
	private RevenueReport report = new RevenueReport();
	@Test
	public void test() {
		assertEquals(report.getId(), 911);
		assertEquals(report.getName(), "Revenue report");
		report.setStartDate("20180901");
		assertEquals(report.getStartDate(), "20180901");
		report.setEndDate("20181117");
		assertEquals(report.getEndDate(), "20181117");
		report.setTotal_admission(1);
		assertEquals(report.getTotal_admission(),1);
		assertEquals(report.toString(),"911 Revenue report");
		report.setRevenue(10.0);
		assertTrue(report.getRevenue()==10.0);
		report.addDetail_by_park(2, "Area 51", 2, 21);
		assertEquals(report.getDetail_by_park().get(0).pid,2);
		assertEquals(report.getDetail_by_park().get(0).name,"Area 51");
		assertEquals(report.getDetail_by_park().get(0).OrderCount,2);
		assertTrue(report.getDetail_by_park().get(0).rev==21);
	}

}
