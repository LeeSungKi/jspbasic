package com.myhome.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Action;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Action action = null;
		//ActionForward actionForward = null;
		
		
		StringBuffer requestURL = request.getRequestURL();
		
		int lastIndax = requestURL.lastIndexOf("/");//url의 마지막 '/'의 인덱스
		int lastIndax2 = requestURL.lastIndexOf(".brd");
		String path = requestURL.substring(lastIndax+1,lastIndax2);
		System.out.println(path);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
