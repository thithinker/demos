package com.zgl.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class PlainNio2EchoServer {
	public void server(int port) throws IOException{
		System.out.println("Listening for connections on port " + port);
		final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
		InetSocketAddress address = new InetSocketAddress(port);
		serverChannel.bind(address);
		final CountDownLatch latch = new CountDownLatch(1);
		serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
			@Override
			public void completed(AsynchronousSocketChannel result, Object attachment) {
				serverChannel.accept(null, this);
				ByteBuffer buffer = ByteBuffer.allocate(100);
				result.read(buffer, buffer, new EchoCompletionHandler(result));
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				try{
					serverChannel.close();
				}catch(IOException e){
					e.printStackTrace();
				}finally{
					latch.countDown();
				}
			}
		});
		try{
			latch.wait();
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}
	
	private final class EchoCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{
		private final AsynchronousSocketChannel channel;
		
		public EchoCompletionHandler(AsynchronousSocketChannel channel){
			this.channel = channel;
		}
		@Override
		public void completed(Integer result, ByteBuffer attachment) {
			attachment.flip();
			channel.write(attachment, attachment, new CompletionHandler<Integer, ByteBuffer>() {
				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					if(attachment.hasRemaining()){
						channel.write(attachment, attachment, this);
					}else{
						attachment.compact();
						channel.read(attachment, attachment, EchoCompletionHandler.this);
					}
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					try{
						channel.close();
					}catch(IOException e){
						
					}
				}
			});
		}

		@Override
		public void failed(Throwable exc, ByteBuffer attachment) {
			try{
				channel.close();
			}catch(IOException e){
				
			}
		}
		
	}
}
