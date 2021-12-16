
package org.masker.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @mudule:
 * @since：2021/12/15 16:38
 */
public class SocketChannelClientMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (Integer i = 0; i < 1; i++){
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.bind(new InetSocketAddress("",8884 + i));
            socketChannel.connect(new InetSocketAddress(8880));
            System.out.println("链接完成 "+ i + new Date());
            socketChannel.write(ByteBuffer.wrap(new byte[]{1,3,4,5,5,6,78,9,i.byteValue()}));
            System.out.println("发送消息完成 "+ i + new Date());
            Thread.sleep(5000L);

            socketChannel.write(ByteBuffer.wrap(new byte[]{19,i.byteValue()}));
            System.out.println("发送消息完成 "+ i + new Date());
            socketChannel.close();
        }

    }

}
