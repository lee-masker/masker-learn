package org.masker.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @mudule: 应用模块名称
 * @since：2021/12/15 16:38
 */
public class SocketChannelServerMain {

    public static void main(String[] args) throws IOException, InterruptedException {


    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    Selector selector = Selector.open();

    // 绑定端口
    serverSocketChannel.socket().bind(new InetSocketAddress(8880));

    // 设置 serverSocketChannel 为非阻塞模式
    serverSocketChannel.configureBlocking(false);

    // 注册 serverSocketChannel 到 selector，关注 OP_ACCEPT 事件
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    while (true) {
        // 没有事件发生
        selector.select();

        Thread.sleep(1000L);
        // 有事件发生，找到发生事件的 Channel 对应的 SelectionKey 的集合
        Set<SelectionKey> selectionKeys = selector.selectedKeys();

        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        System.out.println("一共有：" + selectionKeys.size());
        while (iterator.hasNext()) {
            SelectionKey selectionKey = iterator.next();

            // 发生 OP_ACCEPT 事件，处理连接请求
            if (selectionKey.isAcceptable()) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                //socketChannel
                // 将 socketChannel 也注册到 selector，关注 OP_READ
                // 事件，并给 socketChannel 关联 Buffer
                System.out.println("getLocalAddress:" + socketChannel.getLocalAddress());
                System.out.println("getRemoteAddress:" + socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(20));
            }

            // 发生 OP_READ 事件，读客户端数据
            if (selectionKey.isReadable()) {
                SocketChannel channel = (SocketChannel) selectionKey.channel();
                ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                channel.read(buffer);
                System.out.println("msg form client: " + buffer.array());

            }

            // 手动从集合中移除当前的 selectionKey，防止重复处理事件

            iterator.remove();
        }

        selectionKeys.clear();
    }
}

}
