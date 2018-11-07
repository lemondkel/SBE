/**
 * 로그인을 처리합니다.
 * 
 * @author l2jong
 * @since 2018-11-05
 * @returns
 */
function userLogin() {
	var userId = document.getElementById("userId");

	if (userId.value === "") {
		alert("값을 입력해주세요.");
		userId.focus();
		return false;
	}

	var userPassword = document.getElementById("userPassword");

	if (userPassword.value === "") {
		alert("값을 입력해주세요.");
		userPassword.focus();
		return false;
	}

	var parameterData = {
		userId : userId.value,
		userPassword : userPassword.value
	};

	var request = new XMLHttpRequest();
	request.open('POST', getContextPath() + '/user/process/login', true);
	request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

	request.onload = function() {
		if (request.status >= 200 && request.status < 400) {
			// Success!
			var data = JSON.parse(request.responseText);
			console.log(data);
			if (data.result) {
				// 통신 결과가 올바를 때
				window.location.href = getContextPath() + '/';
			} else {
				// 에러가 있을 때
				alert(data);
			}
		} else {
			alert("Server Error!");
		}
	};

	request.onerror = function() {
		alert("Server Error!");
	};

	request.send(JSON.stringify(parameterData));
}