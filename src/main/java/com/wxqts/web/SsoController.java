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

import com.wxqts.domain.User;
import com.wxqts.service.UserService;

@Controller
public class SsoController {
	private static final Logger log = LoggerFactory.getLogger(SsoController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest req, Model model) {
		String username = req.getParameter("username");
		String password = req.getParameter("passwd");
		User user = null;
		user = userService.getUserByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			model.addAttribute("user", user);
			log.debug("user :" + user);
			return "index";
		}
		model.addAttribute("errorMsg", "用户名或密码错误！");
		return "index";
	}

	@RequestMapping(value = "/login")
	public String redirectLogin(HttpServletRequest req, Model model) {
		if (req.getSession().getAttribute("username") != null) {
			String username = req.getSession().getAttribute("username").toString();
			model.addAttribute("username", username);
			return "index";
		}
		return "login";
	}

	@RequestMapping(value = "index")
	public String index(HttpServletRequest req, Model model) {
		String username = req.getParameter("username");
		model.addAttribute("username", username);
		return "index";
	}

}
