<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="../include/header.jsp"%>
<!-- container -->
<div id="container">
	<!-- location_area -->
	<div class="location_area customer">
		<div class="box_inner">
			<h2 class="tit_page">
				스프링 <span class="in">in</span> 자바
			</h2>
			<p class="location">
				고객센터 <span class="path">/</span> 공지사항
			</p>
			<ul class="page_menu clear">
				<li><a href="#" class="on">공지사항</a></li>
				<li><a href="#">문의하기</a></li>
			</ul>
		</div>
	</div>
	<!-- //location_area -->

	<!-- bodytext_area -->
	<div class="bodytext_area box_inner">
		<ul class="bbsview_list">
			<li class="bbs_title">${boardVO.title}</li>
			<li class="bbs_hit"><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
					value="${boardVO.regdate}" /></span></li>
			<li class="bbs_date"><span>조회수:${boardVO.view_count}</span></li>
			<li class="bbs_content">
				<div class="editer_content">${boardVO.content}</div>
			</li>
			<li class="bbs_content"><a
				href="/download?filename=${boardVO.files[0]}">
				<br>${boardVO.files[0]}</a></li>
			</li>
			<c:set var="extName" value="${fn:split(boardVO.files[0],'.')}" />
			<c:set var="ext" value="${extName[fn:length(extName)-1]}" />
			<c:if test="${ext  eq 'jpg'}">
				<img src="/download?filename=${boardVO.files[0]}" title="첨부 파일 이미지">
			</c:if>
		</ul>
		<p class="btn_line txt_right">
			<a href="/board/list?page=${pageVO.page}" class="btn_bbs">목록</a>
		</p>
	</div>
	<!-- //bodytext_area -->

</div>
<!-- //container -->

<%@ include file="../include/footer.jsp"%>