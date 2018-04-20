package com.wxqts.web;
/**
* @author zhoulong  E-mail:zhoulong@163.com
* @date 2018年4月17日
*/

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxqts.constant.SsoConstants;
import com.wxqts.shiro.filter.SsoFormAuthenticationFilter;

@Controller
public class SsoController {
	private static final Logger logger = LoggerFactory.getLogger(SsoController.class);

	@RequestMapping(value = "/app", method = RequestMethod.GET)
	public String app(HttpServletRequest req, HttpServletResponse res, RedirectAttributes attr)
			throws IOException, ServletException {
		org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
		// request含有无权限访问应用标志属性时返回主页
		if (req.getAttribute(SsoConstants.NO_PERMIT_KEY) != null) {
			attr.addFlashAttribute(SsoConstants.ERROR_MSG_KEY, "<script>alert('没有权限访问该应用')</script>");
			return "redirect:" + SsoConstants.ON_SUCCESS_URL.substring(1);
		}
		// 跳转到应用
		StringBuilder appUrl = new StringBuilder();
		appUrl.append(req.getParameter(SsoConstants.REDIRECT_APP_URL_KEY));
		appUrl.append("?").append(SsoConstants.USER_SESSION_KEY).append("=");
		appUrl.append(req.getSession().getAttribute(SsoConstants.USER_SESSION_KEY).toString());
		return "redirect:" + appUrl.toString();
	}

	@RequestMapping(value = SsoConstants.LOGIN_URL)
	public String redirectLogin(HttpServletRequest req, Model model, RedirectAttributes attr) {
		Exception exception = (Exception) req
				.getAttribute(SsoFormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (exception != null) {
			if (exception instanceof AuthenticationException) {
				model.addAttribute(SsoConstants.ERROR_MSG_KEY, "用户名或密码错误");
				return SsoConstants.LOGIN_URL.substring(1);
			} else if (exception instanceof AuthorizationException) {
				attr.addFlashAttribute(SsoConstants.ERROR_MSG_KEY, "<script>alert('没有权限访问该应用')</script>");
				return "redirect:" + SsoConstants.ON_SUCCESS_URL.substring(1);
			} else {
				model.addAttribute(SsoConstants.ERROR_MSG_KEY, "未知异常，请联系管理员");
				return SsoConstants.LOGIN_URL.substring(1);
			}
		} else if (req.getSession().getAttribute(SsoConstants.USER_SESSION_KEY) != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("用户已登录 ，跳转到成功页面:" + SsoConstants.ON_SUCCESS_URL);
			}
			return "redirect:" + SsoConstants.ON_SUCCESS_URL;
		}
		// 未登录，跳转到登录页面
		return SsoConstants.LOGIN_URL.substring(1);
	}

	@RequestMapping(value = SsoConstants.ON_SUCCESS_URL)
	public String index(HttpServletRequest req, Model model) {
		return SsoConstants.ON_SUCCESS_URL.substring(1);
	}

}
