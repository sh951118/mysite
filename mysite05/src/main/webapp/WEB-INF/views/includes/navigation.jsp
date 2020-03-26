<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- JSTL만의 메소드를 쓸 수있게 해줌  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- 함수 처리를 쓸 수있게 해줌  -->
		<div id="navigation">
			<ul>
				<li><a href="${pageContext.request.contextPath }">메인 페이지</a></li>
				<li><a href="${pageContext.request.contextPath }/guestbook/add">방명록</a></li>
				<li><a href="${pageContext.request.contextPath }/board/list">게시판</a></li>
			</ul>
		</div>