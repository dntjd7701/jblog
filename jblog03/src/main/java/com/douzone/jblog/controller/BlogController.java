package com.douzone.jblog.controller;

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

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
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
	

	
	
	
	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String main(
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<Long> pathNo1,
		@PathVariable("pathNo2") Optional<Long> pathNo2,
		Model model) {
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		BlogVo vo = blogService.findTitleAndLogo(id);
		
		Map<String, Object> map = blogService.findAllMain(id);
		model.addAttribute("map", map);
		application.setAttribute("title", vo.getTitle());
		application.setAttribute("logo", vo.getLogo());
		return "blog/main";
	}
	
	
	
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, Model model) {
		return "blog/admin/basic";
	}
	
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
	
	@RequestMapping("/admin/category")
	public String adminCategory(
			@PathVariable("id") String id,
			Model model) {
		Map<String, Object> map = blogService.findAllCategory(id);
		model.addAttribute("map", map);
		return "blog/admin/category";
	}
	
	
	@RequestMapping("/admin/category/add")
	public String addCategory(
			@PathVariable("id") String id,
			@RequestParam("name") String name,
			@RequestParam("desc") String desc) {
		blogService.addCategory(id, name, desc);
		return "redirect:/" + id + "/admin/category";
	}
	
	@RequestMapping("/admin/category/delete")
	public String deleteCategory(
			@PathVariable("id") String id,
			@RequestParam("no") int no) {
		blogService.deleteCategory(no);
		return "redirect:/" + id + "/admin/category";
	}
	
//	Write (Post)
	
	@RequestMapping("/admin/write")
	public String adminWrite(
			@PathVariable("id") String id,
			Model model) {
		Map<String, Object>  map = blogService.findAllCategory(id);
		model.addAttribute("map", map);
		
		return "blog/admin/write";
	}
	
	@RequestMapping("/admin/write/add")
	public String addPost(
			@PathVariable("id") String id,
			PostVo vo) {
		blogService.addPost(vo);
		return "redirect:/" + id;
	}

}
