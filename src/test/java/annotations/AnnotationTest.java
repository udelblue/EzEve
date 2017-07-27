package annotations;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import annotations.EzEve;
import core.Event;
import core.TestUserWithID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AnnotationTest {
	
	
	
	@Test
	public void annotationWithIDTest() throws Exception {
		TestUserWithID tu = testAnno();
		assertNotNull(tu);
		
	}
	
	@EzEve()
	public TestUserWithID testAnno(){
		TestUserWithID tu = new TestUserWithID(40, "tom", "tester");
		return tu;
	}
	
	
	
	
}
