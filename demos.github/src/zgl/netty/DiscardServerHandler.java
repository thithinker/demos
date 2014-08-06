package zgl.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg){
		//((ByteBuf) msg).release();
		ByteBuf in = (ByteBuf) msg;
		try{
			while(in.isReadable()){
				System.out.println((char) in.readByte());
				System.out.flush();
			}
		}finally{
			ReferenceCountUtil.release(msg);
		}
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
