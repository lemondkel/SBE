/**
 * 회원가입을 처리합니다.
 * 
 * @author l2jong
 * @since 2018-11-06
 * @returns
 */
function joinUser() {
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
	var userPasswordCheck = document.getElementById("userPasswordCheck");

	if (userPasswordCheck.value === "") {
		alert("값을 입력해주세요.");
		userPasswordCheck.focus();
		return false;
	}

	if (userPasswordCheck.value !== userPassword.value) {
		alert("비밀번호가 일치하지 않습니다.");
		userPassword.value = "";
		userPasswordCheck.value = "";
		userPassword.focus();
		return false;
	}

	var userName = document.getElementById("userName");

	if (userName.value === "") {
		alert("값을 입력해주세요.");
		userName.focus();
		return false;
	}
	
	var parameterData = {
		userId : userId.value,
		userPassword : userPassword.value,
		userName : userName.value
	};

	var request = new XMLHttpRequest();
	request.open('POST', getContextPath() + '/user/process/join', true);
	request.setRequestHeader('Content-Type',
			'application/json; charset=UTF-8');

	request.onload = function() {
		if (request.status >= 200 && request.status < 400) {
			// Success!
			var data = JSON.parse(request.responseText);
			console.log(data);
			alert(data.desc);
			if (data.result) {
				window.location.href = getContextPath() + '/';
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