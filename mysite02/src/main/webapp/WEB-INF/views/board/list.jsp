<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.douzone.mysite.vo.BoardVo"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath }/board?a=find&title=${vo.title }"
					method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>타이틀</th>
						<th>이름</th>
						<th>조회수</th>
						<th>시간</th>
						<th>&nbsp;</th>
					</tr>

					<c:set var='listCount' value='${fn:length(list)}' />
					<c:forEach items="${list }" var="vo" varStatus='status'>
						<tr>
							<td>${listCount-status.index }</td>
							<td style="text-align:left; padding-left:${20*vo.depth }px">
								<c:choose>
									<c:when test='${empty vo.title }'>
											<c:if test="${vo.depth > 0 }">
												<img src='/mysite02/assets/images/reply.png'>
											</c:if>
											<a>삭제된 게시글</a>					
									</c:when>
									<c:otherwise>
										<c:if test="${vo.depth > 0 }">
											<img src='/mysite02/assets/images/reply.png'>
										</c:if>
										<a href="${pageContext.request.contextPath }/board?a=view&no=${vo.no }&gno=${vo.gno }&ono=${vo.ono }&depth=${vo.depth }">${vo.title }</a>
									</c:otherwise>
								</c:choose>
							</td>
							<td>${vo.username }</td>
							<td>${vo.hit }</td>
							<td>${vo.regdate }</td>
							<c:if test="${authUser.no eq vo.userno }">
								<td><c:if test='${!empty vo.title }'>
										
										<a href="${pageContext.request.contextPath }/board?a=delete&no=${vo.no }&userno=${vo.userno }"
											class="del">
											<img src='/mysite02/assets/images/recycle.png'></a>
									</c:if></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<a
						href="${pageContext.request.contextPath }/board?a=writeform&userno=${authUser.no}"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>


