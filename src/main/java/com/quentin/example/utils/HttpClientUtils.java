package com.quentin.example.utils;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Created by guoqun.yang
 * @Date Created in 14:24 2017/12/27
 * @Version 1.0
 */
@Slf4j
public class HttpClientUtils {
    /**
     * HTTP协议
     */
    private static final String HTTPS_PROTOCOL = "https://";
    /**
     * 协议默认端口
     */
    private static final int HTTPS_PROTOCOL_DEFAULT_PORT = 443;

    /**
     * 默认编码格式
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    private static CloseableHttpClient httpClient;


    /**
     * 不可实例化
     */
    private HttpClientUtils() {
        httpClient = HttpClientBuilder.create().build();
    }

    public static HttpClientUtils getInstance() {
        return HttpClientUtilsHelper.httpClientUtils;
    }

    public static class HttpClientUtilsHelper {
        private static HttpClientUtils httpClientUtils = new HttpClientUtils();
    }


    /**
     * 拼接参数
     *
     * @param map
     * @Author: guoqun.yang
     * @Date: 2017/12/27 16:29
     * @version 1.0
     */
    public static List<NameValuePair> map2HttpEntity(Map<String, ?> map) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        HttpEntity entity = null;
        if (map != null && map.size() > 0) {
            // 拼接参数
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        return nvps;
    }

    /**
     * @param url 路径
     * @return int
     * @author
     * @date
     */
    private static int getPort(String url) {
        int startIndex = url.indexOf("://") + "://".length();
        String host = url.substring(startIndex);
        if (host.indexOf("/") != -1) {
            host = host.substring(0, host.indexOf("/"));
        }
        int port = HTTPS_PROTOCOL_DEFAULT_PORT;
        if (host.contains(":")) {
            int i = host.indexOf(":");
            port = new Integer(host.substring(i + 1));
        }
        return port;
    }

