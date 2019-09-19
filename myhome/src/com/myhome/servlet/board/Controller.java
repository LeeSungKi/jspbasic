package com.myhome.servlet.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.brd")  // URL : /myhome/(뭐든 상관없음 ).brd
public class Controller extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Action action = null;
		ActionForward actionForward = null;
		
		String requestURL = request.getRequestURL().toString();
		
		int lastIndex = requestURL.lastIndexOf("/"); // url의 마지막 '/'의 인덱스
		int lastIndex2 = requestURL.lastIndexOf(".brd");
		String path = requestURL.substring(lastIndex+1, lastIndex2);
		System.out.println(requestURL);
		System.out.println(path);
		
		
		if("list".equals(path)) {
			action = new ListAction();
			actionForward = new ActionForward("listView.brd", false);
		}
		else if("listView".equals(path)) {
			actionForward = new ActionForward("board/listView.jsp", false);
		}
		else if("write".equals(path)) {
			actionForward = new ActionForward("board/writeView.jsp", true);
		}
		else if("writeLogic".equals(path)) {
			request.setAttribute("status", "write");
			action = new WriteAction();
			actionForward = new ActionForward("result.brd", false);
		}
		else if("read".equals(path)) {
			action = new ReadAction();
			actionForward = new ActionForward("readView.brd", false);
		}
		else if("readView".equals(path)) {
			actionForward = new ActionForward("board/readView.jsp", false); 
		}
		
		else if("deleteLogic".equals(path)) {
			request.setAttribute("status", "delete");
			action = new DeleteAction();
			actionForward = new ActionForward("result.brd", false);
		}
		else if("updateLogic".equals(path)) {
			action = new ReadAction(); // 일단 원문을 가져옴 (수정 form에 사용해야 하기때문)
			actionForward = new ActionForward("/board/updateView.jsp", false);
		}
		else if("updateLogic2".equals(path)) {
			request.setAttribute("status", "modify");
			action = new UpdateAction();
			actionForward = new ActionForward("result.brd", false);
		}
		
		
		else if("result".equals(path)) {
			actionForward = new ActionForward("/board/result.jsp", false);
		}
		else if("writeReply".equals(path)) {
			action = new WriteReplyAction();
			actionForward = new ActionForward("/read.brd?num=" + request.getParameter("reBoardNum"), false);
		}
		
		
		if(action != null) {
			action.execute(request, response);
		}
		
		if(actionForward.isRedirect()) { // 다음 경로로 redirect?
			response.sendRedirect(request.getContextPath() + "/" + actionForward.getNextPath());
		}
		else { // forward?
			request.getRequestDispatcher(actionForward.getNextPath())
				   .forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
