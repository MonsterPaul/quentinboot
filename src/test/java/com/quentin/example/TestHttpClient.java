package com.quentin.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.optmius.sdk.HexUtil;
import com.optmius.sdk.OrderIdGenerator;
import com.quentin.example.pay.scanpay.TLinx2Util;
import com.quentin.example.pay.scanpay.TLinxAESCoder;
import com.quentin.example.utils.HttpClientUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

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
        httpParams.put("object",paramsMap.toJSONString());
        httpParams.put("event","record");

        System.out.println(httpParams.toString());

        String url = "http://192.168.1.171:8086/api/getTransferMsg";
        HttpClientUtils.getInstance().sendPost(url, httpParams.toJSONString(),"utf-8");
    }

    @Test
    public void testKakaleQueryOrderPost() {
        String url = "http://118.89.217.178:9909/weixin/kklPayRequest3g/kklGetOrderInfo.3g";

        TreeMap<String, String> paramsMap = Maps.newTreeMap();

        JSONObject requestObj = new JSONObject();
        requestObj.put("orderId", "12018051813290899224405");
        // AES加密，并bin2hex
        String data = "";
        try {
            data = TLinxAESCoder.encrypt(requestObj.toString(), "c6f5e8c7250c961548fd09dfe4ae105b");
        } catch (Exception e) {
            e.printStackTrace();
        }
        paramsMap.put("object",data);

        //时间戳
        paramsMap.put("timestamp",System.currentTimeMillis()+"");

        Map<String, String> veriDataMap = Maps.newHashMap();
        veriDataMap.putAll(paramsMap);
        veriDataMap.put("open_key", "c6f5e8c7250c961548fd09dfe4ae105b");
        // 签名
        String sign = TLinx2Util.sign(veriDataMap);
        paramsMap.put("sign",sign);

        System.out.println(JSON.toJSON(paramsMap));

        String str = HttpClientUtils.getInstance().sendPostReq(url, paramsMap, "utf-8");

        JSONObject object = JSON.parseObject(str);
        String restulstr = String.valueOf(object.get("obj"));
        String resulturl = "";
        try {
            resulturl = decrypt(restulstr,"c6f5e8c7250c961548fd09dfe4ae105b");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("获取到返回结果："+resulturl);

    }

    @Test
    public void testKakalePost() {
        String url = "http://118.89.217.178:9909/weixin/kklPayRequest3g/kklPay.3g";

        TreeMap<String, String> paramsMap = Maps.newTreeMap();

        JSONObject requestObj = new JSONObject();
        requestObj.put("mchId", "4403180501225");
        requestObj.put("orderId", OrderIdGenerator.genUnique());
        requestObj.put("isweixin", 1);//支付宝
        requestObj.put("goodsTitile", "蓝牙音箱");
        requestObj.put("goodsDescription", "this is uncledrew");
        requestObj.put("goodsTotalAmount", 0.1D);
        requestObj.put("notifyUrl", "http://118.89.217.178:9909/weixin/kklPayRequest3g/yt_callback");
        requestObj.put("jumpUrl", "www.baidu.com");
        requestObj.put("osPlatform", 5);
        requestObj.put("wapUrl", "www.baidu.com");
        requestObj.put("androidPackageName","");
        requestObj.put("bundleId","");
        // AES加密，并bin2hex
        String data = "";
        try {
            data = TLinxAESCoder.encrypt(requestObj.toString(), "c6f5e8c7250c961548fd09dfe4ae105b");
        } catch (Exception e) {
            e.printStackTrace();
        }
        paramsMap.put("object",data);

        //时间戳
        paramsMap.put("timestamp",System.currentTimeMillis()+"");

        Map<String, String> veriDataMap = Maps.newHashMap();
        veriDataMap.putAll(paramsMap);
        veriDataMap.put("open_key", "c6f5e8c7250c961548fd09dfe4ae105b");
        // 签名
        String sign = TLinx2Util.sign(veriDataMap);
        paramsMap.put("sign",sign);

        System.out.println(JSON.toJSON(paramsMap));

        String str = HttpClientUtils.getInstance().sendPostReq(url, paramsMap, "utf-8");

        JSONObject object = JSON.parseObject(str);

        if (object.get("code").equals(0)) {
            String restulstr = String.valueOf(object.get("obj"));
            String resulturl = "";
            try {
                resulturl = decrypt(restulstr,"c6f5e8c7250c961548fd09dfe4ae105b");
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("获取到返回结果："+resulturl);
        }else {
            System.out.println(object.toJSONString());
        }



    }

    private static String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static String KEY_ALGORITHM = "AES";

    public static String decrypt(String sSrc, String sKey) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes("ASCII"), KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(2, skeySpec);
        byte[] encrypted1 = hex2byte(sSrc);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original);
    }

    private static byte[] hex2byte(String strhex) {
        if (strhex == null)
            return null;

        int l = strhex.length();
        if (l % 2 == 1)
            return null;

        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; ++i)
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);

        return b;
    }

}
