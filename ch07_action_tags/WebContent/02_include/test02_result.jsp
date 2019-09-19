<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
나는 test02_result.jsp <br>
<!-- 액션태그로 한글을 파라미터로 넣을 때는, 
	넣기 전, request.setChar..("UTF-8"); 을 해야 한다.
	(tomcat 설정을 변경해도 됨)
 -->
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:include page="test02.jsp">
	<jsp:param name = "a" value = "ㅋㅋㅋㅋ"/>
</jsp:include>
</body>
</html>



