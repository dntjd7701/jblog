package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping("/join")
	public String join() {
		return "user/join";
	}
	
	// 이제 회원가입을 만들어야돼
	// Autowired, service, repo, sql 고고 
	@RequestMapping(value="/joinsuccess", method=RequestMethod.POST)
	public String joinsuccess(UserVo vo) {
		userService.joinUser(vo);
		blogService.createBlog(vo.getId());
		blogService.createCategory(vo.getId());
		return "user/joinsuccess";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}
	
	
}
