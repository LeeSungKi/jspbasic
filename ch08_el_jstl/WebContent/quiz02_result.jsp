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
	<c:set var = "message" scope="page">
<c:forEach begin="1" end="9" step="1" var="i">
		<c:choose>
			<c:when test="${sessionScope.dan * i eq paramValues.a[i-1]}">
				<p>${dan}x${i}=${paramValues.a[i-1]}o</p>
			</c:when>
			<c:otherwise>
				<p style = "color:red">${dan}x${i}=${paramValues.a[i-1]}x</p>
			</c:otherwise>
		</c:choose>
</c:forEach>
		
	
	</c:set>
	${message}
</body>
</html>