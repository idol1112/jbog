package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {
	@Autowired 
	private BlogDao blogDao;
	
	//블로그 정보 하나 가져오기
	public BlogVo getBlog(String id) {
		System.out.println("[블로그서비스.겟블로그]");
		
		return blogDao.selectBlog(id);
	}
	
	//블로그 관리 수정
	public void modifyBlog(String id, MultipartFile file, String blogTitle) {
		System.out.println("[블로그서비스.수정]");
		
		String saveDir = "C:\\javaStudy\\upload";
		
		//원파일 이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		if(!"".equals(orgName)) {
			
			BlogVo blogVo = new BlogVo(id, blogTitle);
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println(exName);
		
		//저장파일이름(관리때문에 겹치지 않는 새이름 부여)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);
		blogVo.setLogoFile(saveName);
		//파일패스
		String filePath = saveDir  + "\\" + saveName;
		System.out.println(filePath);
		
		//1.파일을 서버에 하드디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
					
			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(blogVo);
		blogDao.updateBlog(blogVo);
		
		}else if("".equals(orgName)) {
			System.out.println("로고이미지 변경사항이 없습니다");
			BlogVo blogVo = new BlogVo(id, blogTitle, "");
			blogDao.updateBlog(blogVo);
		}
		
	}

}
