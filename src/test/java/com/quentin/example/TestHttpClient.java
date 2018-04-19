package com.quentin.example;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

    @Test
    public void testJsonPost() {
        JSONObject paramsMap = new JSONObject();
        paramsMap.put("ewbNo","300143773134");
        paramsMap.put("operationId","6456465465465");
        paramsMap.put("ewbDate","2017-08-02 00:00:00");
        paramsMap.put("sendCustomerId",23L);
        paramsMap.put("sendCustomerAddressId",56L);
        paramsMap.put("receiveCustomerId",41L);
        paramsMap.put("receiveCustomerAddressId",62L);
        paramsMap.put("piece",62L);
        paramsMap.put("weight",62D);
        paramsMap.put("calcWeight",46546D);
        paramsMap.put("remark","测试一下");
        paramsMap.put("packageType","我");
        paramsMap.put("goodsName","测试我");

        Map<String, Object> m2 = Maps.newHashMap();
        m2.put("ewbsListNo", "43312114504044");
        m2.put("preSiteId", 23L);
        m2.put("preSiteName", "湘西吉首");

        JSONArray array = new JSONArray();
        array.add(m2);



        //验证签名摘要
        String digest = DigestUtils.md5Hex(paramsMap.toJSONString() + "waybillcenter" + "waybillcenter123456");

        JSONObject httpParams = new JSONObject();
        httpParams.put("sign",digest);
        httpParams.put("object",array.toJSONString());
        httpParams.put("event","I");
        httpParams.put("type","ewbslist");

        System.out.println(httpParams.toString());

        String url = "http://192.168.0.227:8088/api/getTransferMsg";
        HttpClientUtils.getInstance().sendPost(url, httpParams.toJSONString(),"utf-8");
    }

}
