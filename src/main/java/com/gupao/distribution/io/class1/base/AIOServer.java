package com.gupao.distribution.io.class1.base;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AIOServer {

    private int port;

    public AIOServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        new AIOServer(8080).start();
    }

    //启动服务
    private void start() throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AsynchronousChannelGroup group = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
        final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        // 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("服务已启动，监听端口为：" + this.port);

        // 准备接收数据，当成功时触发completed方法，失败是触发failed方法
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.clear();
                try {
                    result.read(buffer).get();
                    buffer.flip();
                    result.write(buffer);
                    buffer.flip();
                } catch (Exception e) {
                    System.out.println(e.toString());
                } finally {
                    try {
                        result.close();
                        serverSocketChannel.accept(null, this);
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
                System.out.println("操作完成");
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("操作失败");
            }
        });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
