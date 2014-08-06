package zgl.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i < 10; i++){
			Runnable runnable = new WorkThread("" + i);
			executorService.execute(runnable);
		}
		executorService.shutdown();
		while(!executorService.isTerminated()){}
		System.out.println("finish");
		
		
	}
}
