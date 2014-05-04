package com.zgl.vm;

/**
 * @author yize
 * @date May 4, 2014
 * 产生StackOverflowError
 * VM Args: -Xss128k
 */
public class JavaVMStackSOF {
	private int stackLength = 1;
	
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try{
			oom.stackLeak();
		}catch(Throwable e){
			System.out.println("StackLenth: " + oom.stackLength);
			throw e;
		}
	}
}
