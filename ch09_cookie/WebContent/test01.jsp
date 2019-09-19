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
		c.setMaxAge(0);//수명을0초로
		//c.setMaxAge(60*60*24); 수명을 하루로
		c.setPath("/");
		response.addCookie(c);
							
	%>
	쿠키 삭제 완료! 
</body>
</html>