package zgl.netty;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyNettyEchoServer {
	private final int port;
	
	public MyNettyEchoServer(int port){
		this.port = port;
	}
	
	public void start() throws InterruptedException, UnknownHostException {
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			//通过创建一个ServerBootstrap类的实例来引导服务器程序。
			ServerBootstrap b = new ServerBootstrap();
			//指定NIO传输（指定NioEventLoopGroup接收和处理新的连接， 指定NioServerSocketChannel作为channel类型），通过InetSocketAddress指定服务端口号
			b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(InetAddress.getLocalHost(), port))
			.childHandler(new ChannelInitializer<SocketChannel>() { //指定有连接时需要创建的子channel，此处使用了ChannelInitializer
				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
					//ChannelPipeline持有所有不同的ChannelHandlers
					arg0.pipeline().addLast(new MyNettyEchoServerHandler());
					
					ChannelPipeline pipeline = arg0.pipeline();
					pipeline.addLast("handlername", new MyNettyEchoClientHandler());
					ChannelHandler handler = pipeline.get("handlername");
					ChannelHandlerContext context = pipeline.context(handler);
					context = pipeline.context("handlername");
					pipeline.fireChannelUnregistered();
					pipeline.fireExceptionCaught(new Throwable());
				}
			});
			//绑定服务器直到绑定完成。sync()方法的调用会导致阻塞直到绑定完成。
			ChannelFuture f = b.bind().sync();
			System.out.println(MyNettyEchoServer.class.getName() + " "
					+ " started and listen on " + f.channel().localAddress());
			//通过调用sync(), 应用程序在服务器channel关闭前会一直阻塞
			f.channel().closeFuture().sync();
			
		}finally{
			//应用程序在服务器执行完成后关闭EventLoopGroup同时释放所有资源
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) throws InterruptedException, UnknownHostException{
		/*if(args.length != 1){
			System.err.println("Usage: " + MyNettyEchoServer.class.getSimpleName() + " <port>");
		}
		int port  = Integer.parseInt(args[0]);*/
		int port = 8088;
		new MyNettyEchoServer(port).start();
	}
}
