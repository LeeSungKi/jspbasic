<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="p1" class="ch07_action_tags.Pokemon"/>
	<jsp:setProperty name="p1" property = "name" value="pikachu"/>
	<jsp:setProperty name="p1" property = "lv" value = "10" />
	<jsp:setProperty name="p1" property = "hp" value = "1000" />		
	
	<jsp:useBean id="p2" class="ch07_action_tags.Pokemon" scope ="session"/>
	<jsp:setProperty name="p2" property = "name" value="purin"/>
	<jsp:setProperty name="p2" property = "lv" value = "20" />
	<jsp:setProperty name="p2" property = "hp" value = "2000" />	
	
	
	<p>p1의 이름 : <jsp:getProperty name = "p1" property = "name"/></p>
	<p>p1의 레벨 : <jsp:getProperty name = "p1" property = "lv"/></p>
	<p>p1의 체력 : <jsp:getProperty name = "p1" property = "hp"/></p>
	<p>p1의 체력 : ${p1.hp }</p>
	
	<p>p2의 이름 : <jsp:getProperty name = "p2" property = "name"/></p>
	<p>p2의 레벨 : <jsp:getProperty name = "p2" property = "lv"/></p>
	<p>p2의 체력 : ${p2.hp }</p>
</body>
</html>





