package com.raindrop.mail.spring.boot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.raindrop.mail.spring.boot.cache.AccountCache;
import com.raindrop.mail.spring.boot.cache.TokenCache;
import com.raindrop.mail.spring.boot.model.Account;
import com.raindrop.mail.spring.boot.model.MailModel;
import com.raindrop.mail.spring.boot.service.LoginService;
import com.raindrop.mail.spring.boot.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @name: com.raindrop.mail.spring.boot.service.impl.LoginServiceImpl.java
 * @description: 登录ServiceImpl
 * @author: Wang Liang
 * @create Time: 2018/6/27 18:28
 */
@Service
public class LoginServiceImpl implements LoginService {

	public static final String NOT_ACTIVATED = "0";
	public static final String FAIL = "fail";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";

	public static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private MailService mailService;

	/**
	 * 账号注册
	 *
	 * @param email
	 * @param password
	 * @param confirmPassword
	 * @return
	 */
	@Override
	public JSONObject registered(String email, String password, String confirmPassword) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(email)) {
			logger.warn("账号注册失败，邮箱地址为空");
			result.put("code", FAIL);
			result.put("message", "Email不能为空");
			return result;
		}

		if (StringUtils.isEmpty(password)) {
			logger.warn("账号注册失败，密码为空");
			result.put("code", FAIL);
			result.put("message", "密码不能为空");
			return result;
		}

		if (StringUtils.isEmpty(confirmPassword)) {
			logger.warn("账号注册失败，确认密码为空");
			result.put("code", FAIL);
			result.put("message", "确认密码不能为空");
			return result;
		}

		if (!password.equals(confirmPassword)) {
			logger.warn("账号注册失败，两次密码输入不一致");
			result.put("code", FAIL);
			result.put("message", "两次密码输入不一致");
			return result;
		}

		try {
			Account account = AccountCache.accountMap.get(email);
			if (account == null) {
				Account newAccount = new Account(email, password, "0");
				AccountCache.accountMap.put(email, newAccount);

				String token = UUID.randomUUID().toString().replaceAll("-", "");
				TokenCache.tokenMap.put(email, token);

				Map<String, String> paramMap = new HashMap<>(2);
				paramMap.put("to", email);
				paramMap.put("verifyUrl", "http://10.10.25.19:8080/login/active?account=" + email + "&token=" + token);

				MailModel mailModel = new MailModel();
				mailModel.setSendTo(email);
				mailModel.setSubject("欢迎您注册，请激活！");
				mailModel.setTemplateName("registered");
				mailModel.setTemplateModel(paramMap);

				mailService.sendMailByTemplate(mailModel);
				logger.info("账号注册成功");
				result.put("code", SUCCESS);
				result.put("message", "账号注册成功！请前往邮箱激活账号！");
				return result;
			}
			result.put("code", FAIL);
			result.put("message", "账号已经存在");
		} catch (Exception e) {
			logger.error("账号注册错误: {}", e.getMessage());
			result.put("code", ERROR);
			result.put("message", "服务器内部错误，请稍后再试！");
		}
		return result;
	}

	/**
	 * 账号激活
	 *
	 * @param account
	 * @param token
	 * @return
	 */
	@Override
	public JSONObject active(String account, String token) {
		JSONObject result = new JSONObject();
		try {
			String realToken = TokenCache.tokenMap.get(account);
			if (realToken.equals(token)) {
				Account email = AccountCache.accountMap.get(account);
				email.setIsActive("1");
				AccountCache.accountMap.put(account, email);
				result.put("code", SUCCESS);
				result.put("message", "账号激活成功！");
				return result;
			}

			logger.warn("账号激活失败");
			result.put("code", FAIL);
			result.put("message", "账号激活失败！");
		} catch (Exception e) {
			logger.error("账号激活错误");
			result.put("code", ERROR);
			result.put("message", "服务器内部错误，请稍后再试！");
		}
		return result;
	}

	/**
	 * 登录
	 *
	 * @param email
	 * @param password
	 * @return
	 */
	@Override
	public JSONObject login(String email, String password) {
		JSONObject result = new JSONObject();
		if (StringUtils.isEmpty(email)) {
			logger.warn("Email不能为空");
			result.put("code", FAIL);
			result.put("message", "Email不能为空");
			return result;
		}

		if (StringUtils.isEmpty(password)) {
			logger.warn("密码不能为空");
			result.put("code", FAIL);
			result.put("message", "密码不能为空");
			return result;
		}

		Account account = AccountCache.accountMap.get(email);
		if (account == null) {
			logger.warn("账号不存在，请重新登录！");
			result.put("code", FAIL);
			result.put("message", "账号不存在，请重新登录！");
			return result;
		}

		if (!account.getPassword().equals(password)) {
			logger.warn("密码输入错误，请确认密码后再次登录！");
			result.put("code", FAIL);
			result.put("message", "密码输入错误，请确认密码后再次登录！");
			return result;
		}

		if (NOT_ACTIVATED.equals(account.getIsActive())) {
			logger.warn("账号未激活，请前往邮箱激活后再次登录！");
			result.put("code", FAIL);
			result.put("message", "账号未激活，请前往邮箱激活后再次登录！");
			return result;
		}

		logger.info("登录成功");
		result.put("code", SUCCESS);
		result.put("message", "登录成功");
		return result;
	}

}
