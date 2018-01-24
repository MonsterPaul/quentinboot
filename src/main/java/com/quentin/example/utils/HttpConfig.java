package com.quentin.example.utils;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.protocol.HttpContext;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/** 
 * 请求配置类
 * 
 * @author arron
 * @date 2016年2月2日 下午3:14:32 
 * @version 1.0 
 */
public class HttpConfig {
	
	private HttpConfig(){

	};
	
	/**
	 * 获取实例
	 * @return
	 */
	public static HttpConfig getInstance(){
		return new HttpConfig();
	}

	/**
	 * HttpClient对象
	 */
	private HttpClient client;
	
	/**
	 * Header头信息
	 */
	private Header[] headers;
	
	/**
	 * 是否返回response的headers
	 */
	private boolean isReturnRespHeaders;

	/**
	 * 请求方法
	 */
	private String method= "GET";
	
	/**
	 * 请求方法名称
	 */
	private String methodName;

	/**
	 * 用于cookie操作
	 */
	private HttpContext context;

	/**
	 * 传递参数
	 */
	private Map<String, Object> map;
	
	/**
	 * 以json格式作为输入参数
	 */
	private String json;

	/**
	 * 输入输出编码
	 */
	private String encoding=Charset.defaultCharset().displayName();

	/**
	 * 输入编码
	 */
	private String inenc;

	/**
	 * 输出编码
	 */
	private String outenc;

	/**
	 * 解决多线程下载时，strean被close的问题
	 */
	private static final ThreadLocal<OutputStream> outs = new ThreadLocal<OutputStream>();	
	
	/**
	 * 解决多线程处理时，url被覆盖问题
	 */
	private static final ThreadLocal<String> urls = new ThreadLocal<String>();	


	public HttpConfig client(HttpClient client) {
		this.client = client;
		return this;
	}

	public HttpClient client() {
		return client;
	}

	public HttpConfig headers(Header[] headers) {
		this.headers = headers;
		return this;
	}

	public Header[] headers() {
		return this.headers;
	}

	public HttpConfig isReturnRespHeaders(boolean returnRespHeaders) {
		isReturnRespHeaders = returnRespHeaders;
		return this;
	}

	public boolean isReturnRespHeaders(){
		return isReturnRespHeaders;
	}


	public HttpConfig method(String method) {
		this.method = method;
		return this;
	}

	public String method(){
		return this.method;
	}

	public HttpConfig methodName(String methodName) {
		this.methodName = methodName;
		return this;
	}

	public String methodName(){
		return this.methodName;
	}

	public HttpConfig context(HttpContext context) {
		this.context = context;
		return this;
	}

	public HttpContext context(){
		return this.context;
	}

	public HttpConfig map(Map<String, Object> map) {
		synchronized (getClass()) {
			if(this.map==null || map==null){
				this.map = map;
			}else {
				this.map.putAll(map);;
			}
		}
		return this;
	}

	public Map<String, Object> map() {
		return map;
	}

	public HttpConfig json(String json) {
		this.json = json;
		map = new HashMap<String, Object>();
		map.put("$ENTITY_STRING$", json);
		return this;
	}

	public String json() {
		return json;
	}

	public HttpConfig encoding(String encoding) {
		this.encoding = encoding;
		return this;
	}

	public String encoding() {
		return this.encoding;
	}

	public HttpConfig inenc(String inenc){
		this.inenc = inenc;
		return this;
	}

	public String inenc() {
		return inenc == null ? encoding : inenc;
	}

	public String outenc() {
		return outenc == null ? encoding : outenc;
	}

	public HttpConfig outs(OutputStream outputStream) {
		outs.set(outputStream);
		return this;
	}
	public OutputStream outs() {
		return outs.get();
	}

	public HttpConfig urls(String url) {
		urls.set(url);
		return this;
	}
	public String urls() {
		return urls.get();
	}


}