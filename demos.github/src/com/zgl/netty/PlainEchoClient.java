package com.zgl.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class PlainEchoClient {
	private int port;
	
	public PlainEchoClient(int port){
		this.port = port;
	}
	
	public void client(){
		try {
			Socket socket = new Socket(InetAddress.getLocalHost(), port);
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			writer.write(socket + " got " + new Random().nextInt(100));
			writer.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			System.out.println("From server: " + br.readLine());
			socket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
