package zgl.thread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
	
	BlockingQueue<Message> queue;
	
	public Consumer(BlockingQueue<Message> q){
		queue = q;
	}
	
	@Override
	public void run() {
		try{
			Message msg;
			while((msg = queue.take()).getMsg() != "exit"){
				Thread.sleep(new Random().nextInt(100));
				System.out.println("Consumed " + msg.getMsg());
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}
