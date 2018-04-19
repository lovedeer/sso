package com.wxqts.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wxqts.domain.App;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月19日
 */
public interface AppDao {

	/**
	 * 根据应用id查找
	 * 
	 * @param appId
	 *            应用Id
	 * @return App
	 */
	App getAppByAppId(int appId);

	/**
	 * 根据应用地址查找
	 * 
	 * @param appUrl
	 *            应用地址
	 * @return App
	 */
	App getAppByAppUrl(String appUrl);

	/**
	 * 根据用户Id查找允许访问的所有应用集合
	 * 
	 * @param username
	 *            用户名
	 * @return 应用集合
	 */
	List<String> getAppUrlByUsername(@Param("username") String username);
}
