package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;

public class ReportTest {
	private Report report = new ReportOfAdmission();
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
	}
}
