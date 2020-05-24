package com.gupao.distribution.io.class1.base;

import com.gupao.utils.IoUtils;
import com.gupao.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端（底层ServerSocket）
 */
public class BIOServer {

    private int port;

    public BIOServer(int port){
        this.port = port;
    }

    public static void main(String[] args) {
        System.out.println("server start...");
        System.out.println("server port is：" + 8080);

        // 启动服务端
        new BIOServer(8080).startServer();
    }

    /**
     * 启动服务端
     */
    private void startServer() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(this.port);
            while(true){
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = IoUtils.read(inputStream);
                inputStream.close();
                String receiveInfo = new String(bytes, StringUtils.DEFAULT_CHARSET);
                System.out.println("服务端接收数据：" + receiveInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
