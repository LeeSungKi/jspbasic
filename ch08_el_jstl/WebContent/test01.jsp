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
	<c:set var="a" value = "10" scope= "page"/>
	<!-- pageContext.setAttribute("a", 10); -->
	<c:set var="b" value = "20" scope = "page"/>
	<c:set var = "c">30</c:set>
	
	
	${ pageScope.a + pageScope.b } <br>
	<c:if test = "${ a < b }">
		${a }(은)는 ${b }보다 작다.
	</c:if>
	<c:if test = "${ a > b }">
		${a }(은)는 ${b }보다 크다.
	</c:if>
	<c:if test = "${ a eq b }">
		${a }(은)는 ${b }와 같다.
	</c:if>
	
	<c:set var = "age" value = "25"/>
	<c:set var = "message" scope = "session">
		<c:choose>
			<c:when test = "${age >= 20 }">성인입니다.</c:when>
			<c:otherwise>미성년입니다.</c:otherwise>
		</c:choose>
	</c:set>
	
	${ message }
</body>
</html>










