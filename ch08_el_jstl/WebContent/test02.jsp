<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach begin = "0" end ="10" step="2" var ="i">
		<p>${i }</p>
	</c:forEach>
	<%
		ArrayList<String> list = new ArrayList<>();
		list.add("aaaa");
		list.add("bbbb");
		list.add("cccc");
		list.add("dddd");
		list.add("eeee");
		//애트리뷰트로 등록
		session.setAttribute("aList", list);
	
	%>
	<c:forEach var = "s" items = "${aList }">
		<p>문자열 : ${s }</p>
	</c:forEach>
</body>
</html>










