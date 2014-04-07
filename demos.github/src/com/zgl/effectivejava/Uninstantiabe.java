package com.zgl.effectivejava;

public class Uninstantiabe {
	private Uninstantiabe(){
		throw new AssertionError();
	}
	
	public static void add(int x, int y){
		System.out.println(x + y);
	}
	
	public static void main(String[] args) {
		Uninstantiabe.add(12, 34);
	}
}
