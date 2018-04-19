package com.wxqts.web;
/**
* @author zhoulong  E-mail:zhoulong@163.com
* @date 2018年4月17日
*/

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wxqts.constant.SsoConstants;
import com.wxqts.domain.User;
import com.wxqts.service.UserService;

@Controller
public class SsoController {
	private static final Logger logger = LoggerFactory.getLogger(SsoController.class);

	@RequestMapping(value = "/app", method = RequestMethod.GET)
	public String test(HttpServletRequest req, Model model) {
		// request含有无权限访问应用标志属性时返回主页
		if (req.getAttribute(SsoConstants.NO_PERMIT_KEY) != null) {
			return SsoConstants.ON_SUCCESS_URL;
		}
		// 跳转到应用
		StringBuilder appUrl = new StringBuilder();
		appUrl.append(req.getParameter(SsoConstants.REDIRECT_APP_URL_KEY));
		appUrl.append("?").append(SsoConstants.USER_SESSION_KEY).append("=");
		appUrl.append(req.getSession().getAttribute(SsoConstants.USER_SESSION_KEY).toString());
		return "redirect:" + appUrl.toString();
	}

	@RequestMapping(value = SsoConstants.LOGIN_URL)
	public String redirectLogin(HttpServletRequest req, Model model) {
		if (req.getSession().getAttribute(SsoConstants.USER_SESSION_KEY) != null) {
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
