<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// POST 방식으로 전달된 파라미터는 한글이 깨짐
		// 따라서 parameter를 꺼내기 전에 request 메시지가 utf-8임을 알려야 함
		request.setCharacterEncoding("utf-8"); 
	
		// 동명의 파라미터가 여러개? 
		String a = request.getParameter("a"); 
		String[] b = request.getParameterValues("b"); 
		String c = request.getParameter("c");
		
		String message;
		message = "a : " + a +"<br>"
				+ "b : ";
		if( b != null ){
			for(String s : b){
				message += s + " ";
			}
		}
		message += "<br> c : " + c + "<br>";
	%>
	
	<%= message %>
	
	<%
		// 모든 파라미터의 이름을 얻어오기 
		Enumeration<String> names = request.getParameterNames();
		message = "전달받은 파라미터 이름들 <br>";
		while(names.hasMoreElements()){
			String name = names.nextElement();
			message += name + "<br>";
		}
	%>
	<%= message %>
	
	<% // 파라미터 이름-값 쌍을 모두 받아오기 (Map 객체로 받기)
		message = "";
		Map<String,String[]> map = request.getParameterMap();
		Set<String> keys = map.keySet();
		for(String k : keys){
			String[] arr = map.get(k); // k의 짝꿍 value ( k 파라미터의 값(들) )
			for(int i = 0; i < arr.length; ++i){
				message += "이름 : " + k + "<br>"
						 + "값 : " + arr[i] + "<br>";
			}
		}
	%>
	<%= message %>	
	
	요청 방식 : <%= request.getMethod() %> <br>
	요청 정보의 길이 : <%= request.getContentLength() %>byte <br>
	요청 페이지 URL : <%= request.getRequestURL() %><br>
	요청 페이지 URI : <%= request.getRequestURI() %><br>
	호스트(서버) IP : <%= request.getRemoteAddr() %><br>
	JSP 파일의 실제 서비스 경로 : <%= request.getRealPath("/01_request_2.jsp") %><br>
	요청 메시지의 컨텐트 타입(MIME) : <%= request.getContentType() %><br>
	요청 페이지의 컨텍스트 경로 : <%= request.getContextPath() %><br>
	프로토콜 : <%= request.getProtocol() %><br>
	
	<h2>헤더 정보</h2>
	<% 
		Enumeration<String> hNames = request.getHeaderNames();
		while(hNames.hasMoreElements()){
			String name = hNames.nextElement();
			out.println("헤더 이름 : " + name + "<br>"); 
			out.println("헤더 값 : " + request.getHeader(name) + "<br><br>");
		}
	%>
</body>
</html>











