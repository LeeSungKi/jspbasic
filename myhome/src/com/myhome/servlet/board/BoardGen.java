package com.myhome.servlet.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.beans.BoardDAO;
import com.myhome.beans.BoardVO; 

@WebServlet("/gen")
public class BoardGen extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardDAO dao = BoardDAO.getInstance();
		for(int i = 1; i <= 100; ++i) {
			BoardVO v = new BoardVO();
			v.setTitle(String.valueOf(i) + "번째 제목");
			v.setContent(String.valueOf(i) + "번째 내용");
			v.setWriter("admin2");
			dao.insert(v);
		}
	}
}

