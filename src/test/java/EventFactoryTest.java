
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Event;
import core.EventFactory;
import core.TestUser;
import core.TestUserWithID;
import core.TestUserWithIDLong;

public class EventFactoryTest {

	EventFactory eventFactory;

	@Before
	public void setUp() throws Exception {
		this.eventFactory = new EventFactory();

	}

	@Test
	public void createEventTest() throws Exception {
		TestUser tu = new TestUser("tom", "tester");
		Event e = this.eventFactory.create(tu, "created", "system");
		assertNotNull(e);
		assertTrue("Null ID", e.getClassID().equals(""));
		assertFalse("No eventType", e.getEventType().equals(""));
	}

	@Test(expected = Exception.class)
	public void createEventWithIDNoPackageTest() throws Exception {
		TestUserWithIDNoPackage tu = new TestUserWithIDNoPackage(86, "tom", "tester");
		Event e = this.eventFactory.create(tu, "created", "system");
		assertNotNull(e);
		assertTrue("Empty ID", !e.getClassID().equals(""));
		assertTrue("Empty ID", e.getClassID().equals("86"));
		assertFalse("No eventType", e.getEventType().equals(""));
	}

	@Test
	public void createEventWithIDTest() throws Exception {
		TestUserWithID tu = new TestUserWithID(40, "tom", "tester");
		Event e = this.eventFactory.create(tu, "created", "system");
		assertNotNull(e);
		assertTrue("Empty ID", !e.getClassID().equals(""));
		assertTrue("Empty ID", e.getClassID().equals("40"));
		assertFalse("No eventType", e.getEventType().equals(""));
	}

	@Test
	public void createEventWithIDLongTest() throws Exception {
		TestUserWithIDLong tul = new TestUserWithIDLong((long) 60, "tom", "tester");
		Event e = this.eventFactory.create(tul, "created", "system");
		assertNotNull(e);
		assertTrue("Empty ID", !e.getClassID().equals(""));
		assertTrue("Empty ID", e.getClassID().equals("60"));
		assertFalse("No eventType", e.getEventType().equals(""));
	}

	/*
	 * @Test(expected = Exception.class) public void
	 * createThrowExceptionEventTest() throws Exception {
	 * 
	 * 
	 * 
	 * }
	 */

}
