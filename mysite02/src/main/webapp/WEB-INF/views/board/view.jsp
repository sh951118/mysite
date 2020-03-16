<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- JSTL만의 메소드를 쓸 수있게 해줌  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 함수 처리를 쓸 수있게 해줌  -->
<%@ page import="com.douzone.mysite.vo.BoardVo"%>
<!DOCTYPE html>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board" class="board-form">
				<input type='hidden' name="no" value="${authcons.no }">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${authcons.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">${fn:replace(authcons.contents, newLine, "<br>")}</div>
						</td>
					</tr>
				</table>
				<form name=bot>
					<div class="bottom">
						<a href="${pageContext.request.contextPath }/board?a=list">글목록</a>
						<c:choose>
							<c:when test="${!empty authUser }">
								<a href="${pageContext.request.contextPath }/board?a=modifyform&no=${authcons.no }">글수정</a>
								<a href="${pageContext.request.contextPath }/board?a=commentfrom&gno=${authcons.gno }&ono=${authcons.ono }&depth=${authcons.depth }">답글</a>
							</c:when>
							<c:otherwise>
								<p align="center">로그인 후 컨텐츠 이용이 가능합니다.</p>
							</c:otherwise>
						</c:choose>
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>