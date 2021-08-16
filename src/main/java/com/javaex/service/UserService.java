package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("[유저서비스.조인]");
		return userDao.insert(userVo);
	}
	
	//아이디 값 불러오기
	public boolean getUser(String id) {
		System.out.println("[유저서비스.겟유저]");
		UserVo userVo = userDao.selectUser(id);
		
		if(userVo == null) {//getId로 따로 꺼낸값을 비교해봤는데 널포인트 오류가남 왜지..
			return true;
		}else {
			return false;
			
		}
		
	}
	// 블로그+카테고리 생성
	public void blog(UserVo userVo) {
		System.out.println("[유저서비스.블로그]");
		
		String id = userVo.getId();
		String blogTitle = userVo.getUserName() + "님의 블로그 입니다";
		
		BlogVo blogVo = new BlogVo(id, blogTitle);
		blogDao.insertBlog(blogVo);
		
		categoryDao.insertDefault(id);
	}
	
	//로그인
	public UserVo getUser(UserVo userVo) {
		System.out.println("[유저서비스.로그인]");
		
		return userDao.selectUser(userVo);

	}

}
