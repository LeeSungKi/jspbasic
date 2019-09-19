<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name = "h_title" value = "DOWNLOADS"/>
</jsp:include>

<%
	// storage의 진짜 경로 얻어오기
	String realPath = application.getRealPath("/storage");

	// 진짜 storage 폴더의 파일 목록을 File[] 로 받기
	File storage = new File(realPath);
	File[] list = storage.listFiles();
	System.out.println(list);
	// File[]을 통째로 pageContext 바구니의 attr로 넣기
	pageContext.setAttribute("list", list);
%>

<div align = "center">
<button onclick="location.href='upload.jsp'">업로드</button>


<!--파일 목록 (storage에 있는 파일 이름들을 나열할 것임)-->
<table border="1">
	<c:forEach var ="f" items="${list}">
		<tr>
			<td><a href ="download?file=${f.name}">${f.name} / ${f.length()}byte</a></td>
		</tr>
	
	</c:forEach>
 </table>

</div>



<jsp:include page ="/layout/footer.jsp"/> 