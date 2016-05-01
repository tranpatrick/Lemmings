package tests;
import static org.junit.Assert.*;

public class AssertionTests {

	public AssertionTests() {
	}

	public void assertion(String message,boolean test){
		if(!test)
			System.out.println(message);
		assertTrue(message, test);
	}
}
