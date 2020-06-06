package com.gupao.distribution.io.classs2.buffer;

import java.nio.ByteBuffer;

public class ByteBufferSlice {

    /**
     * 分片
     *
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 给缓冲区填充数据
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        System.out.println(buffer);

        // 截取子缓冲区 5-9
        buffer.position(5);
        buffer.limit(10);
        final ByteBuffer slice = buffer.slice();
        System.out.println(buffer);
        for (int i = buffer.position(); i < buffer.limit(); i++) {
            System.out.println("位置i=" + i + "的值为：" + buffer.get());
        }

        System.out.println("===================");
        // 现在slice的情况是啥[pos=0 lim=5 cap=5]
        System.out.println(slice);
        for (int i = 0; i < slice.limit(); i++) {
            System.out.println("位置i=" + i + "的值为：" + slice.get());
        }

        // 修改子缓冲区内容
        slice.flip();
        System.out.println(slice);
        for (int i = 0; i < slice.limit(); i++) {
            // 这里为啥要指定index，是因为get和put操作之后position都会+1
            slice.put(i, (byte) (slice.get() + 5));
        }
        slice.flip();
        for (int i = 0; i < slice.limit(); i++) {
            System.out.println("位置i=" + i + "的值为：" + slice.get());
        }

        // 此时buffer的情况是：[pos=10 lim=10 cap=16]
        System.out.println(buffer);
    }
}
