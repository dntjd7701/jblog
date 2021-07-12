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
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/ejs/ejs.js"></script>

<script>
var listEJS = new EJS({
	url: "${pageContext.request.contextPath }/assets/ejs/category-list.ejs"
});

var listItemEJS = new EJS({
	url: "${pageContext.request.contextPath }/assets/ejs/category-item.ejs"
})
var messageDialog = function(message, callback){
	$("#dialog-message").dialog({
		modal: true,
		buttons: {
			"확인": function(){
				$(this).dialog("close");
			}
		},
		close: callback
	});
	$("#dialog-message p").text(message);
}
var fetch = function(){
	$.ajax({
		url: "${pageContext.request.contextPath}/${authUser.id }/api/admin/category",
		dataType:"json",
		type: "get",
		success: function(response){
			if(response.result != "success"){
				response.error(response.message);
				return;
			};
			let html = listEJS.render(response);
			$(".admin-cat tr:last").after(html);
		}
	});
}


$(function(){
	fetch();
	$("#form-add").submit(function(event){
		event.preventDefault();
		
		vo = {}; // add-form 의 데이터 받기용
		vo.name = $("#category-name").val(); //form의 input데이터 받아서 담기.
		vo.desc = $("#category-desc").val();
		/* Validations */
		if(vo.name == ""){
			messageDialog("카테고리명을 입력해주세요.");
			return;
		}
		if(vo.desc == ""){
			messageDialog("설명을 입력해주세요.");
			return;
		}
		
		// ajax
		$.ajax({
				url: "${pageContext.request.contextPath }/${authUser.id }/api/admin/category",
				dataType: "json",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(vo),
				success : function(response){ // callback
					if(response.result != "success"){
						response.error(response.message);
						return;
					};
					/* EJS내부 함수 render 사용, Mapping한 템플릿으로 적용 */
					//rendering
					let html = listItemEJS.render(response.data);
					$(".admin-cat tr:last").after(html);
					
					
					//table-id 삽입 
					$("#table-id").attr("value", "1");
					// form reset
					$("#form-add")[0].reset();
				},
				error: function(xhr, status, e){
					console.error(status + ":" + e);
				}
			});
		
	});
	
	//live event: 존재하지 않는 element의 이벤트 핸들러를 미리 등록
	// delegation(위임 -> document)
	$(document).on("click", ".admin-cat td a", function(event){
	/* 	$("#admin-cat td a").click(function(event){ */
		event.preventDefault();
		let no = $(this).data("no");
		console.log(no);
		deleteCategory(no);
	});
	
	deleteCategory= function(no){
		// ajax
		$.ajax({
				url: "${pageContext.request.contextPath }/${authUser.id }/api/admin/delete/" + no,
				dataType: "json",
				type: "delete",
				success : function(response){ // callback
					if(response.result != "success"){
						console.error(response.message);
						return;
					} 
					// 삭제 처리 
					// if(response.data != -1){ 
						$(".admin-cat tr[data-no=" + response.data + "]").remove();
						let len = $(".admin-cat tr").length;
						for(let i = 2;i<len;i++){
							$(".admin-cat tr:nth-child("+i+") td:first").text(len-i+2);
						}
						return;
						
					//}

				}
			});
		};
});


</script>
</head>
<body>
	<div id="container">
	
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		
		<h1>${authUser.id }</h1>
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
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가 </h4>
      			<form id="form-add" action="">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input id="category-name" type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input id="category-desc" type="text" name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
			     </form>
			     
			</div>
		</div>
		
		<!-- message dialog -->
			<div id="dialog-message" title="ERROR" style="display:none">
  				<p></p>
			</div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
		
	</div>
</body>
</html>