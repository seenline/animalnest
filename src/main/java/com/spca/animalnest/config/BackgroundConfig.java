package com.spca.animalnest.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties(prefix = "background")
public class BackgroundConfig implements InitializingBean {

    public static String ACCESS_KEY1;
    public static String ACCESS_KEY2;
    private String appId;
    private String appSecret; //微信端帐号主体密钥

    public static String ACCESS_KEY3;
    public static String ACCESS_KEY4;
    public static String ACCESS_KEY5;
    private String aliId;
    private String aliKey;
    private String bucketName;//阿里云帐号主体密钥与实例

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY1 = appId;
        ACCESS_KEY2 = appSecret;
        ACCESS_KEY3=aliId;
        ACCESS_KEY4=aliKey;
        ACCESS_KEY5=bucketName;
    }
}

