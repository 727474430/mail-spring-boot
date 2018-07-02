package com.raindrop.mail.spring.boot.controller;

import com.raindrop.anno.WebLogging;
import com.raindrop.mail.spring.boot.model.MailModel;
import com.raindrop.mail.spring.boot.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @name: com.raindrop.mail.spring.boot.controller.MailController.java
 * @description: 邮件服务控制层
 * @author: Wang Liang
 * @create Time: 2018/6/16 18:05
 */
@Controller
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailService;

	/**
	 * 发送基础邮件
	 *
	 * @param mailModel 邮件实体
	 */
	@WebLogging(description = "发送简单邮件接口")
	@RequestMapping("/sentBasicMail")
	@ResponseBody
	public String sendBasicMail(@RequestBody MailModel mailModel) {
		mailService.sendBasicMail(mailModel);
		return "ok";
	}

	/**
	 * 发送带附件的邮件
	 *
	 * @param mailModel 邮件实体
	 * @param request	包含附件内容
	 */
	@WebLogging(description = "发送带附件的邮件接口")
	@RequestMapping("/sendAttachmentMail")
	@ResponseBody
	public String sendAttachmentMail(HttpServletRequest request, @RequestBody MailModel mailModel) {
		mailService.sendAttachmentMail(mailModel, request);
		return "ok";
	}

	/**
	 * 发送静态邮件
	 *
	 * @param mailModel
	 * @return
	 */
	@WebLogging(description = "发送静态邮件接口")
	@RequestMapping("/sendInlineMail")
	@ResponseBody
	public String sendInlineMail(@RequestBody MailModel mailModel) {
		mailService.sendInlineMail(mailModel);
		return "ok";
	}

	/**
	 * 发送模板邮件
	 *
	 * @param mailModel
	 * @return
	 */
	@WebLogging(description = "发送模板邮件接口")
	@RequestMapping("/sendMailByTemplate")
	@ResponseBody
	public String sendMailByTemplate(@RequestBody MailModel mailModel) {
		mailService.sendMailByTemplate(mailModel);
		return "ok";
	}

}
