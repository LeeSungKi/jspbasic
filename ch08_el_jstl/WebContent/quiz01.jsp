<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="n1" value="${ Integer.valueOf(Math.random()*9 + 1) }" scope = "session"/>
<c:set var="n2" value="${ Integer.valueOf(Math.random()*9 + 1) }" scope = "session"/>

<form action = "quiz01_result.jsp" method = "post">
<!--  
<input type = "hidden" name = "correct_answer" value = "${n1*n2}">
-->
<p>${n1 }x ${n2 }</p>
<input type = "text" name ="user_answer">
<input type = "submit">

</form>

</body>
</html>