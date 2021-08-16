package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	@Autowired
	private SqlSession sqlSession;
	
	//가입한 유저 정보 데이터베이스에 넣기
	public void insertBlog(BlogVo blogVo) {
		System.out.println("[블로그다오.인설트]");
		sqlSession.insert("blog.insert", blogVo);
	}
	
	public BlogVo selectBlog(String id) {
		System.out.println("[블로그다오.셀렉트]");
		
		return sqlSession.selectOne("blog.selectBlog", id);

	}
	
	public void updateBlog(BlogVo blogVo) {
		System.out.println("[블로그다오.업데이트]");
		
		sqlSession.update("blog.update", blogVo);
	}

}
