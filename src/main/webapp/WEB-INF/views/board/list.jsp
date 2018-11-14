<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<input type='hidden' value="${boardSeq}" id="boardSeq" />

<h2>게시판</h2>

<h3>카테고리</h3>
<ul>
	<li>
		<a href="${pageContext.request.contextPath}/board/${boardSeq}/0/1">전체</a>
	</li>
	<c:forEach items="${categoryList}" var="item">
		<li>
			<a
				href="${pageContext.request.contextPath}/board/${boardSeq}/${item.categorySeq}/1">${item.categoryName}</a>
		</li>
	</c:forEach>
</ul>

<button type='button' id="writeBoardBt" onclick="goBoardWrite()">글작성</button>

<h3>목록</h3>
<table>
	<thead>
		<tr>
			<th>글번호</th>
			<th>카테고리</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성자</th>
			<th>날짜</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${postList}" var="item">
			<tr>
				<td>${item.postSeq}</td>
				<td>${item.postCategoryName}</td>
				<td>
					<a href="${pageContext.request.contextPath}/post/view/${item.postSeq}">${item.postTitle}</a>
				</td>
				<td>${item.postViewCount}</td>
				<td>${item.postRegUserName}</td>
				<td>${item.postRegDate}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>