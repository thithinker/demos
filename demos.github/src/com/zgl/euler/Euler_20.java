package com.zgl.euler;

import java.math.BigInteger;

/**
 * @date 2014.4.7
 * @author yize
 *
 */
public class Euler_20 {
	public int euler_20(int n){
		BigInteger bi = new BigInteger(String.valueOf(n));
		
		while(--n > 0){
			bi = bi.multiply(new BigInteger(String.valueOf(n)));
		}
		String multiply = bi.toString();
		int sum = 0;
		for(int i = 0; i < multiply.length(); i++){
			sum += multiply.charAt(i) - '0';
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		System.out.println(new Euler_20().euler_20(100));
	}
}
