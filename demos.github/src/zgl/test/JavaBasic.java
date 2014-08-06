package zgl.test;

class Value{
	public int i = 15;
}

public class JavaBasic {
	Value v = new Value();
	public static void main(String[] args) {
		JavaBasic jb = new JavaBasic();
		jb.first();
	}
	
	public void first(){
		int i = 5;
		Value v = new Value();
		v.i = 25;
		second(this.v, i);
		System.out.println(v.i);
	}
	
	public void second(Value v, int i){
		i = 0;
		v.i = 20;
		System.out.println(this.v == v);
		Value val = new Value();
		v = val;
		System.out.println(v.i + " " + i);
	}
}
