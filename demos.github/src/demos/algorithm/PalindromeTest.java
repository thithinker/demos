package demos.algorithm;

import junit.framework.TestCase;
import junit.framework.TestResult;

import org.junit.Test;

public class PalindromeTest extends TestCase{
	Palindrome p;
	@Override
	protected void setUp() throws Exception {
		p = new Palindrome();
	}
	
	@Test
	void testLength(){
		assertEquals(3, p.length("1234545624530"));
		assertEquals(0, p.length(null));
		assertEquals(0, p.length(" "));
	}
	
	@Override
	public TestResult run() {
		return null;
		// TODO
	}
}
