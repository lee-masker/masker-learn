package org.masker.netty.first;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.masker.netty.first.handler.EchoClientHandler;
import org.masker.netty.first.handler.EchoServerHandler;

import java.net.InetSocketAddress;

/**
 * @mudule: 应用模块名称
 * @since：2021/12/16 14:04
 */
@Slf4j
public class EchoClientMain {

    private final int port;

    public EchoClientMain(int port){
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new EchoClientMain(8089).start();
    }


    public void start() throws Exception {
        log.info("EchoClientMain start ......");
        // 创建 EventLoopGroup
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建 ServerBootstrap
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    //
                    .channel(NioSocketChannel.class)
                    // 使用指定的端口设置套接字地址
                    .remoteAddress(new InetSocketAddress("127.0.0.1",port))
                    // 添加一个EchoServerHandler 到一个子 channel的 channelPipLine
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // EchoServerHandler 被标注为 @Sharable 所以我们可以总是使用同样的实例 ？？
                            socketChannel.pipeline().addLast( new EchoClientHandler());
                        }
                    });
            // 连接到远程节点 阻塞等待直到连接完成
            log.info("EchoClientMain 连接到远程节点 阻塞等待直到连接完成");
            ChannelFuture channelFuture = bootstrap.connect().sync();

            // 获取Channel的 CloseFuture 并且阻塞当前线程直到它完成
            log.info("EchoClientMain 连接到远程节点 阻塞等待直到连接完成");
            channelFuture.channel().closeFuture().sync();
        }finally {
            // 关闭 EventLoopGroup 释放所有资源
            group.shutdownGracefully().sync();
        }
    }

}
