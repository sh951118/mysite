<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- JSTL만의 메소드를 쓸 수있게 해줌  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <!-- 함수 처리를 쓸 수있게 해줌  -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %><!-- 에러처리 가능 @Valid로 들어 온 값 -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %><!-- jsp 상의 form테그 사용 가능 -->
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form:form modelAttribute="userVo"
						   id="join-form"
						   name="joinForm" 
						   method="post" 
						   action="${pageContext.request.contextPath }/user/join">
					<label class="block-label" for="name">이름</label>
					<form:input path="name"/>
					<p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
						<spring:hasBindErrors name="userVo">
							<c:if test='${errors.hasFieldErrors("name") }'>
								<spring:message code='${errors.getFieldError("name").codes[0] }'/>
							</c:if>
						</spring:hasBindErrors>
					</p>

					<!-- form 태그를 사용하면 오류가 발생해도 처음에 입력한 값을 지우지 않고 남겨둔다.!! 그렇기에 활용도가 높음 -->
					<!-- input에 path만 입력해주면 됨. -->
					<label class="block-label" for="email"><spring:message code="User.Join.Email"/></label>
					<form:input path="email"/>
					<input type="button" value="id 중복체크">
					<p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
						<form:errors path="email"/>
					</p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password"/>
					<p style="font-weight:bold; color:#f00; text-align:left; padding-left:0">
						<form:errors path="password"/>
					</p>
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
			
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>