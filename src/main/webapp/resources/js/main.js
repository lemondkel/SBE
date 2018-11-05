/**
 * 로그인을 처리합니다.
 * 
 * @author l2jong
 * @since 2018-11-05
 * @returns
 */
function userLogin() {
	var userId = document.getElementById("userId").value;
	var userPassword = document.getElementById("userPassword").value;
	var params = 'user_id=' + userId + '&user_password=' + userPassword
	var request = new XMLHttpRequest();
	request.open('POST', getContextPath() + '/user/process/login', true);
	request.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded; charset=UTF-8');

	request.onload = function() {
		if (request.status >= 200 && request.status < 400) {
			// Success!
			var resp = request.responseText;
			console.log(resp);
		} else {
			// We reached our target server, but it returned an error

		}
	};

	request.onerror = function() {
		// There was a connection error of some sort
	};

	request.send(params);
}