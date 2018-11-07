documentReady(documentReadyFunc);

/**
 * 페이지가 준비됬을 경우 동작하는 함수
 * 
 * @author l2jong
 * @since 2018-11-06
 * @returns
 */
function documentReadyFunc() {
	var userEmailSelect = document.getElementById("userEmailSelect");
	userEmailSelect.addEventListener('change', function(e) {
		var emailVal = e.target.value;
		var userEmail2 = document.getElementById('userEmail2');
		if (emailVal === "") {
			// 직접 입력의 경우
			if (userEmail2.disabled !== false) {
				userEmail2.disabled = false;
			}
			userEmail2.value = "";
			userEmail2.focus();
		} else {
			if (userEmail2.disabled !== true) {
				userEmail2.disabled = true;
			}
			userEmail2.value = emailVal;
		}
	})
}

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

	var params = 'user_id=' + userId.value + '&user_password='
			+ userPassword.value + '&user_name=' + userName.value;

	var request = new XMLHttpRequest();
	request.open('POST', getContextPath() + '/user/process/join', true);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded; charset=UTF-8');

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

	request.send(params);
}