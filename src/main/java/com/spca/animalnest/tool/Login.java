package com.spca.animalnest.tool;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author seenline
 * @date 2021/8/9 21:49
 * @description
 */
public class Login {
    public void getHttp(String code) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.weixin.qq.com/sns/jscode2session?appid="+ com.spca.animalnest.config.BackgroundConfig.ACCESS_KEY1+"&secret="+ com.spca.animalnest.config.BackgroundConfig.ACCESS_KEY2+"&js_code="+code+"&grant_type=authorization_code");
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            HttpEntity entity=response.getEntity();
            JSONObject obj = JSONObject.parseObject(EntityUtils.toString(entity));
            //存储获得到的openidnew LoginUserInformation().setContentFirst(obj.getString("openid")).setContentSecond(obj.getString("session_id")).propertiesSet();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
