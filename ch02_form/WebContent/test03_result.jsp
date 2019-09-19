<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% // 자바 영역 
	
	// 파라미터 받기 
	String name = request.getParameter("userName"); 
	String[] subjects = request.getParameterValues("userSubject");
	// 체크박스 등으로 1개의 파라미터에 여러 value가 넘어왔을 때는 배열 형태로 받는다.
	String result = request.getParameter("userResult"); 
	
	String message = "이름 : " + name + "<br>과목 : ";
	for(String s : subjects){
		message += s + "<br>";
	}
	message += "사용자 평가 : " + result;				

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= message %>
</body>
</html>





