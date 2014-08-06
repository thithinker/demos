package zgl.thread;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @date 2014.4.17
 * @author yize
 *
 */
public class ThreadLocalExample implements Runnable{
	private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyddMM ddmm");
		};
	};
	
	private static ThreadLocal<Integer> n = new ThreadLocal<>();

	public static void main(String[] args) throws InterruptedException {
		ThreadLocalExample obj = new ThreadLocalExample();
		for(int i = 0; i <= 10; i++){
			Thread t = new Thread(obj, "" + i);
			Thread.sleep(new Random().nextInt(1000));
			t.start();
		}
	}
	
	@Override
	public void run() {
		System.out.println("Thread name = " + Thread.currentThread().getName() + 
				" formatter: "+ formatter.get().toPattern() + " N:" + n.get());
		n.set(new Random().nextInt(1000));
		try{
			Thread.sleep(new Random().nextInt(1000));
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		formatter.set(new SimpleDateFormat());
		System.out.println("Thread name = " + Thread.currentThread().getName() + 
				" formatter: "+ formatter.get().toPattern() + " N: " + n.get());
	}
}
