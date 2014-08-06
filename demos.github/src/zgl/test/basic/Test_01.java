package zgl.test.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 来自《Java程序员面试宝典》，原题答案是错的
 * @author yize
 * 2014年7月5日
 */
public class Test_01 {
	private String value = null;
	public Test_01(String v){
		value = v;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}
		if(obj instanceof Test_01){
			Test_01 t = (Test_01)obj;
			return value.equals(t.value);
		}
		return false;
	}
	
	public static void main(String[] args) {
		List<Test_01> list = new ArrayList<>();
		Test_01 t1 = new Test_01("object");
		Test_01 t2 = new Test_01("object");
		Test_01 t3 = new Test_01("object");
		Object t4 = new Test_01("object");
		list.add(t1);
		
		System.out.println(t1==t2);
		System.out.println(list.contains(t2));
		System.out.println(t2.equals(t3));
		System.out.println(t3.equals(t4));
		System.out.println(t4.equals(t3));
	}
}
