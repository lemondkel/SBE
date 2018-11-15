<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>

<input type='hidden' value="${boardSeq}" id="boardSeq" />
<input type='hidden' value="${categorySeq}" id="categorySeq" />
<input type='hidden' value="${postSum}" id="postSum" />
<input type='hidden' value="${page}" id="currentPage" />

<div>
	<h2>게시판: ${boardName}</h2>

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
</div>

<div class="post-area">
	<button type='button' id="writeBoardBt" onclick="goBoardWrite()">글작성</button>

	<h3>게시물 목록</h3>
	<table class="post-list">
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
			<c:if test="${fn:length(postList) == 0}">
				<tr>
					<td style="text-align: center;" colspan="6">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${postList}" var="item">
				<tr>
					<td>${item.postSeq}</td>
					<td>${item.postCategoryName}</td>
					<td>
						<a
							href="${pageContext.request.contextPath}/post/view/${item.postSeq}">${item.postTitle}</a>
					</td>
					<td>${item.postViewCount}</td>
					<td>${item.postRegUserName}</td>
					<td>${item.postRegDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<c:if test="${postSum > 10}">
	<div class="page-area">
		<ul id="pagingList" class="paging-list"></ul>
	</div>
</c:if>