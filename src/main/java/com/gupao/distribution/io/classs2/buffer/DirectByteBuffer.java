package com.gupao.distribution.io.classs2.buffer;

import com.gupao.distribution.io.classs2.TestInfos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接缓冲区
 */
public class DirectByteBuffer {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream(TestInfos.TEST_FILE_PATH);
        FileChannel finChannel = fin.getChannel();

        FileOutputStream fout = new FileOutputStream("E:/❤❤❤❤❤❤工作与学习❤❤❤❤❤❤/develop_station/test_out.txt");
        final FileChannel foutChannel = fout.getChannel();

        // 直接缓冲区（Zero Copy-零拷贝，不需要先从内存拷贝到JVM内存）
        ByteBuffer buffer = ByteBuffer.allocateDirect(26);

        while (true) {
            // 如果文件中内容不够26个byte，则全部一次性就全部读取到缓冲区；如果超过了缓冲区大小，则每次最多读取缓冲区大小的数据。
            int length = finChannel.read(buffer);
            System.out.println("本次读取的长度是：" + length);
            if (length == -1) {
                break;
            }
            buffer.flip(); // 这里为啥是flip，是因为只需要读取有效的内容即可。
            foutChannel.write(buffer);
            buffer.clear(); // 这里为啥需要clear，是因为需要重置了后像缓冲区写入内容。
        }
        System.out.println("execute over...");
    }
}
