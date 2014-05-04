package com.zgl.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleClient {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for(int i = 0; i < 5; i++){
			exec.execute(new SocketThread());
		}
		exec.shutdown();
		
		Thread tt = new Thread(new SocketThread(), "tt");
		tt.start();
	}

}

class SocketThread implements Runnable{
	@Override
	public void run() {
		try {
			Socket ss = new Socket(InetAddress.getLoopbackAddress(), 30000);
			ss.setSoTimeout(1000);
			String name = Thread.currentThread().getName();
			BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
			String line = null;
			while((line = br.readLine()) != null){
				System.out.println(name + " get msg: " + line);
			}
			br.close();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
