<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ssera::${ param.h_title }</title>
<style>
	*{
		margin:0;
		padding:0;
	}
	a {
		text-decoration: none;
	}
	a.hover {
	  border: solid 4px #5e9bff;
	  border-radius: 4px;
	}
	
	#header, #footer {
		text-decoration: none; /* 링크의 밑줄 생기는 거 없애기 */
		background-color: #d6e1ff;
		height: 85px;
		width: 100%;
		text-align: center;
		line-height: 85px;
		font-size: 30px;
	}
	
	#main {
		min-height: 650px;
		text-align: center;
		font-size: 20px;
		padding: 15px;
	}
	
	input {
	 border: solid 4px #d6e1ff;
	 padding: 14px 20px;
	 margin: 8px 0;
	}
	
	.button {
	  width: 100%;
	  background-color: #d6e1ff;
	  color: white;
	  padding: 14px 20px;
	  margin: 8px 0;
	  border: none;
	  border-radius: 4px;
	  cursor: pointer;
	}
	
	.button:hover {
		background: #5e9bff; 
	}
	
	h1 {
		color: #5e9bff;
	}
	
</style>
</head>
<body>
	<div style = "width:100%;">
	<div id = "header">
		<!-- <a href="../login/loginView.jsp">LOGIN</a> -->
			<c:choose>
				<c:when test = "${sessionScope.currentId eq null }">
					<a href="/myhome/login/loginView.jsp">LOGIN</a>
					<a href="/myhome/join/joinView.jsp">JOIN</a>
				</c:when>
				<c:otherwise>
					${sessionScope.currentId} 님
					<a href="/myhome/login/logout">LOGOUT</a>
					<a href="/myhome/signout/signoutView.jsp">SIGNOUT</a>
					<a href="/myhome/mypage/mypage">MYPAGE</a>
				</c:otherwise>
			</c:choose>
		<a href="/myhome/list.brd">BOARD</a>
		<a href="/myhome/res/fileList.jsp">DOWNLOADS</a>
	</div>
	<div id = "main" >
		






