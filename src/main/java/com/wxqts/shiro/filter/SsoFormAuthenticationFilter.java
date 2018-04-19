package com.wxqts.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhoulong E-mail:zhoulong1588@163.com
 * @date 2018年4月18日
 */
public class SsoFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger logger = LoggerFactory.getLogger(SsoFormAuthenticationFilter.class);

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		String appUrl = request.getParameter("appUrl");

		AuthenticationToken token = createToken(request, response);
		if (token == null) {
			throw new IllegalStateException("createToken failed");
		}
		try {
			Subject subject = getSubject(request, response);
			// 用户名与密码认证
			subject.login(token);
			if (logger.isDebugEnabled()) {
				logger.debug("appUrl: " + appUrl);
			}
			// 用户允许访问的应用权限认证
			subject.checkPermission(appUrl);
			setSuccessUrl(appUrl + "?username=" + token.getPrincipal().toString());
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("wrong username or password");
			}
			return onLoginFailure(token, e, request, response);
		} catch (AuthorizationException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("no permit");
			}
			throw e;
		}
	}
}
