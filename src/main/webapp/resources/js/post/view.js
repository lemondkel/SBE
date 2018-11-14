documentReady(postViewReadyFunc);

/**
 * 화면이 정상적으로 로딩됬을 때 동작하는 함수
 * 
 * @author l2jong
 * @since 2018-11-14
 * @returns
 */
function postViewReadyFunc() {

	// 댓글 삭제 클릭 이벤트 리스너 부착.
	addCommentDeleteListener();
}

/**
 * 동적으로 추가 / 삭제되는 댓글들에 대한 클릭이벤트입니다.
 * 
 * @author l2jong
 * @since 2018-11-14
 * @param event
 * @returns
 */
function addCommentDeleteListener() {
	document.querySelector('.comment-list').addEventListener('click', function (event) {
		if (event.target.classList.contains('comment-delete-button')) {
			// console.log("삭제버튼 클릭!");
			var question = confirm("정말로 해당 댓글을 삭제하시겠습니까?");
			
			if (question === true) {
				// 확인을 누를 경우
				var item = event.target.parentElement.parentElement;
				var commentSeq = parseInt(item.getAttribute("data-comment-seq"));

				var request = new XMLHttpRequest();
				request.open('DELETE', getContextPath() + '/comment/process/delete/' + commentSeq, true);
				request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

				request.onload = function() {
					if (request.status >= 200 && request.status < 400) {
						// Success!
						var data = JSON.parse(request.responseText);
						console.log(data);
						alert(data.desc);
						if (data.result) {
							item.remove();
						}
					} else {
						alert("Server Error!");
					}		
				};

				request.onerror = function() {
					alert("Server Error!");
				};

				request.send();
			}
		}
	})
}

/**
 * 댓글을 작성합니다.
 * 
 * @author l2jong
 * @since 2018-11-14
 * @returns
 */
function writeComment() {
	var commentText = document.getElementById('commentText');
	var postSeq = document.getElementById('postSeq').value;

	if (commentText.value === "") {
		alert("내용을 입력하고 시도해주세요.");
		commentText.focus();
		return;
	}

	var parameterData = {
		commentContents : commentText.value,
		postSeq : postSeq
	};

	var request = new XMLHttpRequest();
	request.open('PUT', getContextPath() + '/comment/process/write', true);
	request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

	request.onload = function() {
		if (request.status >= 200 && request.status < 400) {
			// Success!
			var data = JSON.parse(request.responseText);
			console.log(data);
			alert(data.desc);
			if (data.result) {
				var commentList = document.getElementById("commentList");
				var html = '<li data-comment-seq="' + data.commentSeq + '">';
				html += '<div>';
				html += '<p>작성자: ' + data.username + '</p>';
				html += '<p>내용: ' + commentText.value + '</p>';
				html += '</div>';
				html += '<div>';
				html += '<button type="button">댓글수정</button>&nbsp;';
				html += '<button type="button" class="comment-delete-button" onclick="deleteComment(this)">댓글삭제</button>';
				html += '</div>';
				html += '</li>';

				commentList.insertAdjacentHTML('afterbegin', html);
			}
		} else {
			alert("빈 값을 채워주세요!");
		}
	};

	request.onerror = function() {
		alert("Server Error!");
	};

	request.send(JSON.stringify(parameterData));
}

var nextPage = 2;

/**
 * 더보기 버튼을 클릭하면 발생되는 이벤트입니다.
 * 
 * 더보기를 클릭하면 3개씩 다음 댓글을 가져옵니다.
 * 
 * @author l2jong
 * @since 2018-11-14
 * @returns
 */
function viewMore() {
	var postSeq = document.getElementById('postSeq').value;

	var request = new XMLHttpRequest();
	request.open('GET', getContextPath() + '/comment/process/view_more/' + postSeq + '/' + nextPage, true);
	request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

	request.onload = function() {
		if (request.status >= 200 && request.status < 400) {
			// Success!
			var data = JSON.parse(request.responseText);
			console.log(data);
			if (data.result) {
				var commentList = document.getElementById("commentList");
				
				var dataList = data.commentList;
				for (var i=0; i<dataList.length; i++) {
					var html = '<li data-comment-seq="' + dataList[i].commentSeq + '">';
					html += '<div>';
					html += '<p>작성자: ' + dataList[i].commentRegUsrName + '</p>';
					html += '<p>내용: ' + dataList[i].commentContents + '</p>';
					html += '</div>';
					html += '<div>';
					html += '<button type="button">댓글수정</button>&nbsp;';
					html += '<button type="button" class="comment-delete-button" onclick="deleteComment(this)">댓글삭제</button>';
					html += '</div>';
					html += '</li>';

					commentList.innerHTML += html;
				}
				document.getElementsByTagName('article')[0].scrollTo(0, document.getElementsByTagName('article')[0].scrollHeight);
			} else {
				alert(data.desc);
				document.getElementById('viewMoreBt').remove();
			}
			nextPage++;
		} else {
			alert("빈 값을 채워주세요!");
		}		
	};

	request.onerror = function() {
		alert("Server Error!");
	};

	request.send();
}

/**
 * 게시물을 삭제시킵니다.
 * 
 * @author l2jong
 * @since 2018-11-14
 * @returns
 */
function deletePost() {
	var question = confirm("정말로 해당 게시물을 삭제하시겠습니까?");
	
	if (question === true) {
		// 확인을 눌렀을 경우
		var postSeq = document.getElementById('postSeq').value;

		var request = new XMLHttpRequest();
		request.open('DELETE', getContextPath() + '/post/process/delete/' + postSeq, true);
		request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

		request.onload = function() {
			if (request.status >= 200 && request.status < 400) {
				// Success!
				var data = JSON.parse(request.responseText);
				console.log(data);
				alert(data.desc);
				if (data.result) {
					window.location.href = getContextPath() + '/board/1/0/1';
				}
			} else {
				alert("Server Error!");
			}
		};

		request.onerror = function() {
			alert("Server Error!");
		};

		request.send();
	}
}