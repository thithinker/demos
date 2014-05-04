package com.zgl.thread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
	private BlockingQueue<Message> queue;
	
	public Producer(BlockingQueue<Message> q){
		queue = q;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 100; i++){
			Message msg = new Message("" + i);
			try{
				Thread.sleep(new Random().nextInt(100));
				queue.put(msg);
				System.out.println("Produced " + msg.getMsg());
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		Message msg = new Message("exit");
		try{
			queue.put(msg);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
