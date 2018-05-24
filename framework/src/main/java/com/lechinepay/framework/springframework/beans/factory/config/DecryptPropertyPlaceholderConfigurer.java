/** 
 * 文件名：DecryptPropertyPlaceholderConfigurer.java
 * 创建时间：2017年5月4日 下午5:02:30
 * 描述：用一句话描述该文件
 * 版权所有：浙江乐信付金融服务有限公司
 * @author abacusli
 */
package com.lechinepay.framework.springframework.beans.factory.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.lechinepay.framework.security.util.AESCodec;

/**
 * @ClassName DecryptPropertyPlaceholderConfigurer
 * @Description 用一句话描述该类的作用
 * @author abacusli
 * @date 2017年5月4日 下午5:02:30
 */
public class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DecryptPropertyPlaceholderConfigurer.class);

    private String              secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptPropertyValue(propertyName)) {
            try {
                return AESCodec.decrypt(propertyValue, null == secretKey ? AESCodec.SECRET_KEY : secretKey);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                return propertyValue;
            }
        } else {
            return super.convertProperty(propertyName, propertyValue);
        }
    }

    protected boolean isEncryptPropertyValue(String propertyName) {
        return propertyName.startsWith("encrypt_") || propertyName.startsWith("encrypt.");
    }
}
