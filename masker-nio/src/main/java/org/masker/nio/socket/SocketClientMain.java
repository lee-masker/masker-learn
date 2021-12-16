
package org.masker.nio.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * @mudule:
 * @since：2021/12/15 16:38
 */
public class SocketClientMain {

    public static void main(String[] args) throws IOException {
        for (Integer i = 0; i < 1; i++){
            Socket socket = new Socket();
            socket.bind(new InetSocketAddress("",7881 + i));
            socket.connect(new InetSocketAddress(7880));
            System.out.println("链接完成 "+ i + new Date());
            OutputStream ops = socket.getOutputStream();
            ops.write(new byte[]{1,3,4,5,5,6,78,9,i.byteValue()});
            System.out.println("发送消息完成 "+ i + new Date());
        }

    }

}
