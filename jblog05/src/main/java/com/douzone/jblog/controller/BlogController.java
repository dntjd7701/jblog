package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private ServletContext application;
	@Autowired
	private FileUploadService fileUploadService;
	
	
//	 Main 
	
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String main(
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<Integer> pathNo1,
		@PathVariable("pathNo2") Optional<Integer> pathNo2,
		Model model) {
		
		// 자 ~~~ 초기값 주고,
		// 아무값이 안들어왔을땐, id로 categoryNo값 구하고, No에 의한 post의 limit로 구하자. 
		int categoryNo = 0;
		int postNo=0;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
			PostVo post = blogService.defaultPost(categoryNo);
			if(post == null) {
				postNo = 0;
			} else {
				postNo = post.getNo();
			}
		} else {
			CategoryVo categoryVo = blogService.findFirstCategory(id);
			if(categoryVo == null) {
				categoryNo = 0;
			} else {
				categoryNo = categoryVo.getNo();
			}
			PostVo post = blogService.defaultPost(categoryNo);
			if(post == null) {
				postNo = 0;
			} else {
				postNo = post.getNo();
			}
			
		}

		
		List<CategoryVo> categoryList = blogService.findCategoryList(id);
		List<PostVo> postList = blogService.findPostListByCategory(categoryNo);
		System.out.println(postNo);
		
		PostVo post = blogService.currentPost(postNo);
		Map<String,Object> map = new HashMap<>();
		map.put("postNo", postNo);
		map.put("categoryList", categoryList);
		map.put("postList", postList);
		map.put("post", post);
		
		model.addAttribute("map", map);
		
		
		// title, logo #scope=application
		BlogVo vo = blogService.findTitleAndLogo(id);
		
		application.setAttribute("title", vo.getTitle());
		application.setAttribute("logo", vo.getLogo());
		
		return "blog/main";
	}
	
	
	
//	Admin
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, Model model) {
		return "blog/admin/basic";
	}
	@Auth
	@RequestMapping("/admin/basic/upload")
	public String updateTitleAndLogo(
			@RequestParam("file1") MultipartFile file1,
			@PathVariable("id") String id,
			BlogVo vo) {
		String url = fileUploadService.getUrl(file1, id);
		vo.setId(id);
		vo.setLogo(url);
		blogService.updateBasic(vo);
		
		return "redirect:/" + id;
	}
	
//	Category
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(
			@PathVariable("id") String id,
			Model model) {
		Map<String, Object> map = blogService.findAllCategory(id);
		model.addAttribute("map", map);
		return "blog/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/category/add")
	public String addCategory(
			@PathVariable("id") String id,
			@RequestParam("name") String name,
			@RequestParam("desc") String desc) {
		blogService.addCategory(id, name, desc);
		return "redirect:/" + id + "/admin/category";
	}
	@Auth
	@RequestMapping("/admin/category/delete")
	public String deleteCategory(
			@PathVariable("id") String id,
			@RequestParam("no") int no) {
		
		blogService.deleteCategoryAndPost(no);
		return "redirect:/" + id + "/admin/category";
	}
	
//	Write (Post)
	@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(
			@PathVariable("id") String id,
			Model model) {
		Map<String, Object>  map = blogService.findAllCategory(id);
		model.addAttribute("map", map);
		
		return "blog/admin/write";
	}
	@Auth
	@RequestMapping("/admin/write/add")
	public String addPost(
			@PathVariable("id") String id,
			PostVo vo) {
		blogService.addPost(vo);
		return "redirect:/" + id;
	}

}
