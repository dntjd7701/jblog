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

//	public Map<String, Object> findAllCategory(String id) {
//		
//		List<CategoryVo> list = blogRepository.findAllCategory(id);
//		Map<String, Object> map = new HashMap<>();
//		int countOfCategory = blogRepository.countOfCategory();
//		
//		map.put("list",	 list);
//		map.put("countOfCategory", countOfCategory);
//		
//		return map;
//	}
	public List<CategoryVo> findAllCategory(String id) {
		
		List<CategoryVo> list = blogRepository.findAllCategory(id);
//		int countOfCategory = blogRepository.countOfCategory();
		return list;
	}
	public int  countOfCategory() {
		return  blogRepository.countOfCategory();
	}
	
	public void addCategory(String id,  String name, String desc) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("desc", desc);
		blogRepository.addCategory(map);
	}

	public void deleteCategoryAndPost(int categoryNo) {
		blogRepository.deleteCategory(categoryNo);
		// Category 삭제시 Post 삭제 
		blogRepository.deletePost(categoryNo);
	}

	public void addPost(PostVo vo) {
		blogRepository.addPost(vo);
	}
	
	public void createCategory(String id) {
		blogRepository.createCategory(id);
	}


	public void createPost(int findRecentlyCategoryNo) {
		blogRepository.createPost(findRecentlyCategoryNo);
	}

	
	// main) post, category 처리 

	public PostVo defaultPost(int categoryNo) {
		return blogRepository.defaultPost(categoryNo);
	}

	
	public List<CategoryVo> findCategoryList(String id) {
		return blogRepository.findCategoryList(id);
	}
	

	public List<PostVo> findPostListByCategory(int categoryNo) {
		return blogRepository.findPostListByCategory(categoryNo);
	}

	public PostVo currentPost(int postNo) {
		return blogRepository.currentPost(postNo);
	}

	public CategoryVo findFirstCategory(String id) {
		return blogRepository.findFirstCategory(id);
	}

	
	
}
