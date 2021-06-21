package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public BlogVo findTitleAndLogo(String id) {
		return blogRepository.findTitleAndLogo(id);
	}

	public void createBlog(String id) {
		blogRepository.createBlog(id);
	}

	public void updateBasic(BlogVo vo) {
		blogRepository.updateBasic(vo);
	}

	public Map<String, Object> findAllCategory(String id) {
		
		List<CategoryVo> list = blogRepository.findAllCategory(id);
		Map<String, Object> map = new HashMap<>();
		int countOfCategory = blogRepository.countOfCategory();
		
		map.put("list",	 list);
		map.put("countOfCategory", countOfCategory);
		
		return map;
	}

	public void createCategory(String id) {
		blogRepository.createCategory(id);
	}

	public void addCategory(String id,  String name, String desc) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("desc", desc);
		blogRepository.addCategory(map);
	}

	public void deleteCategory(int no) {
		blogRepository.deleteCategory(no);
	}

	public void addPost(PostVo vo) {
		blogRepository.addPost(vo);
	}

	public Map<String, Object> findAllMain(String id) {
		List<CategoryVo> list = blogRepository.findAllCategory(id);
		
		Map<String, Object> map = new HashMap<>();
		
		return map;
	}


}
