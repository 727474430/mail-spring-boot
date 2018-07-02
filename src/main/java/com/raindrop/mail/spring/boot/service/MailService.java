package com.raindrop.mail.spring.boot.service;

import com.raindrop.mail.spring.boot.model.MailModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @name: com.raindrop.mail.spring.boot.service.MailService.java
 * @description: 邮件服务接口
 * @author: Wang Liang
 * @create Time: 2018/6/16 16:47
 */
public interface MailService {

	/**
	 * 发送基础邮件
	 *
	 * @param mailModel 邮件实体
	 */
	void sendBasicMail(MailModel mailModel);

	/**
	 * 发送带附件的邮件
	 *
	 * @param mailModel 邮件实体
	 * @param request	包含附件内容
	 */
	void sendAttachmentMail(MailModel mailModel, HttpServletRequest request);

	/**
	 * 发送静态资源
	 *
	 * @param mailModel 邮件实体
	 */
	void sendInlineMail(MailModel mailModel);

	/**
	 * 发送邮件使用模板
	 *
	 * @param mailModel
	 */
	void sendMailByTemplate(MailModel mailModel);

}
