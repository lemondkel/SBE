/**
 * 페이지가 준비됬을 경우 동작하는 함수
 * 
 * @author l2jong
 * @since 2018-11-06
 * @param fn
 * @returns
 */
function documentReady(fn) {
	if (document.attachEvent ? document.readyState === "complete"
			: document.readyState !== "loading") {
		fn();
	} else {
		document.addEventListener('DOMContentLoaded', fn);
	}
}

/**
 * 로그아웃을 처리합니다.
 * 
 * @author l2jong
 * @since 2018-11-06
 * @returns
 */
function logout() {
	var request = new XMLHttpRequest();
	request.open('POST', getContextPath() + '/user/process/logout', true);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded; charset=UTF-8');

	request.onload = function() {
		if (request.status >= 200 && request.status < 400) {
			// Success!
			var data = JSON.parse(request.responseText);
			console.log(data);
			if (data.result) {
				window.location.href = getContextPath() + '/';
			} else {
				alert(data.desc);
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
