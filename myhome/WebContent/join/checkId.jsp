<%@page import="com.myhome.beans.AccountDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function closeWindow( str ) {
		opener.joinForm.user_id.value = str;
		window.close(); 
		opener.joinForm.user_password.focus();
		// BOM 객체 
		// DOM 객체 
	}
</script>
</head>
<body>
	<c:choose>
		<c:when test = "${AccountDao.getInstance().isExist(param.id) }">
			<p>${param.id }(은)는 사용중입니다.</p>
			<form action = "checkId.jsp" method = "get">
				<input type = "text" name = "id" 
						placeholder ="새로운 아이디" required="required">
				<input type = "submit" value = "중복확인">
			</form>
		</c:when>
		<c:otherwise>
			<p>사용 가능한 아이디입니다.</p>
			<button onclick="closeWindow('${param.id}')">사용</button>
		</c:otherwise>
	</c:choose>
</body>
</html>






