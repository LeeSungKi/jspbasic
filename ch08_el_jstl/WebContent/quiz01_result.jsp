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
		<c:choose>
			<c:when test="${n1*n2 eq param.user_answer}">
				정답입니다.
			</c:when>
			<c:otherwise>
				땡입니다.
			</c:otherwise>
		</c:choose>
	
	</c:set>
	${message}
</body>
</html>