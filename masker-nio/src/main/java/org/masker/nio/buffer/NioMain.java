package org.masker.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.ShortBuffer;

/**
 * @mudule: 应用模块名称
 * @since：2021/12/15 16:33
 */
public class NioMain {

    public static void main(String[] args) {
        byte[] byteArray = new byte[]{1,3,4,5,5,6,78,9,0};
        short[] shortArray = new short[]{1,3,4,5,5,6,0};
        char[] chartArray = new char[]{'a','d','g','j','k','g','f','j','d','j'};

        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        ShortBuffer shortBuffer = ShortBuffer.wrap(shortArray);
        CharBuffer charBuffer = CharBuffer.wrap(chartArray);

        printBuffer(byteBuffer);
        printBuffer(shortBuffer);
        printBuffer(charBuffer);

    }


    public static void printBuffer(Buffer buffer){
        System.out.println("array Object is " + buffer.array());
        System.out.println("arrayOffset int is " + buffer.arrayOffset());
        System.out.println("limit int is " + buffer.limit());
        System.out.println("capacity int is " + buffer.capacity());
        System.out.println("position int is " + buffer.position());
        System.out.println();
    }
}
