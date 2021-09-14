package com.spca.animalnest.tool;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.Test;

import javax.management.ObjectName;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @author seenline
 * @date 2021/8/26 9:51
 * @description 与阿里云oss的交互
 */

public class OssInteraction {
    @Test
    public static String push(String name,InputStream inputStream,String type) {
        String objectNameChoice[] = {"pictures/"+name,"videos/"+name};
        String objectName;


        String endpoint = ;
        String accessKeyId =;
        String accessKeySecret =;
        String bucketName = "test-to-zsy";

        if (type.equals("picture")) {
            objectName = objectNameChoice[0];
        }
        else

            objectName=objectNameChoice[1];

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, objectName,inputStream);
        String URL=ossClient.generatePresignedUrl(bucketName,objectName, new Date(new Date().getTime()+3600l*1000*24*365*10)).toString();
        ossClient.shutdown();
        return URL;
    }

}
