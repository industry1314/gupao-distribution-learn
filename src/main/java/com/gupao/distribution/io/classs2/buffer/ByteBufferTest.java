package com.gupao.distribution.io.classs2.buffer;

import com.gupao.distribution.io.classs2.TestInfos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBuffer的操作和属性
 */
public class ByteBufferTest {

    public static void main(String[] args) throws Exception {
        FileInputStream fin = new FileInputStream(TestInfos.TEST_FILE_PATH);
        FileChannel channel = fin.getChannel();

        // 第一步：初始化缓存
        ByteBuffer buffer = ByteBuffer.allocate(8);
        output("step1:初始化缓存", buffer);

        // 第二步：读取内容到缓存
        channel.read(buffer);
        output("step2:读取内容到缓存", buffer);

        // 第三步：flip（获取实际有用的范围，比如buffer的后面空间为null，则无用）
        buffer.flip();
        output("step3:flip", buffer);

        // 第四步：读取数据
        while (buffer.remaining() > 0) {
            System.out.print((char) buffer.get());
        }
        output("step4:get", buffer);

        // 将buffer还原为初始化状态
        buffer.clear();
        output("step5:clear", buffer);

        // 关闭流
        fin.close();
    }

    private static void output(String step, ByteBuffer buffer) {
        System.out.println("====" + step + "====");
        System.out.println("capacity = " + buffer.capacity());
        System.out.println("limit = " + buffer.limit());
        System.out.println("position = " + buffer.position());
    }
}
