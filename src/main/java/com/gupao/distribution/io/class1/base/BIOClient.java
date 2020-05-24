package com.gupao.distribution.io.class1.base;

import com.gupao.utils.StringUtils;
import com.gupao.utils.UUIDUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class BIOClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8080);
        String request = "this is client request message info. reqId = " + UUIDUtils.getUUID();
        System.out.println("客户端请求数据：" + request);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(request.getBytes(StringUtils.DEFAULT_CHARSET));
        outputStream.flush();
        outputStream.close();
    }
}
