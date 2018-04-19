package com.wxqts.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxqts.domain.User;
import com.wxqts.service.UserService;
import com.wxqts.shiro.permission.AppPermission;

/**
 * @author zhoulong E-mail:zhoulong@163.com
 * @date 2018年4月18日
 */
@Service("userRealm")
public class UserRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = principals.getPrimaryPrincipal().toString();
		authorizationInfo.addObjectPermission(new AppPermission(userService.getAppUrlByUsername(username)));

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (logger.isDebugEnabled()) {
			logger.debug("*****用户登录认证*****");
		}
		String username = token.getPrincipal().toString();
		if (logger.isDebugEnabled()) {
			logger.debug("userName: " + username);
		}
		User user = userService.getUserByUsername(username);
		if (user != null) {
			String password = new String((char[]) token.getCredentials());
			if (logger.isDebugEnabled()) {
				logger.debug("password: " + password);
			}
			if (user.getPassword().equals(password)) {
				return new SimpleAuthenticationInfo(username, password, "userRealm");
			}
		}

		throw new UnauthenticatedException();
	}

}
