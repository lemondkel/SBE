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
				var html = '<li>';
				html += '<div>';
				html += '<p>작성자: ' + data.username + '</p>';
				html += '<p>' + commentText.value + '</p>';
				html += '</div>';
				html += '<div>';
				html += '<button type="button">댓글수정</button>';
				html += '<button type="button">댓글삭제</button>';
				html += '</div>';
				html += '</li>';

				commentList.innerHTML += html;
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
					html += '<p>' + dataList[i].commentContents + '</p>';
					html += '</div>';
					html += '<div>';
					html += '<button type="button">댓글수정</button>';
					html += '<button type="button">댓글삭제</button>';
					html += '</div>';
					html += '</li>';

					commentList.innerHTML += html;
				}
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