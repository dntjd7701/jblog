package com.douzone.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.vo.BlogVo;


@Service
public class FileUploadService {

	private static final String SAVE_PATH = "/Users/kang-woosung/uploads-mysite/";
	private static final String URL_BASE = "/assets/images/logo/";
	
	@Autowired
	private BlogService blogService;
	
	
	public String getUrl(MultipartFile file, String id) {
		String url = null;
		
	try {
		if(file.isEmpty()) {
			BlogVo vo = new BlogVo();
			vo = blogService.findTitleAndLogo(id);
			return url=vo.getLogo();
		}
		
		String originalFileName = file.getOriginalFilename();
		
		String extensionName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
		String saveFileName = generateSaveFileName(extensionName);
		
		
			byte[] data = file.getBytes();
		
		OutputStream os = new FileOutputStream(SAVE_PATH + saveFileName);
		os.write(data);
		os.close();
		
		url = URL_BASE + saveFileName;
		
	} catch (IOException e) {
		System.out.println("File upload failed.. read follow errors : " + e);
	}
		
	return url;
}



	private String generateSaveFileName(String extensionName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += "." + extensionName;
		
		return filename;
	}
	
	
	
	
}
