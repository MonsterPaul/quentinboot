package com.quentin.example;

import com.google.common.collect.Maps;
import com.quentin.example.utils.HttpClientUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * @Auth Created by guoqun.yang
 * @Date Created in 16:54 2018/1/30
 * @Version 1.0
 */
public class TestHttpClient {

    @Test
    public void testpost() {
        String url = "http://58.215.167.237:60211/ane-job/100410.bizservice";
        Map map = Maps.newHashMap();
        map.put("ewbsListNo","40714071418013001");

        String digest = Base64.encodeBase64String(DigestUtils.md5Hex(map.toString()  + "ane"+ "123456").getBytes());
        String timestamp = "" + new Date().getTime();

        Map paramsMap = Maps.newHashMap();
        paramsMap.put("params",map);
        paramsMap.put("digest",digest);
        paramsMap.put("timestamp",timestamp);

        String str = HttpClientUtils.getInstance().sendPostReq(url, paramsMap, "utf-8");
        System.out.println("获取到返回结果："+str);

    }

}
