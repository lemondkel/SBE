<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page session="false"%>

<table>
	<tr>
		<td>
			<label for="userId">아이디</label>
		</td>
		<td>
			<input id="userId" type='text' />
		</td>
	</tr>
	<tr>
		<td>
			<label for="userPassword">비밀번호</label>
		</td>
		<td>
			<input id="userPassword" type='password' />
		</td>
	</tr>
	<tr>
		<td>
			<label for="userPasswordCheck">비밀번호 확인</label>
		</td>
		<td>
			<input id="userPasswordCheck" type='password' />
		</td>
	</tr>
	<tr>
		<td>
			<label for="userName">이름</label>
		</td>
		<td>
			<input id="userName" type='text' />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<button type='button' onclick="joinUser()">가입하기</button>
		</td>
	</tr>
</table>