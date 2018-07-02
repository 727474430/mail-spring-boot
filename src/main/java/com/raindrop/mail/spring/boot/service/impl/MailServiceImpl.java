package com.raindrop.mail.spring.boot.service.impl;

import com.raindrop.mail.spring.boot.model.MailModel;
import com.raindrop.mail.spring.boot.service.MailService;
import com.raindrop.mail.spring.boot.util.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @name: com.raindrop.mail.spring.boot.service.impl.MailServiceImpl.java
 * @description: 邮件服务实现
 * @author: Wang Liang
 * @create Time: 2018/6/16 16:52
 */
@Service
public class MailServiceImpl implements MailService {

	/** logger */
	public static Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	/** 发送人 */
	@Value("${spring.mail.username}")
	private String from;

	/**
	 * 发送基础邮件
	 *
	 * @param mailModel 邮件实体
	 */
	@Override
	public void sendBasicMail(MailModel mailModel) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setSubject(mailModel.getSubject());
			message.setTo(mailModel.getSendTo());
			message.setCc(mailModel.getCcTo());
			message.setText(mailModel.getText());
			message.setSentDate(new Date());
			logger.info("Send Basic Mail, from: {} to: {} cc: {} subject: {} text: {} ", from, mailModel.getSendTo(),
					mailModel.getCcTo(), mailModel.getSubject(), mailModel.getText());

			mailSender.send(message);
			logger.info("Send Basic Mail Success!");
		} catch (MailException e) {
			logger.error("Send Basic Mail Error: {}", e.getMessage());
		}
	}

	/**
	 * 发送带附件的邮件
	 *
	 * @param mailModel 邮件实体
	 * @param request	包含附件内容
	 */
	@Override
	public void sendAttachmentMail(MailModel mailModel, HttpServletRequest request) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(mailModel.getSendTo());
			helper.setCc(mailModel.getCcTo());
			helper.setSubject(mailModel.getSubject());
			helper.setText(mailModel.getText());
			helper.setSentDate(new Date());
			// get file paths
			List<String> filePaths = FileUtil.fileUpload(request);
			for (int i = 0; i < filePaths.size(); i++) {
				String filePath = filePaths.get(i);
				String fileType = filePath.substring(filePath.lastIndexOf("."));
				String attachmentName = "附件-" + (i + 1) + fileType;
				helper.addAttachment(attachmentName, new FileSystemResource(new File(filePath)));
				logger.info("Send attachment mail, attachment name: {}", attachmentName);
			}
			// send attachment mail
			mailSender.send(mimeMessage);
			logger.info("Send attachment mail success!");
		} catch (Exception e) {
			logger.error("Send attachment mail error: {}", e.getMessage());
		}
	}

	/**
	 * 发送静态资源
	 *
	 * @param mailModel 邮件实体
	 */
	@Override
	public void sendInlineMail(MailModel mailModel) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(mailModel.getSendTo());
			helper.setCc(mailModel.getCcTo());
			helper.setSubject(mailModel.getSubject());
			helper.setSentDate(new Date());
			helper.setText(mailModel.getText(), true);

			// static resource file
			FileSystemResource fileSystemResource = new FileSystemResource(
					new File("C:/Users/Public/Pictures/Sample Pictures/006.png"));
			helper.addInline("006.png", fileSystemResource);

			mailSender.send(mimeMessage);
			logger.info("Send inline mail success!");
		} catch (Exception e) {
			logger.error("Send inline mail error: {}", e.getMessage());
		}
	}

	/**
	 * 发送邮件使用模板
	 *
	 * @param mailModel
	 */
	@Override
	public void sendMailByTemplate(MailModel mailModel) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(mailModel.getSendTo());
//			helper.setCc(mailModel.getCcTo());
			helper.setSubject(mailModel.getSubject());
			helper.setSentDate(new Date());
			// load mail template
			Configuration configuration = getConfiguration();
			Template template = configuration.getTemplate(mailModel.getTemplateName() + ".ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mailModel.getTemplateModel());
			helper.setText(html, true);

			mailSender.send(mimeMessage);
			logger.info("Send mail by template success!");
		} catch (Exception e) {
			logger.error("Send mail by template error: {}", e.getMessage());
		}
	}

	/**
	 * load mail template
	 *
	 * @return
	 */
	private Configuration getConfiguration() throws IOException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates/mail"));
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		return cfg;
	}

}
