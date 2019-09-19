<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "h_title" value = "Login Page"/>
</jsp:include>
<script>
	alert("${ requestScope.result }");
</script>
<input type = "button" value = "메인으로" class = "button" onclick = "location.href ='/myhome'">
<jsp:include page="/layout/footer.jsp" />
