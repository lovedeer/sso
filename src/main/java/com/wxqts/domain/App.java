package com.wxqts.domain;

import java.io.Serializable;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月19日
 */
public class App implements Serializable {

	private static final long serialVersionUID = 1L;
	private String appName;
	private int appId;
	private String appUrl;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

}
