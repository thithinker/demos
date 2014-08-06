package zgl.test;

import java.util.Arrays;

import zgl.test.OuterClass.InnerClass;
import zgl.test.OuterClass.StaticNestedClass;

public class OuterClassTest {
	public static void main(String[] args) {
		OuterClass outer = new OuterClass(1, 2, 3, 4);
		
		//static nested classes example
		StaticNestedClass staticNestedClass = new StaticNestedClass();
		StaticNestedClass staticNestedClass2 = new StaticNestedClass();
		
		System.out.println(staticNestedClass.getName());
		staticNestedClass.d = 10;
		System.out.println(staticNestedClass.d);
		System.out.println(staticNestedClass2.d);
		
		//inner class example
		InnerClass innerClass = outer.new InnerClass();
		System.out.println(innerClass.getName());
		System.out.println(innerClass);
		innerClass.setValues();
		System.out.println(innerClass);
		
		//calling method using local inner class
		outer.print("Outer");
		
		//calling method using anonymous inner class
		System.out.println(Arrays.toString(outer.getFilesInDir("src/com/zgl/test", ".java")));
		
		System.out.println(Arrays.toString(outer.getFilesInDir("bin/com/zgl/test", ".class")));
		
	}
	
	
	
	
}
