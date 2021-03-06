package com.wxqts.service;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.shiro.cache.Cache;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月23日
 */
@Service
public class TokenService {
	@Autowired
	private EhCacheManager cacheManager;

	/**
	 * 保存token进缓存
	 * 
	 * @param cacheName
	 *            缓存名
	 * @param key
	 *            缓存健，默认为sessionId
	 * @param object
	 *            token
	 */
	public void saveToken(String cacheName, Object key, Object object) {
		Cache<Object, Object> cache = cacheManager.getCache(cacheName);
		cache.put(key, object);
	}

	/**
	 * 从缓存中查找token
	 * 
	 * @param cacheName
	 *            缓存名
	 * @param key
	 *            缓存健，默认为sessionId
	 * @param tClass
	 *            token类型
	 * @return token
	 */
	@SuppressWarnings("unchecked")
	public <T> T getToken(String cacheName, Object key, Class<T> tClass) {
		Cache<Object, Object> cache = cacheManager.getCache(cacheName);
		return (T) cache.get(key);
	}
}
