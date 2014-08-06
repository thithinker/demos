package demos.algorithm;

import org.junit.Test;

import junit.framework.TestCase;

public class AlgosTest extends TestCase{
	Algos algos = null;
	@Override
	protected void setUp() throws Exception {
		algos = new Algos();
	}
	@Test
	public void testBinarySearch(){
		Integer[] arr = {2,3,4,23,45,56,67,78,89,90};
		assertEquals(arr.length - 1, algos.binarySearch(arr, 90));
		assertEquals(3, algos.binarySearch(arr, 23));
		assertEquals(0, algos.binarySearch(arr, 2));
	}
	
	@Test
	public void testMaxSubSum(){
		int[] arr = {2,3,4,-23,4,0, -5, -8, 17};
		assertEquals(17, algos.maxSubSum(arr));
		int[] arr2 = {-23, -5, -8, -17};
		assertEquals(-5, algos.maxSubSum(arr2));
	}
	
	@Test
	public void testGcd(){
		assertEquals(5, algos.gcd(15, 20));
		assertEquals(1, algos.gcd(17, 11));
	}
	
	@Test
	public void testPow(){
		assertEquals(4096, algos.pow(2, 12));
	}
}
