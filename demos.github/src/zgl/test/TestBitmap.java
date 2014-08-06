package zgl.test;

import java.util.BitSet;
import java.util.Random;

public class TestBitmap {
	public static void main(String[] args) {
		BitSet bs = new BitSet();
		for(int i = 0 ; i < 100;){
			bs.set(i);
			i += new Random().nextInt(10);
		}
		System.out.println(bs);
	}
}
