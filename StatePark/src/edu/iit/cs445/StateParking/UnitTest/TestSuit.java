package edu.iit.cs445.StateParking.UnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AddressTest.class,
	VehicleTest.class,
	CardTypeTest.class,
	CreditCardTest.class,
	geoTest.class,
	MockCCprocessorTest.class,
	
	MockDatabaseTest.class,
	
	ParkTest.class,
	NullParkTest.class,
	
	NoteObjTest.class ,
	NullNoteTest.class,

	OrderObjTest.class,
	NullOrderTest.class,
	ListOfNoteAndOrderTest.class,
	
	VisitorTest.class,
	NullVisitorTest.class,
	ListOfVisitorTest.class,
	

	VehicalTypeTest.class,
	
	ReportCodeTest.class,
	ReportTest.class,
	ReportOfAdmissionTest.class,
	RevenueReportTest.class,
	AdditionalFunctionTest.class
})
public class TestSuit {   
} 