package com.zgl.thread;

public class Waiter implements Runnable{
	private Message msg;
	public Waiter(Message msg){
		this.msg = msg;
	}
	
	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		synchronized (msg) {
			try{
				System.out.println(name + " wait to get notifed " + System.currentTimeMillis());
				msg.wait();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			System.out.println(name + " waiter thread got notified at " + System.currentTimeMillis());
			//process the message now
			System.out.println(name + " processed: " + msg.getMsg());
			
		}
		
	}
}
