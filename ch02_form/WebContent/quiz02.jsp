<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int n1 = (int)(Math.random()*8) + 2;
	int n2 = (int)(Math.random()*9) + 1;
	String quiz = n1 + "X" + n2 + "= ";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "quiz02_result.jsp">
		<%= quiz %>
		<input type = "text" name = "user_answer" >
		<input type = "submit" value = "결과 확인">
		<input type = "hidden" name = "real_answer" value = "<%=n1*n2 %>">
	</form>
</body>
</html>

