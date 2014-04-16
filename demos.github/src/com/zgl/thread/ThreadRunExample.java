package com.zgl.thread;

public class ThreadRunExample {
	public static void main(String[] args) {
		Thread t1 = new Thread(new HeavyWorkRunnable(), "t1");
		Thread t2 = new Thread(new HeavyWorkRunnable(), "t2");
		System.out.println("Starting Runnable threads");
		t1.start();
		t2.start();
		System.out.println("Runnable Threads have been started");
		
		Thread t3 = new MyThread("t3");
		Thread t4 = new MyThread("t4");
		System.out.println("Starting MyThreads");
		t3.start();
		t4.start();
		System.out.println("MyThreads have been started");
		
		
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(0, 1500);
			System.out.println(System.currentTimeMillis() - start);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
