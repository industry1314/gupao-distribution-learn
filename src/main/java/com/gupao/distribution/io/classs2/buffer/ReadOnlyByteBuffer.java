package com.gupao.distribution.io.classs2.buffer;

import java.nio.ByteBuffer;

/**
 * 只读缓冲区
 * 特性：Creates a new, read-only byte buffer that shares this buffer's content.
 * 注意“shares this buffer's content”，原buffer中内容变化了之后，只读缓冲区的内容随之变化。
 */
public class ReadOnlyByteBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        System.out.println(buffer);
        // 只读缓冲区 Creates a new, read-only byte buffer that shares this buffer's content.
        // 注意“shares this buffer's content”，原buffer中内容变化了之后，只读缓冲区的内容随之变化。
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        try {
            buffer.put(5, (byte) 5);
        } catch (Exception e) {
            System.out.println("buffer操作put抛异常");
        }
        try {
            readOnlyBuffer.put(5, (byte) 5);
        } catch (Exception e) {
            System.out.println("readOnlyBuffer操作put抛异常");
        }

        System.out.println("===================");
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i, (byte) (i * 10));
        }

        for (int j = 0; j < readOnlyBuffer.capacity(); j++) {
            System.out.println(readOnlyBuffer.get(j));
        }
    }
}
