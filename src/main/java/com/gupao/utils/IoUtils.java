package com.gupao.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.server.ExportException;

public class IoUtils {

    private IoUtils() {
    }

    public static byte[] read(InputStream inputStream) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int num = inputStream.read(buffer);
        while (num != -1) {
            bout.write(buffer, 0, num);
            num = inputStream.read(buffer);
        }
        bout.flush();
        return bout.toByteArray();
    }
}
