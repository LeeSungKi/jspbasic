<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name="h_title" value = "Login Page"/>
</jsp:include>

<h1>로그인</h1>
<div align = "center">
<form action = "/myhome/login/loginLogic.jsp" method = "post">
	<!-- 아이디 입력란 -->
	<!-- 비밀번호 입력란 -->
	<!-- submit -->
	<table>
	<tr>
		<td><input type = "text" name = "user_id" value = "${cookie.auto_login.value }"
			placeholder="ID를 입력하세요." required></td>
	</tr>
	<tr>
		<td><input type = "password" name = "user_password" 
		placeholder="패스워드를 입력하세요." required></td>
	</tr>
	<tr>
		<td align="center"><input type = "submit" class="button" value = "LOGIN"></td>
	</tr>
	
	<tr>
		<td align = "center">
			<input type = "checkbox" name = "rememberId" value ="true">
			아이디 기억하기
		</td>
	</tr>
	
	</table>
</form>
</div>

<%@ include file = "/layout/footer.jsp" %>



