package com.lechinepay.framework.util;

import java.io.File;

public class RoutePath {

    /**
     * 路由目录算法，共5层，每层文件名长度为4 如long最大值9223372036854775807处理后为/0922/3372/0368/5477/5807
     * 
     * @param id
     * @return
     */
    public static String routeCertPath(Long id) {
        if (null == id || id < 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(39);
        sb.append("00000000000000000000").append(id);
        sb.delete(0, sb.length() - 20);
        for (int i = 0, loop = 0; i < 20; i = i + 4, loop++) {
            sb.insert(i + loop, File.separatorChar);
        }
        return sb.toString();
    }

    public static String formatCertPath(Long id) {
        if (null == id || id < 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(39);
        sb.append("00000000000000000000").append(id);
        sb.delete(0, sb.length() - 20);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(Long.MAX_VALUE).length());
        System.out.println((Long.MAX_VALUE));
        System.out.println(routeCertPath(Long.MAX_VALUE));
        System.out.println(routeCertPath(123456789L));
        System.out.println(routeCertPath(15002L));
        System.out.println(formatCertPath(15002L));

        File file = new File("/home/abacusli/cert" + routeCertPath(Long.MAX_VALUE));
        file.mkdirs();

        file = new File("/home/abacusli/cert" + routeCertPath(123456789L));
        file.mkdirs();
    }

}
