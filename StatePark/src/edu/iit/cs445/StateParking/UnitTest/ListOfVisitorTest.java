package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.ListOfVisitor;
import edu.iit.cs445.StateParking.Objects.NullVisitor;
import edu.iit.cs445.StateParking.Objects.Visitor;
import edu.iit.cs445.StateParking.Objects.VisitorObj;

public class ListOfVisitorTest {
	private ListOfVisitor Vlist = ListOfVisitor.getInstance();
	//private Visitor visitor1 = new VisitorObj("John Doe","john.doe@example.com");
	//private Visitor visitor2 = new VisitorObj("Tom cat","TomAndJerry123@gmail.com");
	ArrayList <Visitor> Vlist2 = new ArrayList<Visitor>();
	@Test
	public void test() {
		assertFalse(ListOfVisitor.getInstance()==null);

		assertEquals(Vlist.addVisitor("John Doe","john.doe@example.com")+1,
				Vlist.addVisitor("Tom cat","TomAndJerry123@gmail.com"));
		assertTrue(Vlist.SearchByID(0).getName().equals("John Doe"));
		assertTrue(Vlist.SearchByID(0).getEmail().equals("john.doe@example.com"));
		assertTrue(Vlist.SearchByID(100000000).isNull());
		assertFalse(Vlist.SearchByID(1).isNull());
		assertTrue(Vlist.SearchByID(1).getName().equals("Tom cat"));
		assertTrue(Vlist.SearchByID(1).getEmail().equals("TomAndJerry123@gmail.com"));
		assertTrue(Vlist.SearchByKeyWord("dJ").get(0).getName().equals("Tom cat"));
		assertTrue(Vlist.SearchByKeyWord("dJ").get(0).getEmail().equals("TomAndJerry123@gmail.com"));
	}

}
