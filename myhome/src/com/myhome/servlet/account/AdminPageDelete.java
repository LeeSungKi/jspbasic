package com.myhome.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.beans.AccountDao;
import com.myhome.beans.AccountVo;

@WebServlet("/mypage/adminpageDelete")
public class AdminPageDelete extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		AccountVo vo = new AccountVo();
		vo.setNum(num);
		
		AccountDao.getInstance().delete(vo);
		request.getRequestDispatcher("adminpage").forward(request, response);
	}

}
