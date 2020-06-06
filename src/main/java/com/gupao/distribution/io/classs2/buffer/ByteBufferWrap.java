package com.gupao.distribution.io.classs2.buffer;

import java.nio.ByteBuffer;

public class ByteBufferWrap {

    /**
     * 将byte数组封装成Buffer缓冲区
     *
     * @param args
     */
    public static void main(String[] args) {
        byte[] bytes = new byte[16];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
    }
}
