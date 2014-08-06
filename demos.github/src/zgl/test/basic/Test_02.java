package zgl.test.basic;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 来自《Java程序员面试宝典》，P102.
 * 
 * @author yize 2014年7月5日
 */
public class Test_02 {
	static class A {
		private static class B {
			public static void main(String[] args) {
				System.out.println("23");
				
				Map<String, String> m = new HashMap<String, String>();
				m.put(null, "th");
				m.put(null, null);
				System.out.println(m.size());
				Dictionary<String, String> d = new Hashtable<>();
				
			}
		}
	}
}
