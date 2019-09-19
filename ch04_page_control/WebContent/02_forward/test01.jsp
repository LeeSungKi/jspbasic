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
		< forward >
		- 페이지 이동 
		- 이동된 페이지가 주소창에 안보임 
		- 처음 페이지로 요청이 들어오면 탐캣이 포워드할 페이지의 service()를 실행
		  (처음페이지가 가지고 있었던 request 객체를 도착페이지 service()의 매개변수에 
		     그대로 넘겨줌)
		- 파라미터, 애트리뷰트를 전달받을 수 있음 
		- 외부 사이트로는 이동 불가 ( 컨테이너 내부에서만 포워드 가능 )    
		
		< forward 방법 >
		1. jsp : pageContext 내장 객체 사용 
		pageContext.forward("이동할 페이지"); 
		
		2. servlet : RequestDispatcher를 사용 
		(pageContext 내장객체가 없음)
		request.getRequestDispatcher("이동할 페이지").forward(request, response);
	 --%>
	이곳은 test01.jsp!
	<%
		request.setAttribute("c", 3);
		request.setAttribute("d", 4);
		pageContext.forward("result.jsp?a=1&b=2");
	%>
</body>
</html>