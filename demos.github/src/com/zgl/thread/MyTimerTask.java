package com.zgl.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @date 2014.4.18
 * @author yize
 *
 */
public class MyTimerTask extends TimerTask{
	@Override
	public void run() {
		System.out.println("Start date " + new Date());
		completeWork();
		System.out.println("End date " + new Date());
	}
	
	void completeWork(){
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TimerTask task = new MyTimerTask();
		//the TimerTask created by Timer as daemon thread
		Timer timer = new Timer(true);
		
		timer.scheduleAtFixedRate(task, 1000, 1000);
		System.out.println("TimerTask started");
		try{
			Thread.sleep(10000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		timer.cancel();
		System.out.println("TimerTask cancelled");
		try{
			Thread.sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}
