package com.gupao.distribution.io.class1.base;

import com.gupao.utils.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 通过BIOClient客户端发送请求过来
 */
public class NIOServer {

    private int port;

    private Selector selector;

    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public NIOServer(int port) {
        this.port = port;
        // 初始化工作
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 绑定服务地址和端口
            serverSocketChannel.bind(new InetSocketAddress(port));
            // 设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("服务已启动，监听端口：" + this.port);
    }

    public static void main(String[] args) {
        new NIOServer(8080).start();
    }

    private void start() {
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                // 轮询当前获得的keys，也就是当前的任务，进行业务处理
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    process(selectionKey);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 业务处理
    private void process(SelectionKey selectionKey) throws IOException {
        // 数据就绪
        if (selectionKey.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            // 从多路复用器中拿到客户端的引用
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            // 读取内容到缓冲区
            int len = socketChannel.read(buffer);
            if (len > 0) {
                buffer.flip();
                byte[] bytes = buffer.array();
                String content = new String(bytes, StringUtils.DEFAULT_CHARSET);
                selectionKey = socketChannel.register(selector, SelectionKey.OP_WRITE);
                selectionKey.attach(content);
                System.out.println("读取到的内容为：" + content);
            }
        } else if (selectionKey.isWritable()) {
            // 从多路复用器中拿到客户端的引用
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            String content = (String) selectionKey.attachment();
            // 添加响应
            socketChannel.write(ByteBuffer.wrap(content.getBytes(StringUtils.DEFAULT_CHARSET)));
            socketChannel.close();
        }
    }
}
