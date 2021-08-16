<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      		<input type="hidden" id="userId" name="id" value="${requestScope.id}">
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
	//화면이 로딩되기 직전
	$("document").ready(function() {
		console.log("화면 로딩 직전");
		var id = $("#userId").val();
		console.log(id);
		
		$.ajax({

			url : "${pageContext.request.contextPath }/"+ id +"/admin/category/list",
			type : "post",
			/* contentType : "application/json", */
			data : {id:id},
	
			//응답
			dataType : "json",
			success : function(cList) {
			/*성공시 처리해야될 코드 작성*/
			console.log(cList)
			
			for (var i = 0; i < cList.length; i++) {
				render(cList[i], "down");
			}


			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
			});
		
	});
	
	//카테고리 추가 버튼 클릭했을 때
	$("#btnAddCate").on("click", function(){
		console.log("카테고리 추가 버튼");
		
		var categoryVo = {
			cateName: $("[name='name']").val(),
			description: $("[name='desc']").val(),
			id: $("#userId").val()
		};
		
		$.ajax({

			url : "${pageContext.request.contextPath }/"+categoryVo.id+"/admin/category/add",
			type : "post",
			/* contentType : "application/json", */
			data : categoryVo,
	
			//응답
			dataType : "json",
			success : function(cateVo) {
			/*성공시 처리해야될 코드 작성*/
			console.log(cateVo);
			render(cateVo,"up");
			
			$("[name='name']").val("");
			$("[name='desc']").val("");


			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
			});
		
	});
	
	//카테고리 삭제버튼 눌렀을 때
	$("#cateList").on("click", "img", function(){
		console.log("카테고리 삭제 버튼");
		
		var id = $("#userId").val();
		var cateNo = $(this).data("cateno");
		console.log(cateNo);
		
		$.ajax({

			url : "${pageContext.request.contextPath }/"+id+"/admin/category/remove",
			type : "post",
			/* contentType : "application/json", */
			data : {cateNo:cateNo},
	
			//응답
			dataType : "json",
			success : function(count) {
			/*성공시 처리해야될 코드 작성*/
			if(count == 0){
				alert("삭제할 수 없습니다.");
			}else{
				$("#c-"+cateNo).remove();
			}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
			});
	});
	
	
	
	function render(categoryVo, type) {
		var str ="";
		str += '<tr id = "c-'+ categoryVo.cateNo +'">';
		str += '	<td>'+ categoryVo.cateNo +'</td>';
		str += '	<td>'+ categoryVo.cateName +'</td>';
		str += '	<td>7</td>';
		str += '	<td>'+ categoryVo.description +'</td>';
		str += '	<td class="text-center">';
		str += '	<img data-cateno="'+categoryVo.cateNo+'" class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '	</td>';
		str += '<tr>';
		
		if(type === 'down') {
			$("#cateList").append(str);
		}else if(type === 'up') {
			$("#cateList").prepend(str);
		}else {
			console.log("방향을 지정해주세요");
		}
		
	}


</script>




</html>