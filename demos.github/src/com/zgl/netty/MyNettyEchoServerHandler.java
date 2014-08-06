package com.zgl.netty;


import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufProcessor;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.ReferenceCountUtil;

/**
 * @author yize
 * 2014年5月30日
 */
//@Sharable注解：通道(channel)间共享
@Sharable
public class MyNettyEchoServerHandler extends ChannelInboundHandlerAdapter{
	static{
		System.err.println("In MyNettyEchoServerHandler");
	}

	@SuppressWarnings("unused")
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = ((ByteBuf)msg);
		
		/*ByteBuf heapBuf = (ByteBuf) msg;
		if(heapBuf.hasArray()){
			byte[] array = heapBuf.array();
			int offset = heapBuf.arrayOffset();
			int length = heapBuf.readableBytes();
			
			myMethod(array, offset, length);
		}
		
		ByteBuf directBuf = (ByteBuf) msg;
		if(!directBuf.hasArray()){
			int length = directBuf.readableBytes();
			byte[] array = new byte[length];
			directBuf.getBytes(0, array);
			myMethod(array, 0, array.length);
		}*/
		
		ByteBuf newBuf = Unpooled.copiedBuffer("string to be stored in bytebuf", Charset.forName("UTF-8"));
		ByteBuf newBuf2 = ByteBufAllocator.DEFAULT.buffer();
		newBuf2.writeInt(1234);
		
		
		while(in.isReadable()){
			System.out.print((char)in.readByte());
		}
		//System.out.println("From client:" + s);
		//System.out.println("Read data");
		System.out.println(ctx);
		ByteBuf bb = ctx.alloc().buffer(4);	
		String st = "this world";
		bb.writeBytes(st.getBytes());
		ctx.write(newBuf2);
		
		ReferenceCountUtil.release(msg);
		ReferenceCountUtil.release(newBuf);
		
	}
	
	/*private void myMethod(byte[] array, int i, int length) {
		// TODO
	}*/

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//刷新所有之前写的信息到远程连接，操作完成后关闭通道（ctx.close()）
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
	}
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
