package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	// 회원가입 폼
	@RequestMapping(value = "/jform", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");

		return "user/joinForm";
	}
	
	//회원가입 폼(아이디 중복체크)
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean idCheck(@RequestParam("id") String id) {
		System.out.println("[UserController.idCheck()]");
		System.out.println(id);	

		return userService.getUser(id);
	}
	
	
	// 회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.join()]");

		userService.join(userVo);
		
		//블로그 생성
		userService.blog(userVo);

		return "user/joinSuccess";
	}
	
	// 로그인 폼
	@RequestMapping(value = "/lform", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController.lForm()]");
		
		return "user/loginForm";
	}
	
	//로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");
		UserVo authUser = userService.getUser(userVo);
		
		if (authUser == null) {
			return "redirect:/user/lform?result=fail";
		} else {
			
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}
	}
	//로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("[UserController.logout()]");
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
}
