<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
	
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		
		<div id="wrapper">
			<div id="content">
			<c:choose>
					<c:when test="${map.post.title == null }">
						<div class="blog-content">
							<h4>비어있음.</h4>
									<p>
										새로운 포스트를 작성해주세요. 
									<p>
						</div>
					</c:when>
					<c:otherwise>
							<div class="blog-content">
								<h4>${map.post.title }</h4>
								<p>
									${map.post.contents }
								<p>
							</div>
					</c:otherwise>
			</c:choose>
					
						<c:forEach items="${map.postList }" var="postVo">	
							<ul class="blog-list">
								<li><a href="${pageContext.request.contextPath }/${authUser.id }/${postVo.categoryNo }/${postVo.no }">${postVo.title }</a> <span>${postVo.regDate }</span>	</li>
							</ul>
						</c:forEach>
			</div>
		</div>





		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }${logo }">
			</div>
		</div>
		
		
		
		
		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${map.categoryList }" var="categoryVo" > 
					<li><a href="${pageContext.request.contextPath }/${authUser.id }/${categoryVo.no }/${map.postNo }">${categoryVo.name }</a></li>
			</c:forEach> 
				
			</ul>
		</div>
		
		
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
		
	</div>
</body>
</html>