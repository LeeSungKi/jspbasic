<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "h_title" value = "Welcome!"/>
</jsp:include>

<h1>이곳은 메인페이지!</h1>
<%
	out.write(request.getServletContext().getRealPath("/storage"));
%>
<jsp:include page="/layout/footer.jsp"/>