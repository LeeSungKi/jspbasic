package com.myhome.servlet.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myhome.beans.BoardDAO;
import com.myhome.beans.BoardVO; 
import com.myhome.beans.ReplyDao;

public class ReadAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt( request.getParameter("num") );
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = null;
		
		HttpSession session = request.getSession();
		String name = session.getId() + "_" + num;
		Cookie[] cArr = request.getCookies();
		boolean find = false;
		for(Cookie co : cArr) {
			if(co.getName().equals(name)) {
				find = true;
				break;
			}
		}
		if(!find) { // 쿠키가 없으면
			Cookie c = new Cookie(name, "");
			c.setMaxAge(60 * 60 * 24 * 3); // 3일동안 쿠키 유지
			response.addCookie(c);
			dao.incrementHit(num);
		}
		vo = dao.read(num);
		request.setAttribute("vo", vo);
		request.setAttribute("replyList", ReplyDao.getInstance().getList(num));
		
		return;
	}
}




