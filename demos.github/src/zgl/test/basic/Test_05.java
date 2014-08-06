package zgl.test.basic;

/**
 * 来自《Java程序员面试宝典》，P181
 * @author yize
 * 2014年7月6日
 */
public class Test_05 {
	public static void main(String[] args) {
		StringBuffer a = new StringBuffer("A");
		StringBuffer b = new StringBuffer("B");
		
		operate(a, b);
		System.out.println(a + ", " + b);
		
		int[] arr1 = {1, 2};
		int[] arr2 = {3, 4};
		operate(arr1, arr2);
		System.out.println(arr1[0] + " " + arr1[1]);
		System.out.println(arr2[0] + " " + arr2[1]);
	}

	private static void operate(StringBuffer a, StringBuffer b) {
		a.append(b);
		b = a;
	}
	
	private static void operate(int[] a, int[] b){
		a[0] = 11;
		a[1] = 22;
		
		b[0] = 33;
		b[1] = 44;
		b = a;
	}
}
