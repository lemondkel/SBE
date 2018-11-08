<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<input type='hidden' value="${boardSeq}" id="boardSeq" />

<div>
	<div>
		<label for="category">카테고리</label>
		<select id="category">
			<c:forEach var="item" items="${categoryList}">
				<option value="${item.categorySeq}">${item.categoryName}</option>
			</c:forEach>
		</select>
	</div>

	<div>
		<label for="title">제목</label>
		<input type='text' id="title" placeholder="제목을 입력해주세요." />
	</div>

	<div>
		<label for="contents">내용</label>
		<textarea id="contents" placeholder="내용을 입력해주세요.">
</textarea>
	</div>

	<div>
		<button type='button' onclick="writePost()">작성하기</button>
	</div>
</div>
