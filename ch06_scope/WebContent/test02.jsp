<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이곳은 test02</h1>
	<p>page 스코프: <%=pageContext.getAttribute("pageAttr") %></p>
	<p>request 스코프: <%=request.getAttribute("requestArrt") %></p>
	<p>session 스코프: <%=session.getAttribute("sessionAttr") %></p>
	<p>application 스코프: <%=application.getAttribute("applicationAttr") %></p>
	
</body>
</html>