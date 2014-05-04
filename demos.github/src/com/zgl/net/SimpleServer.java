package com.zgl.net;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
	public static void main(String[] args){
		try {
			ServerSocket serverSocket = new ServerSocket(30000, 1024, InetAddress.getLoopbackAddress());
			while(true){
				Socket s = serverSocket.accept();
				new Thread(new ServerThread(s)).start();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

class ServerThread implements Runnable{
	private Socket s = null;
	public ServerThread(Socket ss){
		s = ss;
	}
	@Override
	public void run() {
		OutputStream out;
		String name = Thread.currentThread().getName();
		try {
			out = s.getOutputStream();
			PrintStream print = new PrintStream(out);
			print.println("this is just for test" + " from " + name);
			print.println("port: " + s.getPort() + " from " + name);
			print.println("local port" + s.getLocalPort() + " from " + name);
			out.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
