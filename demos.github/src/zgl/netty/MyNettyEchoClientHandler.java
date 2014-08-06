package zgl.netty;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.channel.ChannelHandler.Sharable;

@SuppressWarnings("unused")
@Sharable
public class MyNettyEchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{
	static{
		System.err.println("In MyNettyEchoClientHandler: ");
	}
	//与服务器的连接建立后执行
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//ctx.write(Unpooled.copiedBuffer("Netty rocks", CharsetUtil.UTF_8));
		ByteBuf buf = ctx.alloc().buffer(100);
		buf.writeBytes(new ByteArrayInputStream("zgl".getBytes()), 5);
		ctx.write(buf);
		ctx.flush();
	}
	
	//从服务器接收到数据后执行
	@Override
	protected void channelRead0(ChannelHandlerContext arg0, ByteBuf arg1) throws Exception {
		char dd = (char)arg1.readByte();
		System.out.println("Client received: " + dd + " || " + ByteBufUtil.hexDump(arg1.readBytes(arg1.readableBytes())));
	}
	
	//出现异常时执行
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
