package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;

public class VisitorTest {
	private Visitor visitor1 = new VisitorObj("John Doe","john.doe@example.com");
	private Visitor visitor2 = new VisitorObj("Tom cat","TomAndJerry123@gmail.com");
	
	@Test
	public void test() {
		assertEquals(visitor1.getVid(), visitor2.getVid()-1);
		assertTrue("Tom cat".equals(visitor2.getName()));
		assertFalse("John Doe".equals(visitor2.getName()));
		assertTrue("TomAndJerry123@gmail.com".equals(visitor2.getEmail()));
		assertFalse("john.doe@example.com".equals(visitor2.getEmail()));
		assertTrue(visitor2.KeywordMatch("To"));
		assertFalse(visitor2.KeywordMatch("Jo"));
		assertFalse(visitor2.isNull());
	}
}
