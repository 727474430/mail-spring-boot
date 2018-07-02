package com.raindrop.mail.spring.boot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raindrop.mail.spring.boot.model.MailModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MailServiceTest {

	private MockMvc mockMvc;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private MailService mailService;
	@Autowired
	private WebApplicationContext context;

	private MailModel mailModel;
	private Map<String, String> templateMap;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		mailModel = new MailModel();
		mailModel.setSubject("Spring Boot Mail Service Test");
		mailModel.setText("Hello 727474430, You are perfect");
		mailModel.setSendTo("727474430@qq.com");
		mailModel.setCcTo("727474430@qq.com");

		templateMap = getTemplateMap();
	}

	/**
	 * 初始化模板参数
	 *
	 * @return
	 */
	private Map<String,String> getTemplateMap() {
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("to", mailModel.getSendTo());
		resultMap.put("identifyingCode", "1234");
		return resultMap;
	}

	@Test
	public void sendBasicMail() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/mail/sentBasicMail")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(mailModel))
		)
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Assert.assertEquals("ok", result);
	}

	@Test
	public void sendAttachmentMail() throws Exception {
		FileInputStream fi = new FileInputStream(new File("C:/Users/Public/Pictures/Sample Pictures/004.png"));
		MockMultipartFile file = new MockMultipartFile("004.png", "004.png", null, fi);
		String result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/mail/sendAttachmentMail")
				.file(file)
				.content(objectMapper.writeValueAsString(mailModel))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
		)
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Assert.assertEquals("ok", result);
	}

	@Test
	public void sendInlineMail() throws Exception {
		mailModel.setText("<html><body><img src=\"cid:006.png\"></body></html>");
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/mail/sendInlineMail")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(mailModel))
		)
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Assert.assertEquals("ok", result);
	}

	@Test
	public void sendMailByTemplate() throws Exception {
		mailModel.setTemplateModel(templateMap);
		mailModel.setTemplateName("mail");
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/mail/sendMailByTemplate")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(mailModel))
		)
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		Assert.assertEquals("ok", result);
	}

}