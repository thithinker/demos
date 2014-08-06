package zgl.netty;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用Future的方式实现异步（另一种是使用回调的方式实现异步处理）
 * @author yize
 * 2014年5月29日
 */
public class FutureExample {
	private void test01(){
		ExecutorService executor = Executors.newCachedThreadPool();
		Runnable task1 = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println("Task 1");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Callable<Integer> task2 = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(2000);
				System.out.println("Task 2");
				return new Random().nextInt();
			}
		};
		
		Future<?> future1 = executor.submit(task1);
		Future<Integer> future2 = executor.submit(task2);
		
		while(!future1.isDone() || !future2.isDone()){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("waiting");
		}
		try {
			System.out.println("task2 got " + future2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
		System.out.println("shutdown");
	}

	public static void main(String[] args) {
		new FutureExample().test01();
	}

}
