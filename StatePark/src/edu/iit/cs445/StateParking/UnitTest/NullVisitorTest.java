package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;

public class NullVisitorTest {
	Visitor FakeAccount = NullVisitor.getinstance();
	@Test
	public void test() {
		assertEquals(FakeAccount.getVid(), -1);
		assertFalse(FakeAccount.getVid()>=0);
		assertEquals(FakeAccount.getName(), null);
		assertEquals(FakeAccount.getEmail(), null);
		assertFalse(FakeAccount.KeywordMatch(""));
		assertTrue(FakeAccount.isNull());
	}

}
