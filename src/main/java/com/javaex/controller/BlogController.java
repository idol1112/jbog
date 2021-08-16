package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String blog(Model model, @PathVariable("id")String id) {
		System.out.println("[블로그컨트롤러.블로그]");
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-main";
	}
	
	//블로그 관리 수정폼
	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyFormBlog(Model model, @PathVariable("id")String id) {
		System.out.println("[블로그컨트롤러.수정폼]");
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		return"blog/admin/blog-admin-basic";
	}
	
	//블로그 관리 수정
	@RequestMapping(value = "/{id}/admin/basic/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyBlog(@RequestParam(value="blogTitle",required=false,defaultValue="")String blogTitle,
							 @RequestParam(value="file",required=false,defaultValue="")MultipartFile file,
							 @PathVariable("id")String id) {
		System.out.println("[블로그컨트롤러.수정]");
		System.out.println(blogTitle);
		System.out.println(file.getOriginalFilename());
		
		blogService.modifyBlog(id, file, blogTitle);
		return "redirect:/{id}";
	}

}
