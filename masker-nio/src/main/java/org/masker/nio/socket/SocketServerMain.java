package org.masker.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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
public class SocketServerMain {

    public static void main(String[] args) throws IOException {


    ServerSocket serverSocketChannel = new  ServerSocket(7880);
        serverSocketChannel.accept();


    while (true) {

    }
}

}
