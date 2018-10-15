package com.niezhiliang.wei.pay.utils;


import java.util.UUID;

/**
 * @Author NieZhiLiang
 * @Email nzlsgg@163.com
 */
public class UuidUtils {
    /**
     * 获取没有-的uuid
     * @return
     */
    public static String getRandUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
