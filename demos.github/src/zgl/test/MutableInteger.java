package zgl.test;

import java.lang.reflect.Field;

/**
 * Show that Integers are mutable if you allow reflection
 * @author yize
 * 2014年6月5日
 */
public class MutableInteger {
	public static void main(String[] args) {
		Integer x = new Integer("11");
		System.out.println(x);
		mutate(x);
		System.out.println(x);
	}
	
	public static void mutate(Integer x){
		try {
			Field value = Integer.class.getDeclaredField("value");
			value.setAccessible(true);
			value.setInt(x, 123234);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
}
