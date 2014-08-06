package zgl.test.basic;

/**
 * 来自《Java程序员面试宝典》，P179.
 * @author yize
 * 2014年7月6日
 */
public class Test_04 {
	public static void main(String[] args) {
		String s = "hello";
		String t = "hello";
		char[] c = {'h', 'e', 'l', 'l', 'o'};
		System.out.println(s.equals(t));
		System.out.println(t.equals(c));
		System.out.println(t.equals(new String("hello")));
		System.out.println(s == t);
		System.out.println(s.equals(new String(c)));
	}
}
