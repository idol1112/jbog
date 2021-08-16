package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	//카테고리 수정폼
	@RequestMapping(value = "/{id}/admin/category", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyFormCategory(Model model, @PathVariable("id")String id) {
		
		System.out.println("[카테고리컨트롤러.수정폼]");
		model.addAttribute("id", id);
		
		return "/blog/admin/blog-admin-cate";
	}
	
	//카테고리 리스트 가져오기
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<CategoryVo> listCategory(@PathVariable("id")String id) {
		System.out.println("[카테고리컨트롤러.리스트]");
		System.out.println(id);
		
		return categoryService.getList(id);
	}
	
	//카테고리 추가(ajax)
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/add", method = { RequestMethod.GET, RequestMethod.POST })
	public CategoryVo addCategory(@ModelAttribute CategoryVo categoryVo, @PathVariable("id")String id) {
		System.out.println("[카테고리컨트롤러.추가]");
		
		return categoryService.addCategory(categoryVo);
	}
	
	//카테고리 삭제(ajax)
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/remove", method = { RequestMethod.GET, RequestMethod.POST })
	public int removeCategory(@RequestParam("cateNo") int cateNo, @PathVariable("id")String id) {
		System.out.println("[카테고리컨트롤러.삭제]");
		System.out.println(cateNo);
		int count = categoryService.removeCategory(cateNo);
		System.out.println(count+"건 삭제되었습니다");
		
		return count;
	}
}
