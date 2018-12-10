package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.REST.AdditionalFunction;
import java.util.*;
public class AdditionalFunctionTest {
	AdditionalFunction f = AdditionalFunction.getInstance();
	@Test
	public void OnlineParsingTest() {
		ArrayList<String> expect = new ArrayList<String>();
		expect.add("8763 E. Canyon Rd");
		expect.add(" Apple River");
		expect.add("IL");
		expect.add("61001");
		ArrayList<String> res = f.OneLineParsing("8763 E. Canyon Rd, Apple River, IL 61001");
		assertArrayEquals(expect.toArray(), res.toArray());
	}
	@Test
	public void EmptyInputTest() {
		assertTrue(f.EmptyInput(""));
		assertTrue(f.EmptyInput(null));
		assertTrue(f.EmptyInput("      "));
	}
	@Test (expected=RuntimeException.class)
	public void ValidInputTest() {
		f.ValidInput(null);
	}
	@Test (expected=RuntimeException.class)
	public void ValidInputTest2() {
		f.ValidInput("");
	}
	@Test (expected=RuntimeException.class)
	public void ValidInputTest3() {
		f.ValidInput("            ");
	}
}
