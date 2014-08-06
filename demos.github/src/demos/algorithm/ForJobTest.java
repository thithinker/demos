package demos.algorithm;

import org.junit.Test;

import junit.framework.TestCase;

public class ForJobTest extends TestCase{
	ForJob forJob = null;
	@Override
	protected void setUp() throws Exception {
		forJob = new ForJob();
	}
	
	
	@Test
	public void testNumOfOne(){
		assertEquals(5, forJob.numOfOne(12));
	}
	
	@Test
	public void testTransNum(){
		assertEquals("1000", forJob.transNum(27, 3));
		assertEquals("111111", forJob.transNum(63));
	}
}
