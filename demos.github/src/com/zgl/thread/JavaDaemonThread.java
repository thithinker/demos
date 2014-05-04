package com.zgl.thread;

/**
 * @date 2014.4.17
 * @author yize
 *
 */
public class JavaDaemonThread {
	public static void main(String[] args) {
		Thread dt = new Thread(new DaemonThread(), "dt");
		dt.setDaemon(true);
		dt.start();
		
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Finishing Program");
	}
	
	
}

class DaemonThread implements Runnable{
	@Override
	public void run() {
		
		while(true){
			processSomething();
		}
	}
	
	void processSomething(){
		try{
			System.out.println("Daemon Thread processing...");
			Thread.sleep(5000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
