package com.myhome.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myhome.beans.AccountDao;
import com.myhome.beans.AccountVo;

@WebServlet("/mypage/mypage")
public class Mypage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("currentId");
		if(id == null) {
			request.setAttribute("result", false);
		}
		else {
			AccountVo vo = AccountDao.getInstance().select(id);
			if(vo == null) {
				request.setAttribute("result", false);
			}
			else {
				request.setAttribute("vo", vo);
				request.setAttribute("result", true);
			}
		}
		request.getRequestDispatcher("mypageView.jsp").forward(request, response);
	}
}
