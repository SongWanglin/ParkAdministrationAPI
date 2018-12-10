package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.ReportCode;

public class ReportCodeTest {
	private ReportCode code = new ReportCode(907, "Admission report");
	@Test
	public void test() {
		assertEquals(code.getRid(), 907);
		assertEquals(code.getName(),"Admission report");
	}

}
