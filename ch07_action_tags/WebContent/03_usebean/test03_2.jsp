<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=request.getHeader("host")%>
	${ header.host }
	${ header.cookie }
	${ cookie.JSESSIONID.value } 
	<jsp:useBean id="p1" class="ch07_action_tags.Pokemon"/>
	<jsp:useBean id="p2" class="ch07_action_tags.Pokemon" scope = "session"/>
	<p> ${ p1.name } / ${ p1.lv } / ${p1.hp } </p>
	<p> ${ p2.name } / ${ p2.lv } / ${p2.hp } </p>
</body>
</html>