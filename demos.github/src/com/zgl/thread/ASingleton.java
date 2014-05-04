package com.zgl.thread;

/**
 * @date 2014.4.17
 * @author yize
 *
 */
public class ASingleton {
	private static ASingleton instance;
	private static Object mutex = new Object();
	
	private ASingleton(){}
	
	public static ASingleton getInstance(){
		if(instance == null){
			synchronized(mutex){
				if(instance == null)
					instance = new ASingleton();
			}
		}
		return instance;
	}
}
