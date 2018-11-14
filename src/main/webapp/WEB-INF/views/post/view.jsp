<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib
	prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>

<input type='hidden' value="${postSeq}" id="postSeq" />

<div class="post-area">
	<h2>게시물</h2>

	<h3>[${postDetail.postCategoryName}] ${postDetail.postTitle}</h3>

	<p>작성자: ${postDetail.postRegUserId}</p>
	<p>작성일자: ${postDetail.postRegDate}</p>

	<pre id="postContents">${postDetail.postContents}</pre>
</div>

<div class="button-area">
	<button type='button' id="updatePostBt" onclick="updatePost()">글수정</button>
	<button type='button' id="deletePostBt" onclick="deletePost()">글삭제</button>
	<button type='button' onclick="window.history.back()">뒤로가기</button>
</div>

<div class="comment-area">
	<h2>댓글</h2>

	<div class="comment-write-area">
		<textarea id="commentText"></textarea>
		<button type='button' onclick="writeComment()">작성하기</button>
	</div>

	<ul class="comment-list" id="commentList">
		<c:forEach items="${commentList}" var="item">
			<li data-comment-seq="${item.commentSeq}">
				<div>
					<p>작성자: ${item.commentRegUsrName}</p>
					<p>내용: ${item.commentContents}</p>
				</div>
				<div>
					<button type='button'>댓글수정</button>
					<button type='button' class="comment-delete-button"
						onclick="deleteComment(this)">댓글삭제</button>
				</div>
			</li>
		</c:forEach>
	</ul>

	<c:if test="${fn:length(commentList) == 3}">
		<button id="viewMoreBt" type="button" style="margin: 0 10px;"
			onclick="viewMore()">더 보기</button>
	</c:if>
</div>