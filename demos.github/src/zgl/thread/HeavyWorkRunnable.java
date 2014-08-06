package zgl.thread;

/**
 * @date 2014.4.16
 * @author yize
 * To use this class as Thread, we need to create a Thread object by passing object of this runnable
 * 		class and then call start() method to execute the run() method in a separate thread.
 */
public class HeavyWorkRunnable implements Runnable{
	@Override
	public void run() {
		try{
			Thread.sleep(1000);
			doDBProcessing();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Doing heavy processing -END " + Thread.currentThread().getName());
	}
	
	private void doDBProcessing() throws InterruptedException{
		Thread.sleep(5000);
	}
}
