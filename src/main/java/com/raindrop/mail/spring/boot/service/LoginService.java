package com.raindrop.mail.spring.boot.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @name: com.raindrop.mail.spring.boot.service.LoginService.java
 * @description: 登录Service
 * @author: Wang Liang
 * @create Time: 2018/6/27 18:24
 */
public interface LoginService {

	/**
	 * 注册
	 *
	 * @param email
	 * @param password
	 * @param confirmPassword
	 * @return
	 */
	JSONObject registered(String email, String password, String confirmPassword);

	/**
	 * 账号激活
	 *
	 * @param account
	 * @param token
	 * @return
	 */
	JSONObject active(String account, String token);

	/**
	 * 登录
	 *
	 * @param email
	 * @param password
	 * @return
	 */
	JSONObject login(String email, String password);

}
