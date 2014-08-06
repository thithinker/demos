package com.zgl.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class PlainEchoServer {
	private int port;
	
	public PlainEchoServer(int port){
		this.port = port;
	}
	
	public void server(){
		try {
			@SuppressWarnings("resource")
			final ServerSocket serverSocket = new ServerSocket(port, 1024, InetAddress.getLocalHost());
			while(true){
				final Socket clientSocket = serverSocket.accept();
				System.out.println("Accept connetion from " + clientSocket);
				
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							System.out.println("In server");
							//BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
							//System.out.println("*********** " + reader.readLine());
							PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
							writer.write("this is server print");
							writer.flush();
							
							//String line;
							/*while((line = reader.readLine()) != null){
								System.out.println("Server: " + line);
								writer.write(line);
								writer.flush();
							}*/
							try{
								clientSocket.close();
							}catch(Exception e1){
								e1.printStackTrace();
							}
						} catch (IOException e) {
							
							e.printStackTrace();
						}
					}
				}).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
