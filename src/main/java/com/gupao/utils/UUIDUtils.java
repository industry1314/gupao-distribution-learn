package com.gupao.utils;

import java.util.UUID;

public final class UUIDUtils {

    private UUIDUtils() {
    }

    /**
     * 生成一个简单UUID流水号，不包含‘-’
     * @return
     * @throws Exception
     */
    public static String getUUID() throws Exception{
        return UUID.randomUUID().toString().replace("-","");
    }
}
