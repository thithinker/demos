package zgl.test.basic;

/**
 * 来自《Java程序员面试宝典》，P129.
 * @author yize
 * 2014年7月6日
 */

class Base{
	int i;
	Base(){
		add(1);
		System.out.println("Base: " + i);
	}
	
	void add(int v){
		i += v;
		System.out.println("Base add: " + i);
	}
	
	void print(){
		System.out.println(i);
	}
}

class MyBase extends Base{
	MyBase(){
		add(2);
	}
	void add(int v){
		i += v * 2;
		System.out.println("MyBase add: " + i);
	}
}
public class Test_03 {
	public static void main(String[] args) {
		go(new MyBase());
	}
	
	static void go(Base b){
		b.add(8);
	}
}
