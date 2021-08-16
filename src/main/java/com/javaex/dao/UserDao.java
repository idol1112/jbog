package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입
	public int insert(UserVo userVo) {
		System.out.println("[유저다오.인설트]");
		int count = sqlSession.insert("user.insert", userVo);
		System.out.println(count+"건 저장되었습니다");
		return count;
	}
	
	public UserVo selectUser(String id) {//중복체크
		System.out.println("[유저를다오.겟유저]");
		return sqlSession.selectOne("user.selectUser", id);

	}
	
	public UserVo selectUser(UserVo userVo) {//로그인
		System.out.println("[유저를다오.겟유저2]");
		
		return sqlSession.selectOne("user.selectUser2", userVo);
	}

}
