package com.wxqts.jwt;

import java.io.Serializable;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月20日
 */
public class UserSubject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private int id;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
