/** 
 * 文件名：AESCodecTest.java
 * 创建时间：2017年5月5日 上午9:20:14
 * 描述：用一句话描述该文件
 * 版权所有：浙江乐信付金融服务有限公司
 * @author abacusli
 */
package com.lechinepay.framework.security.util;

/**
 * @ClassName AESCodecTest
 * @Description 用一句话描述该类的作用
 * @author abacusli
 * @date 2017年5月5日 上午9:20:14
 */
public class AESCodecTest {

    public static void main(String[] args) {
        String[] argss = new String[] { "123456dev_userxxMMLePay", "7D1aBvl/dUR8djbvjw7WJA==" };
        try {
            AESCodec.main(argss);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// key:Tdr5oxQICBCh7Gb+vRscCA==
// 原文：notify123456LePaywsxzaq!QAZXSW
// 原文长度：30
// 加密：4GFOYQPVhfXqT/QG2caErFoSB2tZw249LsWSgXyiYyY=
// 加密后长度：44
// 解密: notify123456LePaywsxzaq!QAZXSW