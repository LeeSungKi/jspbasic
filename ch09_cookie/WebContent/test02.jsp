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
		//쿠키 만들기
		Cookie c = new Cookie("myCookie","hello");
							//    이름   ,   값(알파벳,숫자만 권장)
							c.setPath("/");
		response.addCookie(c);
							
	%>
	쿠키 저장 완료! 
</body>
</html>