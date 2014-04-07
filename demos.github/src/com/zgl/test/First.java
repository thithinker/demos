package com.zgl.test;

import java.math.BigInteger;
import java.util.Random;

public class First {
	public static void main(String[] args){
		System.out.println("Hello, yize");
		System.out.println(BigInteger.probablePrime(10, new Random()));
	}
}
