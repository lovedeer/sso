package com.wxqts.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxqts.domain.User;
import com.wxqts.service.UserService;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (logger.isDebugEnabled()) {
			logger.debug("用户登录认证");
		}
		String userName = token.getPrincipal().toString();
		if (logger.isDebugEnabled()) {
			logger.debug("userName: " + userName);
		}
		User user = userService.getUserByUserName(userName);
		if (user != null) {
			String password = new String((char[]) token.getCredentials());
			if (logger.isDebugEnabled()) {
				logger.debug("password: " + password);
			}
			if (user.getPassword().equals(password)) {
				return new SimpleAuthenticationInfo(userName, password, "userRealm");
			}
		}

		throw new UnauthenticatedException();
	}

}
