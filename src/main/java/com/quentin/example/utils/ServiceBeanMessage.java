package com.quentin.example.utils;

/**
 * <p>功能描述：上传参数帮助类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: 丞风智能科技有限公司</p>
 * @author Laughing
 * @version 1.0 2014-1-15 下午9:48:36
 */
public class ServiceBeanMessage {

	/** 登录名 **/
	private String loginUser;
	/** 登录密码 **/
	private String loginPwd;
	/** 登录ip **/
	private String ip;
	/** 交易号 **/
	private String serverCode;
	/** 下载数据参数 **/
	private String params;
	/** 扫描数据参数 **/
	private byte[] scanParams;
	/** 状态 **/
	private String states;
	
	public ServiceBeanMessage(){}

	public ServiceBeanMessage(String loginUser, String loginPwd, String ip,
                              String serverCode, String params, byte[] scanParams, String states) {
		super();
		this.loginUser = loginUser;
		this.loginPwd = loginPwd;
		this.ip = ip;
		this.serverCode = serverCode;
		this.params = params;
		this.scanParams = scanParams;
		this.states = states;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public byte[] getScanParams() {
		return scanParams;
	}

	public void setScanParams(byte[] scanParams) {
		this.scanParams = scanParams;
	}

	public String getServerCode() {
		return serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}
}
