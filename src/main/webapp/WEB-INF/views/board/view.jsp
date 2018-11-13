<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<input type='hidden' value="${postSeq}" id="postSeq" />

<div class="post-area">
	<h2>게시물</h2>

	<h3>[${postDetail.postCategoryName}] ${postDetail.postTitle}</h3>

	<p>작성자: ${postDetail.postRegUserId}</p>
	<p>작성일자: ${postDetail.postRegDate}</p>

	<label for="postContents">
		<textarea id="postContents" readonly>
			${postDetail.postContents}
		</textarea>
	</label>
</div>

<div class="button-area">
	<button type='button' id="updatePostBt" onclick="updatePost()">글수정</button>
	<button type='button' id="deletePostBt" onclick="deletePost()">글삭제</button>
	<button type='button' onclick="window.history.back()">뒤로가기</button>
</div>

<div class="comment-area">
	<ul class="comment-list">
		<li>
			<div>
				<p>작성자: 이두종</p>
				<p>내용: ㅁ뉴ㅜ임누아ㅣㅁ누아ㅣㅁ누아ㅣ</p>
			</div>
			<div>
				<button type='button'>댓글수정</button>
				<button type='button'>댓글삭제</button>
			</div>
		</li>
	</ul>
</div>