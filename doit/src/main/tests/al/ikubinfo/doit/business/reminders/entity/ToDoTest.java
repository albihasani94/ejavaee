package al.ikubinfo.doit.business.reminders.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class ToDoTest {

	@Test
	public void validToDo() {
		ToDo valid = new ToDo("", "available", 11);
		assertTrue(valid.isValid());
	}
	
	public void invalidToDo() {
		ToDo valid = new ToDo("", null, 11);
		assertFalse(valid.isValid());
	}
	
	@Test
	public void todoWithoutDescription() {
		ToDo valid = new ToDo("implement", null, 10);
		assertTrue(valid.isValid());
	}

}
