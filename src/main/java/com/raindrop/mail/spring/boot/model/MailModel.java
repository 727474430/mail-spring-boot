package com.raindrop.mail.spring.boot.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @name: com.raindrop.mail.spring.boot.model.MailModel.java
 * @description: 邮件实体
 * @author: Wang Liang
 * @create Time: 2018/6/16 16:31
 */
public class MailModel implements Serializable {

	private static final long serialVersionUID = -2831141671517450978L;

	/**
	 * 发送单个人
	 */
	private String sendTo;
	/**
	 * 发送多个人
	 */
	private String sendTos;
	/**
	 * 抄送单个人
	 */
	private String ccTo;
	/**
	 * 抄送多个人
	 */
	private String ccTos;
	/**
	 * 标题
	 */
	private String subject;
	/**
	 * 子标题
	 */
	private String childSubject;
	/**
	 * 邮件内容
	 */
	private String text;
	/**
	 * 邮件模板名称
	 */
	private String templateName;
	/**
	 * 邮件模板参数map
	 */
	private Map<String, String> templateModel;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getSendTo() {
		return sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	public String getSendTos() {
		return sendTos;
	}

	public void setSendTos(String sendTos) {
		this.sendTos = sendTos;
	}

	public String getCcTo() {
		return ccTo;
	}

	public void setCcTo(String ccTo) {
		this.ccTo = ccTo;
	}

	public String getCcTos() {
		return ccTos;
	}

	public void setCcTos(String ccTos) {
		this.ccTos = ccTos;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getChildSubject() {
		return childSubject;
	}

	public void setChildSubject(String childSubject) {
		this.childSubject = childSubject;
	}

	public Map<String, String> getTemplateModel() {
		return templateModel;
	}

	public void setTemplateModel(Map<String, String> templateModel) {
		this.templateModel = templateModel;
	}

	@Override
	public String toString() {
		return "MailModel{" +
				"sendTo='" + sendTo + '\'' +
				", sendTos='" + sendTos + '\'' +
				", ccTo='" + ccTo + '\'' +
				", ccTos='" + ccTos + '\'' +
				", subject='" + subject + '\'' +
				", childSubject='" + childSubject + '\'' +
				", text='" + text + '\'' +
				", templateName='" + templateName + '\'' +
				'}';
	}
}
