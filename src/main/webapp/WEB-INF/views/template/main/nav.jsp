<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
	<c:if test="${login_user_id != null}">
		<li>
			<a href="${pageContext.request.contextPath}/">A게시판</a>
		</li>
		<li>
			<a href="javascript:logout()">로그아웃</a>
		</li>
	</c:if>
	<c:if test="${login_user_id == null}">
		<li>
			<a href="${pageContext.request.contextPath}/">로그인</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/user/join">회원가입</a>
		</li>
	</c:if>
</ul>