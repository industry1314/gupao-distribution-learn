package com.gupao.distribution.io.classs2.channel;

import com.gupao.distribution.io.classs2.TestInfos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

public class FileInput {

    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream(TestInfos.TEST_FILE_PATH);
        FileChannel channel = fin.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(128);
        channel.read(buffer);

        buffer.flip();
        while (buffer.remaining() > 0) {
            System.out.print((char)buffer.get());
        }
    }
}
