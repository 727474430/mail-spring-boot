package com.raindrop.mail.spring.boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.raindrop.mail.spring.boot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @name: com.raindrop.mail.spring.boot.controller.LoginController.java
 * @description: 登录Controller
 * @author: Wang Liang
 * @create Time: 2018/6/27 13:51
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * 跳转登录页面
	 *
	 * @return
	 */
	@RequestMapping(value = {"/toLogin", "/"}, method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	/**
	 * 登录
	 *
	 * @param email
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject login(String email, String password) {
		return loginService.login(email, password);
	}

	/**
	 * 账号注册
	 *
	 * @param email
	 * @param password
	 * @param confirmPassword
	 * @return
	 */
	@RequestMapping("/registered")
	@ResponseBody
	public JSONObject registered(String email, String password, String confirmPassword) {
		return loginService.registered(email, password, confirmPassword);
	}

	/**
	 * 账号激活
	 *
	 * @param account
	 * @param token
	 * @return
	 */
	@RequestMapping("/active")
	@ResponseBody
	public JSON active(String account, String token) {
		return loginService.active(account, token);
	}

}
