<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
int userAnswer = Integer.parseInt(request.getParameter("user_answer"));
int realAnswer = Integer.parseInt(request.getParameter("real_answer"));
String message = userAnswer == realAnswer ? "정답!" : "땡..";
%>
	<script> // 자바스크립트
		alert("<%= message %>"); // 알림창 띄우기
		location.href= "quiz02.jsp"; // 페이지 이동(해당 페이지를 새로 요청)
	</script>
	
</body>
</html>




