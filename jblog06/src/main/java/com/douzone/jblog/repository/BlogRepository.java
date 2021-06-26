package com.douzone.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlsession;

	public BlogVo findTitleAndLogo(String id) {
		return sqlsession.selectOne("blog.findTitleAndLogo", id);
	}

	public void createBlog(String id) {
		sqlsession.insert("blog.createBlog", id);
	}

	public void updateBasic(BlogVo vo) {
		sqlsession.update("blog.updateBasic", vo);
	}

	
	// Category 
	
	public List<CategoryVo> findAllCategory(String id) {
		List<CategoryVo> list = sqlsession.selectList("category.findAllCategory", id);
		return list;
	}

	public void createCategory(String id) {
		sqlsession.insert("category.createCategory", id);
	}

	public int countOfCategory() {
		return sqlsession.selectOne("category.countOfCategory");
	}

	public void addCategory(Map<String, Object> map) {
		sqlsession.insert("category.addCategory", map);
	}

	public void deleteCategory(int no) {
		sqlsession.delete("category.deleteCategory", no);
	}
	
	// Post

	public void addPost(PostVo vo) {
		sqlsession.insert("post.addPost", vo);
	}

	public void createPost(int categoryNo) {
		sqlsession.insert("post.createPost",categoryNo);
	}

	public void deletePost(int categoryNo) {
		sqlsession.delete("post.deletePost", categoryNo);
	}
	
	
	
	// main) post, category
	

	public PostVo defaultPost(int categoryNo) {
		return sqlsession.selectOne("post.defaultPost", categoryNo);
		
	}


	public PostVo currentPost(int postNo) {
		return sqlsession.selectOne("post.currentPost", postNo);
	}

	public List<CategoryVo> findCategoryList(String id) {
		return sqlsession.selectList("category.findCategoryList", id);
	}

	public List<PostVo> findPostListByCategory(int categoryNo) {
		return sqlsession.selectList("post.findPostListByCategory", categoryNo);
	}

	public CategoryVo findFirstCategory(String id) {
		return sqlsession.selectOne("category.findFirstCategory", id);
	}


	

}
