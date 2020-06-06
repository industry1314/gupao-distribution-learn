package com.gupao.distribution.io.classs2.channel;

import com.gupao.distribution.io.classs2.TestInfos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileOutput {

    static private final byte message[] = {83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46};

    public static void main(String[] args) throws IOException {
        FileOutputStream fout = new FileOutputStream(TestInfos.TEST_FILE_PATH_OUT);
        FileChannel channel = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.wrap(message);
        channel.write(buffer);

        for (byte bytes : message) {
            System.out.print((char) bytes);
        }

    }
}
