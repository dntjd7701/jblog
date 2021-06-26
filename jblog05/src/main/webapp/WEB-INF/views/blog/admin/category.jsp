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
			<div id="content" class="full-screen">
			
			<c:import url="/WEB-INF/views/includes/admin-menu.jsp?sel=2" />
				
				
				
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      	<c:forEach items="${map.list }" var="vo" varStatus="status">	
					<tr>
						<td>${map.countOfCategory - status.index } </td>
						<td>${vo.name }</td>
						<td>${vo.count }</td>
						<td>${vo.desc }</td>
						<td><a href="${pageContext.request.contextPath }/${id }/admin/category/delete?no=${vo.no }"><img src="${pageContext.request.contextPath }/assets/images/delete.jpg"></a></td>
					</tr>  
				</c:forEach>
				
				
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가 </h4>
      			
      			<form action="${pageContext.request.contextPath }/${id }/admin/category/add">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
			     </form>
			     
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
		
	</div>
</body>
</html>