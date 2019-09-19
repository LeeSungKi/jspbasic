<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 
		< redirect >
		- 페이지 이동 
		- 리다이렉트된 페이지가 주소창에 보임 
		- 새로 고침처럼 새로운 페이지를 재요청하는 방식이므로 
		  forward와는 다르게 파라미터, 애트리뷰트가 넘어가진 않는다.
		  (단, get방식으로 새로운 주소에 파라미터를 첨부하는 것은 가능하다.)
		- 외부 사이트로 이동 가능 (네이버, 구글 등) 
	
		< redirect 방법 >
		1. html 
		<a href = "이동할 페이지">...</a>
		
		2. javascript 
		방법1. location.href="이동할 페이지"; 
		방법2. document.location.href="이동할 페이지";
		
		3. jsp 혹은 서블릿  
		response.sendRedirect("이동할 페이지"); 
	 --%>
	 
	 <%
	 	request.setAttribute("c", "abc"); 
	 	request.setAttribute("d", "def");
	 %>
	 
	 <a href = "result.jsp">result.jsp로 이동!</a>
	 <br>
	 <a href = "result.jsp?a=10&b=ㅋㅋㅋ">result.jsp + 파라미터</a>
</body>
</html>






