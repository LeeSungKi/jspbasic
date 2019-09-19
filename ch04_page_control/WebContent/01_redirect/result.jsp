<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	이곳은 redirect된 결과 페이지!
	<br>
	받은 파라미터 : <br>
	a : <%= request.getParameter("a") %> <br>
	b : <%= request.getParameter("b") %> <br>
	받은 애트리뷰트 : <br>
	c : <%= request.getAttribute("c") %> <br>
	d : <%= request.getAttribute("d") %> <br> 
</body>
</html>