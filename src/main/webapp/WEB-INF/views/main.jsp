<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<form id="loginForm"
	action="${pageContext.request.contextPath}/user/process/login"
	method="post">
	<label for="userId"> 아이디 </label>
	<input type="text" name="user_id" id="userId" />

	<label for="userPassword"> 비밀번호 </label>
	<input type="password" name="user_password" id="userPassword" />

	<button type="button" onclick="userLogin()">로그인</button>
</form>