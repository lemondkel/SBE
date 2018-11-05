<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="minimum-scale=1.0, width=device-width, maximum-scale=1, user-scalable=no">
<title>SI Board Example</title>
<meta name="description" content="This is SBE (SI Board Example).">
<meta name="keywords" content="SI,iCC,infobank,SBE,게시판,인포뱅크,SI팀">
<meta name="author" content="l2jong">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <link rel="shortcut icon" href="파비콘 주소"> -->
<script type="text/javascript">
	function getContextPath() {
		var contextPath = "<%=request.getContextPath()%>";
		return contextPath;
	}
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/common.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css<tiles:getAsString name="page-css" />"
	type="text/css">
<body>
	<header>
		<tiles:insertAttribute name="header" />
	</header>
	<nav>
		<tiles:insertAttribute name="nav" />	
	</nav>
	<article>
		<tiles:insertAttribute name="article" />
	</article>
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
	
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js<tiles:getAsString name="page-script" />"></script>
</body>
</html>