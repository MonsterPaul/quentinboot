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

        JSONObject paramsMap1 = new JSONObject();
        paramsMap1.put("ewbNo","300121234");
        paramsMap1.put("operationId","122222222222");
        paramsMap1.put("ewbDate","2017-08-01 00:00:00");
        paramsMap1.put("sendCustomerId",2L);
        paramsMap1.put("sendCustomerAddressId",5L);
        paramsMap1.put("receiveCustomerId",41L);
        paramsMap1.put("receiveCustomerAddressId",62L);
        paramsMap1.put("piece",62L);
        paramsMap1.put("weight",62D);
        paramsMap1.put("calcWeight",462546D);

        JSONArray paramsArray = new JSONArray();
        paramsArray.add(paramsMap);
        paramsArray.add(paramsMap1);

        JSONObject x = new JSONObject();
        x.put("ewbNo","123654");
        x.put("otherCharge",555555d);

        JSONObject y = new JSONObject();
        y.put("ewbNo","4444444");
        y.put("otherCharge",3333333d);

        JSONArray array = new JSONArray();
        array.add(x);
        array.add(y);

        JSONObject transfer = new JSONObject();
        transfer.put("messageType","ewb");
        transfer.put("data",paramsArray);

        JSONObject transfer1 = new JSONObject();
        transfer1.put("messageType","ewbDetail");
        transfer1.put("data",array);

        JSONArray transferArray = new JSONArray();
        transferArray.add(transfer);
        transferArray.add(transfer1);



        JSONObject httpParams = new JSONObject();
        httpParams.put("event","add");
        httpParams.put("object",transferArray.toJSONString());

        //验证签名摘要
        String digest = DigestUtils.md5Hex(transferArray.toJSONString() + "waybillcenter" + "waybillcenter123456");

        httpParams.put("sign",digest);

        System.out.println(httpParams.toString());

        String url = "http://192.168.1.127:8086/api/getTransferMsg";
        HttpClientUtils.getInstance().sendPost(url, httpParams.toJSONString(),"utf-8");
    }

}
