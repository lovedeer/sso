package com.wxqts.shiro.filter;

import java.net.URL;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxqts.constant.SsoConstants;

/**
 * @author zhoulong E-mail:zhoulong1588@163.com
 * @date 2018年4月18日
 */
public class SsoFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger logger = LoggerFactory.getLogger(SsoFormAuthenticationFilter.class);
	private static final String[] NO_CHECK_URL = { SsoConstants.LOGIN_URL, SsoConstants.ON_SUCCESS_URL };

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		boolean checkPermission = false;
		SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);

		// 检查保存的请求地址是否要检查权限
		if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
			if (needPermissionCheck(savedRequest)) {
				checkPermission = true;
			}
			if (logger.isDebugEnabled()) {
				logger.debug("saved request url: " + savedRequest.getRequestUrl());
			}
		}

		AuthenticationToken token = createToken(request, response);
		if (token == null) {
			throw new IllegalStateException("createToken failed");
		}
		try {
			Subject subject = getSubject(request, response);
			// 用户名与密码认证
			subject.login(token);
			// 检查应用访问权限
			if (checkPermission) {
				if (logger.isDebugEnabled()) {
					logger.debug("*****用户权限验证*****");
				}
				String redirectUrlKey = SsoConstants.REDIRECT_APP_URL_KEY + "=";
				subject.checkPermission(savedRequest.getQueryString().substring(redirectUrlKey.length()));
			}
			// 用户名写进session
			((HttpServletRequest) request).getSession().setAttribute(SsoConstants.USER_SESSION_KEY,
					token.getPrincipal().toString());
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

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		String successUrl = null;
		String fallbackUrl = getSuccessUrl();
		boolean contextRelative = true;
		SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
		// 若有保存的请求地址，则跳转到该地址，否则跳转到预定义的成功地址
		if (savedRequest != null && savedRequest.getMethod().equalsIgnoreCase(AccessControlFilter.GET_METHOD)) {
			String username = ((HttpServletRequest) request).getSession().getAttribute(SsoConstants.USER_SESSION_KEY)
					.toString();
			// 形如redirect=
			String redirectUrlKey = SsoConstants.REDIRECT_APP_URL_KEY + "=";
			// 跳转地址带上用户名参数
			successUrl = savedRequest.getQueryString().substring(redirectUrlKey.length()) + "?"
					+ SsoConstants.USER_SESSION_KEY + username;
			contextRelative = false;
		}

		if (successUrl == null) {
			successUrl = fallbackUrl;
		}

		if (successUrl == null) {
			throw new IllegalStateException("Success URL not available via saved request or via the "
					+ "successUrlFallback method parameter. One of these must be non-null for "
					+ "issueSuccessRedirect() to work.");
		}

		WebUtils.issueRedirect(request, response, successUrl, null, contextRelative);
		return false;
	}

	/**
	 * 判断保存的请求是否要检查权限
	 * 
	 * @param savedRequest
	 *            保存的请求对像
	 * @return true/false
	 */
	protected boolean needPermissionCheck(SavedRequest savedRequest) {
		String uri = savedRequest.getRequestURI();
		for (String noCheckUrl : NO_CHECK_URL) {
			if (uri.equals(noCheckUrl)) {
				return false;
			}
		}
		return true;
	}

}
