<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page ="/layout/header.jsp">
	<jsp:param name = "h_title" value = "Board"/>
</jsp:include>

<!-- '글쓰기' 버튼이 있는 곳 -->
<c:if test="${currentId != null }">
	<button onclick="location.href='write.brd'" class = "button" style = "width:100px;align:center">글쓰기</button>
</c:if>

<div align = "center">
	<!-- 글목록 테이블이 있는 곳 -->
	<table border = "1">
		<tr>
			<th>num</th>
			<th style="width:55%">title</th>
			<th>writer</th>
			<th>hit</th>
			<th>regdate</th>
		</tr>
		<c:forEach var = "vo" items = "${brdList }">
		<tr>
			<td>${vo.num }</td>
			<td><a href="read.brd?num=${vo.num}">${vo.title }</a></td>
			<td>${vo.writer }</td>
			<td>${vo.hit }</td>
			<td>${vo.regdate }</td>
		</tr>
		</c:forEach>	
	</table>
</div>

<c:set var = "currentPage" value = "${ param.page != null ? param.page:1}"/>
<c:if test = "${currentPage > 3 }">
	<a href="list.brd?page=${currentPage-3 }">[이전]</a>
</c:if>
<c:if test = "${currentPage > 2 }">
	<a href="list.brd?page=${currentPage-2 }">[${currentPage-2 }]</a>
</c:if>
<c:if test = "${currentPage > 1 }">
	<a href="list.brd?page=${currentPage-1 }">[${currentPage-1 }]</a>
</c:if>
${currentPage }
<c:if test = "${currentPage < totalPage }">
	<a href="list.brd?page=${currentPage+1 }">[${currentPage+1 }]</a>
</c:if>
<c:if test = "${currentPage+1 < totalPage }">
	<a href="list.brd?page=${currentPage+2 }">[${currentPage+2 }]</a>
</c:if>
<c:if test = "${currentPage+2 < totalPage }">
	<a href="list.brd?page=${currentPage+3 }">[다음]</a>
</c:if>
<jsp:include page ="/layout/footer.jsp"/>










