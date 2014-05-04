package com.zgl.vm;

/**
 * @author yize
 * @date May 4, 2014
 * 创建线程导致OutOfMemoryError
 * VM Args: -Xss2M
 * 似乎和Windows平台不一样
 */
public class JavaVMStackOOM {
	private void dontStop(){
		while(true){
			
		}
	}
	
	public void stackLeakByThread(){
		while(true){
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			t.start();
		}
	}
	
	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}
