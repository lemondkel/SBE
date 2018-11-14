/**
 * 게시물을 작성한다.
 * 
 * @author l2jong
 * @since 2018-11-08
 * @returns
 */
function writePost() {
	var category = document.getElementById('category');
	var title = document.getElementById("title");
	var contents = document.getElementById("contents");

	var parameterData = {
		categorySeq : category.value,
		postTitle : title.value,
		postContents : contents.value
	};

	var request = new XMLHttpRequest();
	request.open('PUT', getContextPath() + '/post/process/write', true);
	request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

	request.onload = function() {
		if (request.status >= 200 && request.status < 400) {
			// Success!
			var data = JSON.parse(request.responseText);
			console.log(data);
			alert(data.desc);
			if (data.result) {
				var boardSeq = document.getElementById('boardSeq').value;
				window.location.href = getContextPath() + '/board/' + boardSeq
						+ '/' + category.value + '/' + 1;
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