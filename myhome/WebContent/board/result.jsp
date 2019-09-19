<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var = "st" scope="page">
	<c:choose>
		<c:when test="${status == 'write' }">글쓰기</c:when>
		<c:when test="${status == 'delete' }">글삭제</c:when>
		<c:when test="${status == 'modify' }">글수정</c:when>
	</c:choose>
</c:set>
<script>
	alert("${st}에 ${result ? '성공':'실패'}하였습니다.");
	location.href="/myhome/list.brd";
</script>
