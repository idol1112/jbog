package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	//카테고리 리스트 가져오기
	public List<CategoryVo> getList(String id) {
		System.out.println("[카테고리서비스.겟리스트]");
		
		return categoryDao.selectList(id);
		
	}
	
	//카테고리 추가
	public CategoryVo addCategory(CategoryVo categoryVo) {
		System.out.println("[카테고리서비스.추가]");
		//System.out.println(categoryVo);//no값 없음
		categoryDao.insertCategory(categoryVo);
		//System.out.println(categoryVo);//no값이 생김
		
		int cateNo = categoryVo.getCateNo();
		return categoryDao.selectList(cateNo);
	}
	
	//카테고리 삭제
	public int removeCategory(int cateNo) {
		System.out.println("[카테고리서비스.삭제]");
		
		return categoryDao.deleteCategory(cateNo);
		
	}

}
