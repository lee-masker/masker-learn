package org.masker.netty.first.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @mudule: 处理器
 * @since：2021/12/16 13:23
 */
@Slf4j
@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive in ");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello netty you are welcome!".getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
        log.info("channelRead0 received data is {}",in.toString(CharsetUtil.UTF_8));
    }
}
