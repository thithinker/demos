package netty;

import java.io.File;
import java.io.FileInputStream;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf bb = ctx.alloc().buffer(4);
		bb.writeBytes(new FileInputStream(new File("./object.txt")), 12);
		bb.writeChar('\n');
		ctx.write(bb);
		ctx.write(msg);
		
		ctx.flush();
		/*ByteBuf in = (ByteBuf) msg;
		try{
			while(in.isReadable()){
				System.out.print((char) in.readByte());
				System.out.flush();
				
				
			}
		}finally{
			ReferenceCountUtil.release(msg);
		}*/
	}
	
/*	//rdate -o <port> -p <host>
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		final ByteBuf time = ctx.alloc().buffer(4);
		time.writeInt((int)(System.currentTimeMillis() / 1000L + 2208988800L));
		final ChannelFuture f = ctx.writeAndFlush(time);
		f.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				assert f == future;
				ctx.close();
			}
		});
	
	}*/
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
