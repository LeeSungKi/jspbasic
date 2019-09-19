<%@page import="com.myhome.beans.AccountVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "h_title" value = "Admin Page"/>
</jsp:include>
<div align = "center">
<table border = "1">
	<tr>
		<th>NUM</th>
		<th>ID</th>
		<th>EMAIL</th>
	</tr>
	<c:forEach var="vo" items="${ requestScope.account_list }">
		<tr>
			<td>${vo.num }</td>
			<td>${vo.id }</td>
			<td>${vo.email1 }@${vo.email2 }</td>	
			<td><button onclick = "location.href='adminpageDelete?num=${vo.num }'">강퇴</button>	
		</tr>
	</c:forEach>
</table>

</div>
<jsp:include page="/layout/footer.jsp"/>




