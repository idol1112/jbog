<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
			<h1><a href="${pageContext.request.contextPath }/${blogVo.id}">${blogVo.blogTitle }</a></h1>
			<ul class="clearfix">
				<!-- 로그인 전 메뉴 -->
				<c:choose>
					<c:when test="${authUser == null }">
						<li><a class="btn_s" href="${pageContext.request.contextPath }/user/lform">로그인</a></li>
					</c:when>
					
					<c:otherwise>
						<c:if test="${blogVo.id eq authUser.id }">
							<li><a class="btn_s" href="${pageContext.request.contextPath }/${blogVo.id}/admin/basic">내블로그 관리</a></li>
						</c:if>
						<li><a class="btn_s" href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
		 		
			</ul>
		</div>
		<!-- //header -->
		