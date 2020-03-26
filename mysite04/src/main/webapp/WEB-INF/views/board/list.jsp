<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				<form id="search_form" action="${pageContext.request.contextPath }/board/list" method="get">
					<input type="text" id="kwd" name="kwd" value="${keyword }"> <input
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

					<c:forEach items="${map.list }"	var="vo" varStatus="status">			
						<tr>
							<td>${map.totalCount - (map.currentPage - 1)*map.listSize - status.index }</td>
							<c:choose>
								<c:when test="${vo.depth > 0 }">
									<td class="left" style="text-align:left; padding-left:${20*vo.depth }px">
										<img src="${pageContext.request.contextPath }/assets/images/reply.png">
										<a href="${pageContext.request.contextPath }/board/view/${vo.no }?p=${map.currentPage }&kwd=${map.keyword }">${vo.title }</a>
									</td>
								</c:when>
								<c:otherwise>
									<td class="left" style="text-align:left">
										<a href="${pageContext.request.contextPath }/board/view/${vo.no }?p=${map.currentPage }&kwd=${map.keyword }">${vo.title }</a>
									</td>
								</c:otherwise>
							</c:choose>
							<td>${vo.username }</td>
							<td>${vo.hit }</td>
							<td>${vo.regdate }</td>
							<td>
								<c:choose>
									<c:when test="${not empty authUser && authUser.no == vo.userno }">
										<a href="${pageContext.request.contextPath }/board/delete/${vo.no }?p=${map.currentPage }&kwd=${map.keyword }" class="del">
										<img src="${pageContext.request.contextPath }/assets/images/recycle.png">삭제</a>
									</c:when>
									<c:otherwise>
										&nbsp;
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${map.prevPage > 0 }">
							<li><a
								href="${pageContext.request.contextPath }/board/list?p=${map.prevPage }&kwd=${map.keyword }">◀</a></li>
						</c:if>

						<c:forEach begin="${map.beginPage }"
							end="${map.beginPage + map.listSize - 1 }" var="page">
							<c:choose>
								<c:when test="${map.endPage < page }">
									<li>${page }</li>
								</c:when>
								<c:when test="${map.currentPage == page }">
									<li class="selected">${page }</li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${pageContext.request.contextPath }/board/list?p=${page }&kwd=${map.keyword }">${page }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${map.nextPage > 0 }">
							<li><a
								href="${pageContext.request.contextPath }/board/list?p=${map.nextPage }&kwd=${map.keyword }">▶</a></li>
						</c:if>
					</ul>
				</div>
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a
							href="${pageContext.request.contextPath }/board/write?p=${map.currentPage }&kwd=${map.keyword }"
							id="new-book">글쓰기</a>
					</c:if>
				</div>
			<!-- pager 추가 -->
		</div>
	</div>
	<c:import url="/WEB-INF/views/includes/navigation.jsp">
		<c:param name="menu" value="board" />
	</c:import>
	<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>


