package zgl.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @date 2014.4.17
 * @author yize
 *
 */
public class ThreadSafety {
	public static void main(String[] args) {
		
		ProcessThread pt = new ProcessThread();
		
		Thread t1 = new Thread(pt, "t1");
		Thread t2 = new Thread(pt, "t2");
		
		t1.start();
		t2.start();
		
		try{
			t1.join();
			t2.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		System.out.println(pt.getCount());
		System.out.println(pt.aInteger);
	}
	
	static class ProcessThread implements Runnable{
		AtomicInteger aInteger = new AtomicInteger(0);
		private int count = 0;
		@Override
		public void run() {
			for(int i = 0; i < 5; i++){
				processSomething(i);
				synchronized (this) {
					count++;
				}
				
				aInteger.incrementAndGet();
			}
		}
		
		public int getCount(){
			return this.count;
		}
		
		private void processSomething(int i){
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
}
