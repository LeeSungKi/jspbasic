<%@page import="com.myhome.beans.AccountVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/layout/header.jsp">
	<jsp:param name = "h_title" value = "My Page"/>
</jsp:include>
<div align = "center">

<c:choose>
<c:when test = "${requestScope.result }">	
<table border = "1">
	<tr>
		<th>ID</th>
		<td>${ requestScope.vo.id}</td>
	</tr>
	
	<tr>
		<th>PASSWORD</th>
		<td>${ requestScope.vo.password}</td>
	</tr>
	<tr>
		<th>EMAIL</th>
		<td>${ requestScope.vo.email1}@${ requestScope.vo.email2}</td>
	</tr>
	<tr>
		<th>ADDRESS</th>
		<td>
			<p>${ requestScope.vo.zip}</p>
			<p>${ requestScope.vo.address1}</p>
			<p>${ requestScope.vo.address2}</p>
		</td>
	</tr>
	<tr>
		<th>REGDATE</th>
		<td>${ requestScope.vo.regdate}</td>
	</tr>
</table>
	<c:if test="${ 'admin' == vo.id }">
		<button onclick = "location.href='adminpage'">모든 회원 보기</button>
	</c:if>
</c:when>
<c:otherwise>
	로그인한 회원만 조회 가능합니다.
</c:otherwise>
</c:choose>
</div>
<jsp:include page="/layout/footer.jsp"/>