    /**
     * 发送GET请求
     *
     * @param url     请求地址
     * @param charset 返回数据编码
     * @return 返回数据
     */
    public static String sendGetReq(final String url, String charset) {
        if (StringUtils.isEmpty(charset)) {
            charset = DEFAULT_CHARSET;
        }

        HttpGet get = new HttpGet(url);
        if (url.toLowerCase().startsWith(HTTPS_PROTOCOL)) {
            initSSL(httpClient, getPort(url));
        }
        try {
            // 提交请求并以指定编码获取返回数据
            HttpResponse httpResponse = httpClient.execute(get);
            int statuscode = httpResponse.getStatusLine().getStatusCode();
            if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                Header header = httpResponse.getFirstHeader("location");
                if (header != null) {
                    String newuri = header.getValue();
                    if ((newuri == null) || ("".equals(newuri))) {
                        newuri = "/";
                    }
                    try {
                        httpClient.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        httpClient = null;
                    }
                    log.info("重定向地址：" + newuri);
                    return sendGetReq(newuri, null);
                }
            }
            log.info("请求地址：" + url + "；响应状态：" + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, charset);
        } catch (ClientProtocolException e) {
            log.error("协议异常,堆栈信息如下", e);
        } catch (IOException e) {
            log.error("网络异常,堆栈信息如下", e);
        } finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            } catch (Exception e) {
                log.error("关闭连接异常：HttpClientUtils.sendPost,堆栈信息如下", e);
                httpClient = null;
            }
        }
        return null;
    }


    /**
     * 发送put请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 返回数据编码
     * @return String
     */
    public static String sendPutReq(String url, Map<String, Object> params, String charset) {
        if (StringUtils.isEmpty(charset)) {
            charset = DEFAULT_CHARSET;
        }
        HttpPut put = new HttpPut(url);

        // 封装请求参数
        List<NameValuePair> pairs = map2HttpEntity(params);
        try {
            put.setEntity(new UrlEncodedFormEntity(pairs, charset));
            if (url.startsWith(HTTPS_PROTOCOL)) {
                initSSL(httpClient, getPort(url));
            }
            // 提交请求并以指定编码获取返回数据
            HttpResponse httpResponse = httpClient.execute(put);

            log.info("请求地址：" + url + "；响应状态：" + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, charset);
        } catch (ClientProtocolException e) {
            log.error("协议异常,堆栈信息如下", e);
        } catch (IOException e) {
            log.error("网络异常,堆栈信息如下", e);
        } finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            } catch (Exception e) {
                log.error("关闭连接异常：HttpClientUtils.sendPost,堆栈信息如下", e);
                httpClient = null;
            }
        }
        return null;
    }

    /**
     * 发送delete请求
     *
     * @param url     请求地址
     * @param charset 返回数据编码
     * @return String
     */
    public static String sendDelReq(String url, String charset) {
        if (StringUtils.isEmpty(charset)) {
            charset = DEFAULT_CHARSET;
        }
        HttpDelete del = new HttpDelete(url);
        if (url.toLowerCase().startsWith(HTTPS_PROTOCOL)) {
            initSSL(httpClient, getPort(url));
        }
        try {
            // 提交请求并以指定编码获取返回数据
            HttpResponse httpResponse = httpClient.execute(del);
            log.info("请求地址：" + url + "；响应状态：" + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, charset);
        } catch (ClientProtocolException e) {
            log.error("协议异常,堆栈信息如下", e);
        } catch (IOException e) {
            log.error("网络异常,堆栈信息如下", e);
        } finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            } catch (Exception e) {
                log.error("关闭连接异常：HttpClientUtils.sendPost,堆栈信息如下", e);
                httpClient = null;
            }
        }
        return null;
    }

    /**
     * 发送POST请求，支持HTTP与HTTPS
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 返回数据编码
     * @return 返回数据
     */
    public String sendPostReq(String url, Map<String, ?> params, String charset) {
        if (StringUtils.isEmpty(charset)) {
            charset = DEFAULT_CHARSET;
        }
        RequestConfig reqConf = RequestConfig.DEFAULT;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("busAccount", "4403180501225");
        httpPost.setHeader("sercertkey", "c6f5e8c7250c961548fd09dfe4ae105b");
        // 封装请求参数
        List<NameValuePair> pairs = map2HttpEntity(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
            // 对HTTPS请求进行处理
            if (url.toLowerCase().startsWith(HTTPS_PROTOCOL)) {
                initSSL(httpClient, getPort(url));
            }
            // 提交请求并以指定编码获取返回数据
            httpPost.setConfig(reqConf);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statuscode = httpResponse.getStatusLine().getStatusCode();

            tryGet(statuscode, httpResponse);

            log.info("请求地址：" + url + "；响应状态：" + httpResponse.getStatusLine());
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, charset);
        } catch (UnsupportedEncodingException e) {
            log.error("不支持当前参数编码格式[" + charset + "],堆栈信息如下", e);
        } catch (ClientProtocolException e) {
            log.error("协议异常,堆栈信息如下", e);
        } catch (IOException e) {
            log.error("网络异常,堆栈信息如下", e);
        } finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            } catch (Exception e) {
                log.error("关闭连接异常：HttpClientUtils.sendPost,堆栈信息如下", e);
                httpClient = null;
            }
        }
        return null;
    }

    public static String tryGet(int statuscode, HttpResponse httpResponse) {
        if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                || (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
            Header header = httpResponse.getFirstHeader("location");
            if (header != null) {
                String newuri = header.getValue();
                if ((StringUtils.isEmpty(newuri))) {
                    newuri = "/";
                }
                return sendGetReq(newuri, null);
            }
        }
        return "";
    }

    /**
     * 发送POST请求，支持HTTP与HTTPS, 参数放入请求体方式
     *
     * @param url     请求地址
     * @param content 请求参数
     * @param charset 返回数据编码
     * @return 返回数据
     */
    public String sendPost(String url, String content, String charset) {
        if (StringUtils.isEmpty(charset)) {
            charset = DEFAULT_CHARSET;
        }

        RequestConfig reqConf = RequestConfig.DEFAULT;
        HttpPost httpPost = new HttpPost(url);
        try {
            // 解决中文乱码问题
            StringEntity paramEntity = new StringEntity(content, DEFAULT_CHARSET);
            paramEntity.setContentEncoding(DEFAULT_CHARSET);
            paramEntity.setContentType("application/json");
            httpPost.setEntity(paramEntity);

            // 对HTTPS请求进行处理
            if (url.toLowerCase().startsWith(HTTPS_PROTOCOL)) {
                initSSL(httpClient, getPort(url));
            }
            // 提交请求并以指定编码获取返回数据
            httpPost.setConfig(reqConf);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statuscode = httpResponse.getStatusLine().getStatusCode();

            tryGet(statuscode, httpResponse);

            log.info("请求地址：" + url + "；响应状态：" + httpResponse.getStatusLine());

            JSONObject resultObj = getJsonResult(httpResponse, url);
            return resultObj.toJSONString();
        } catch (UnsupportedEncodingException e) {
            log.error("不支持当前参数编码格式[" + charset + "],堆栈信息如下", e);
        } catch (ClientProtocolException e) {
            log.error("协议异常,堆栈信息如下", e);
        } catch (IOException e) {
            log.error("网络异常,堆栈信息如下", e);
        } finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            } catch (Exception e) {
                log.error("关闭连接异常：HttpClientUtils.sendPost,堆栈信息如下", e);
                httpClient = null;
            }
        }
        return null;
    }

    /**
     * 获取响应
     *
     * @param result 响应对象
     * @param url    请求地址
     * @Author: guoqun.yang
     * @Date: 2018/1/15 16:50
     * @version 1.0
     */
    private static JSONObject getJsonResult(HttpResponse result, String url) {
        JSONObject jsonResult = null;
        if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String str;
            try {
                // 读取服务器返回过来的json字符串数据
                str = EntityUtils.toString(result.getEntity(), "utf-8");
                // 把json字符串转换成json对象
                jsonResult = JSONObject.parseObject(str);

                log.info(jsonResult.toString());
            } catch (Exception e) {
                log.error("PushHttpClientUtils.getJsonResult:post请求提交失败:" + url, e);
            }
        }
        return jsonResult;
    }

    /**
     * 初始化HTTPS请求服务
     *
     * @param httpClient HTTP客户端
     * @param port       端口
     */
    public static void initSSL(CloseableHttpClient httpClient, int port) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            final X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            // 使用TrustManager来初始化该上下文,TrustManager只是被SSL的Socket所使用
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            ConnectionSocketFactory ssf = new SSLConnectionSocketFactory(sslContext);
            Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create().register("https", ssf).build();
            BasicHttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(r);
            HttpClients.custom().setConnectionManager(ccm).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


}
