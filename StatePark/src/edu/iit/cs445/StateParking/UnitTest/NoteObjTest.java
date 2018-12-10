package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;

public class NoteObjTest {
	private Note note = new NoteObj("Good place","But I still want my money back.");
	private Note note2 = new NoteObj("Good place","But I still want my money back.");
	Date date = new Date();
	@Test
	public void test() {
		assertEquals(note.getNid(),note2.getNid()-1);
		assertTrue(note.KeywordMatch("Good"));
		assertTrue(note.KeywordMatch("money back"));
		assertFalse(note.KeywordMatch("would revisit"));
		assertTrue(note.getNoteDate().equals(date));
		note.setTitle("Note modified by administrator");
		assertEquals(note.getTitle(),"Note modified by administrator");
		note.setText("Content deleted and user banned");
		assertEquals(note.getText(), "Content deleted and user banned");
		assertFalse(note.isNull());
	}
}
