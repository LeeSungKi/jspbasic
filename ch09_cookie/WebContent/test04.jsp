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
		//쿠키 조회하기
		//**모든 요청 정보에 쿠키가 들어있음
		Cookie[] c = request.getCookies();//쿠키는 한꺼번에만 받을 수 있음
		for(Cookie co :c ){
			if(co.getName().equals("myCookie")){
				pageContext.setAttribute("name", co.getName());
				pageContext.setAttribute("value", co.getValue());
			}
		}
							
	%>
	<p>
	쿠키 이름 : ${name }<br>
	쿠키 값(컨텐츠) : ${value }<br>
	</p> 
	<p>
		${cookie.JSESSIONID.value }<br>
		${cookie.myCookie.value }<br>
		
	</p>
	<%=session.getMaxInactiveInterval() %>초
</body>
</html>