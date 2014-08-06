package zgl.netty;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyNettyEchoClient {
	private final String host;
	private final int port;
	
	public MyNettyEchoClient(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	public void start() throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			.remoteAddress(new InetSocketAddress(InetAddress.getLoopbackAddress(), port))
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel arg0) throws Exception {
					arg0.pipeline().addLast(new MyNettyEchoClientHandler());
					System.out.println("bind");
				}
			});
			ChannelFuture f = b.connect().sync();
			f.channel().closeFuture().sync();
		}finally{
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) throws Exception {
		/*if(args.length != 2){
			System.err.println("Usage: " + MyNettyEchoClient.class.getSimpleName()
					+ " <host> <port>");
			return;
		}
		final String host = args[0];
		final int port = Integer.parseInt(args[1]);*/
		String host = "127.0.0.1";
		int port = 8088;
		new MyNettyEchoClient(host, port).start();;
		
	}
}
