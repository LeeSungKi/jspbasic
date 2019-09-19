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
		pageContext.setAttribute("pageAttr", 10);
		request.setAttribute("requestAttr", 20);
		session.setAttribute("sessionAttr", 30);
		application.setAttribute("applicationAttr", 40);
		
	%>
	<h1>이곳은 test01</h1>
	<p>page 스코프: <%=pageContext.getAttribute("pageAttr") %></p>
	<p>request 스코프: <%=request.getAttribute("requestArrt") %></p>
	<p>session 스코프: <%=session.getAttribute("sessionAttr") %></p>
	<p>application 스코프: <%=application.getAttribute("applicationAttr") %></p>
	<!--test02를 리다이렉트 형태로 이동  -->
	<a href = "test02.jsp">test02로 이동</a>
	
	<!--test03를 포워드 형태로 이동(리다이렉트 테스트 하려면 주석처리 하기)-->
	<%
		pageContext.forward("test02.jsp");
	%>

</body>
</html>