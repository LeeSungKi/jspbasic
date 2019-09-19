package com.myhome.servlet.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.beans.BoardDAO; 

public class UpdateAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt( request.getParameter("num")); 
		String newTitle = request.getParameter("title"); 
		String newContent = request.getParameter("content"); 
		
		boolean result = BoardDAO.getInstance().update(num, newTitle, newContent);
		request.setAttribute("result", result);
	}
}


















