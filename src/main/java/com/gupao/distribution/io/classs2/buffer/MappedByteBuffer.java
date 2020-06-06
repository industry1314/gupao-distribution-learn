package com.gupao.distribution.io.classs2.buffer;

import com.gupao.distribution.io.classs2.TestInfos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * IO映射缓冲区
 * 把缓冲区跟文件系统做了一个映射，只要修改缓冲区的内容，文件中的内容也同步变化。
 */
public class MappedByteBuffer {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(TestInfos.TEST_FILE_PATH, "rw");
        FileChannel channel = randomAccessFile.getChannel();

        java.nio.MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 26);
        // 将index为0的内容改为a
        mappedByteBuffer.put(0, (byte) 97);
        // 将index为25的内容改为z
        mappedByteBuffer.put(25, (byte) 122);
    }
}
