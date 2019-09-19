package com.myhome.servlet.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.beans.BoardDAO;
import com.myhome.beans.BoardVO; 

public class ListAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sPage = request.getParameter("page");
		int currentPage = sPage != null ? Integer.parseInt(sPage) : 1;
		ArrayList<BoardVO> list = BoardDAO.getInstance().getList(currentPage);
		
		int total = BoardDAO.getInstance().getTotalCount();
		request.setAttribute("totalPage", (total-1) / 5 + 1);
		request.setAttribute("brdList", list);
	}
}







