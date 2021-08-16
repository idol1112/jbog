package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	@Autowired
	private SqlSession sqlsession;
	
	//회원가입 후 미분류 카테고리 insert
	public void insertDefault(String id) {
		System.out.println("[카테고리다오.디폴트인설트]");
		System.out.println(id);
		
		sqlsession.insert("category.insertDefault", id);
	}
	
	//카테고리 수정폼에 카테고리 리스트 전부 가져오기
	public List<CategoryVo> selectList(String id) {
		System.out.println("[카테고리다오.셀렉트]");
		
		return sqlsession.selectList("category.selectList", id);
	}
	
	//카테고리 추가후 cateNo값 넘겨주기
	public int insertCategory(CategoryVo categoryVo) {
		System.out.println("[카테고리다오.인설트]");
		
		return sqlsession.insert("category.insert", categoryVo);
	}
	
	//카테고리 추가한 카테고리 리스트 하나 가져오기
	public CategoryVo selectList(int cateNo) {
		System.out.println("[카테고리다오.셀렉트2]");
		
		return sqlsession.selectOne("category.selectList2", cateNo);
	}
	
	//카테고리 삭제
	public int deleteCategory(int cateNo) {
		System.out.println("[카테고리다오.삭제]");
		
		return sqlsession.delete("category.delete", cateNo);
		
	}

}
