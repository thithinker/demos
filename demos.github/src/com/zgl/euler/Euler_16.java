package com.zgl.euler;

import java.math.BigInteger;

public class Euler_16 {
	public int euler_16(){
		int sum = 0;
		final BigInteger TWO = new BigInteger("2");
		BigInteger bi = TWO.pow(1000);
		String biString = bi.toString();
		for(int i = 0; i < biString.length(); i++){
			sum += biString.charAt(i) - '0';
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(new Euler_16().euler_16());
	}
}
