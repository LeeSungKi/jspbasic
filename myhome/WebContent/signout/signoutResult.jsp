<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "h_title" value = "Signout Page"/>
</jsp:include>
<script>
	alert("${result}");
</script>
<input type = "button" value = "메인으로" 
		onclick = "location.href ='#'" class = "button">
<jsp:include page="/layout/footer.jsp"/>