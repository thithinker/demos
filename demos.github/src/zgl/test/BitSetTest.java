package zgl.test;

import java.util.BitSet;

public class BitSetTest {
	public static void main(String[] args) {
		new BitSetTest().test();
	}
	
	private void test(){
		BitSet set = new BitSet(2);
		
		set.set(3);
		set.set(2);
		for(int i = 0; i < set.size(); i++){
			System.out.println(set.get(i));
		}
		System.out.println(set.toString() + " " + set.size());
	}
}
