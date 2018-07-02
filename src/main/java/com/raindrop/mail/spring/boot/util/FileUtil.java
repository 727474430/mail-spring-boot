package com.raindrop.mail.spring.boot.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @name: com.raindrop.mail.spring.boot.util.FileUtil.java
 * @description: 文件处理工具
 * @author: Wang Liang
 * @create Time: 2018/6/16 17:38
 */
public class FileUtil {

	/**
	 * 文件上传
	 *
	 * @param request
	 * @return 文件路径 (路径 + 名称)
	 */
	public static List<String> fileUpload(HttpServletRequest request) throws IOException {
		List<String> filePaths = new ArrayList<>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// if from type is multipart
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
			while (fileNames.hasNext()) {
				// get upload file
				MultipartFile file = multipartHttpServletRequest.getFile(fileNames.next());
				if (file != null) {
					// get upload file name
					String originalFilename = file.getOriginalFilename();
					if (!"".equals(originalFilename.trim())) {
						// combination file new name
						String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
						String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + fileType;
						// file storage path
						String savePath = "d:/img";
						File fileFolder = new File(savePath);
						if (!fileFolder.exists() && !fileFolder.isDirectory()) {
							fileFolder.mkdirs();
						}
						// save file
						File uploadFile = new File(savePath + File.separator + newFileName);
						file.transferTo(uploadFile);
						// add file path to list
						originalFilename = savePath + File.separator + newFileName;
						filePaths.add(originalFilename);
					}
				}
			}
		}
		return filePaths;
	}

}
