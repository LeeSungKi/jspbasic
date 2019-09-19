<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" info = "로그인 view 페이지"%>
<%--
	java를 사용하여 테이블 조회
	결과저장
--%>
<%
	String db_id = "web1400"; 
	String db_pw = "jsppassword"; 
	String db_url = "jdbc:oracle:thin:@localhost:1521:xe"; 
	
	Class.forName("oracle.jdbc.driver.OracleDriver"); 
	
	Connection con = DriverManager.getConnection(db_url, db_id, db_pw); 
	PreparedStatement ps = con.prepareStatement(
			"SELECT id FROM account WHERE id = ? AND password = ?"); 
	ps.setString(1, request.getParameter("user_id"));
	ps.setString(2, request.getParameter("user_password"));
	
	ResultSet rs = ps.executeQuery();
	String message = "아이디 혹은 비밀번호를 다시 확인해주세요."; 
	if(rs.next()){
		message = rs.getString("id") + "님 환영합니다!";
	}
	
	request.setAttribute("result", message);
	pageContext.forward("loginResult.jsp");
	
%>







