package com.myhome.servlet.account;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.beans.AccountDao;
import com.myhome.beans.AccountVo;
@WebServlet("/mypage/adminpage")
public class AdminPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<AccountVo> list = AccountDao.getInstance().selectAll();
		
		request.setAttribute("account_list", list);
		request.getRequestDispatcher("adminpageView.jsp")
				.forward(request, response);
	}
}










