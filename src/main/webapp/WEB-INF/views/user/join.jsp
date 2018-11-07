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
		<td>생년월일</td>
		<td>
			<input id="userBirthYear" type='text' />
			<label for="userBirthYear">년</label>
			<input id="userBirthMonth" type='text' />
			<label for="userBirthMonth">월</label>
			<input id="userBirthDate" type='text' />
			<label for="userBirthDate">일</label>
		</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>
			<input id="userGenderMale" value="M" type='radio' name="userGender"
				checked />
			<label for="userGenderMale">남</label>
			<input id="userGenderFemale" value="F" type='radio' name="userGender" />
			<label for="userGenderFemale">여</label>
		</td>
	</tr>
	<tr>
		<td>
			<label for="userContact">연락처</label>
		</td>
		<td>
			<input id="userContact" type='text' />
		</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>
			<input id="userEmail1" type='text' />
			<label for="userEmail1">@</label>
			<label for="userEmail2">
				<input id="userEmail2" type='text' />
			</label>
			<select id="userEmailSelect">
				<option value="">직접입력</option>
				<option value="naver.com">naver.com</option>
				<option value="daum.net">daum.net</option>
				<option value="google.com">google.com</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<label for="userZipCode">우편번호</label>
		</td>
		<td>
			<input id="userZipCode" type='text' />
		</td>
	</tr>
	<tr>
		<td>
			<label for="userAddress1">주소</label>
		</td>
		<td>
			<input id="userAddress1" type='text' />
		</td>
	</tr>
	<tr>
		<td>
			<label for="userAddress2">나머지 주소</label>
		</td>
		<td>
			<input id="userAddress2" type='text' />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<button type='button' onclick="joinUser()">가입하기</button>
		</td>
	</tr>
</table>