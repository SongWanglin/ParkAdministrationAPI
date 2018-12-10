package edu.iit.cs445.StateParking.UnitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.iit.cs445.StateParking.Objects.*;

public class NullNoteTest {
	private Note note = NullNote.getinstance();
	@Test
	public void test() {
		assertEquals(note.getNid(), -1);
		assertEquals(note.getNoteDate(), null);
		note.setTitle("Good place");
		note.setText("But I still want my money back");
		assertEquals(note.getTitle(), null);
		assertEquals(note.getText(),null);
		assertFalse(note.KeywordMatch(""));
		assertTrue(note.isNull());
	}
}
