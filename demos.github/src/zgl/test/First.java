package zgl.test;

import java.math.BigInteger;
import java.util.Random;

public class First {
	public static void main(String[] args){
		System.out.println("Hello, yize");
		long t1 = System.currentTimeMillis();
		System.out.println(BigInteger.probablePrime(95, new Random()));
		long t2 = System.currentTimeMillis();
		System.out.println(BigInteger.probablePrime(94, new Random()));
		long t3 = System.currentTimeMillis();
		System.out.println(t2 - t1 + " " + (t3 - t2));
		System.out.println(System.currentTimeMillis() + " \n" + System.nanoTime());
		
		int n = 3;
		for(int i = 0; i < 100; i++){
			n = n++;
		}
		System.out.println(n);
	}
	
	public static void t(){
	}
}
