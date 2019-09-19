<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "h_title" value = "Signout Page"/>
</jsp:include>
<style>
	input[type=text]{
		width:80%;
	}
	input[type=password]{
		width:80%;
	}
	
</style>
<div align = "center">
<form action = "signoutLogic.jsp" method = "post">
<h1>회원 탈퇴</h1>
	<table>
		<tr><td><input type = "text" name = "user_id" placeholder="아이디를 입력하세요."></td></tr>
		<tr><td><input type = "password" name = "user_password" placeholder="패스워드를 입력하세요."></td></tr>
		<tr><td><input type = "submit" class = "button" value = "탈퇴"></td></tr>
	</table>
</form>
</div>
<jsp:include page="/layout/footer.jsp"/>