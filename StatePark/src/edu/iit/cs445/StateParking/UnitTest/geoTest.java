package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;

public class geoTest {
	private geo A = new geo(0,0);
	@Test
	public void test() {
		A.setLat(41.87);
		assertTrue(A.getLat()==41.87);
		A.setLng(87.62);
		assertTrue(A.getLng()==87.62);
	}
	@Test (expected = RuntimeException.class)
	public void testException()
	{
	   try
	   {
	      A.setLat(111);
	   }
	   catch(RuntimeException re)
	   {
	      String message = "Invalid value";
	      assertEquals(message, re.getMessage());
	      throw re;
	    }
	    fail("Invalid lat exception did not throw!");
	  }
	@Test (expected = RuntimeException.class)
	public void testException2()
	{
	   try
	   {
	      A.setLng(181);
	   }
	   catch(RuntimeException re)
	   {
	      String message = "Invalid value";
	      assertEquals(message, re.getMessage());
	      throw re;
	    }
	    fail("Invalid lng exception did not throw!");
	  }
}
