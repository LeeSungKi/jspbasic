package com.myhome.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.beans.AccountDao;
import com.myhome.beans.AccountVo;

@WebServlet("/join/join") // ~~:9090/myhome/join/join
public class Join extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password"); 
		String email1 = request.getParameter("user_email1");
		String email2 = request.getParameter("user_email2");
		String addr1 = request.getParameter("user_addr1"); 
		String addr2 = request.getParameter("user_addr2");
		String addr3 = request.getParameter("user_addr3");
		
		if(email2.equals("None")){
			try {
				email2 = email1.split("@")[1];
			} catch(ArrayIndexOutOfBoundsException e){
				email2 = null;
			}
		}
		
		AccountVo vo = new AccountVo();
		vo.setId(id);
		vo.setPassword(password);
		vo.setEmail1(email1);
		vo.setEmail2(email2);
		vo.setZip(addr1);
		vo.setAddress1(addr2);
		vo.setAddress2(addr3); 
		
		boolean result = AccountDao.getInstance().join(vo); 
		String message = result ? "회원가입에 감사드립니다, " + id + "님!" :
									"회원가입에 실패하였습니다.";
		request.setAttribute("result", message);
		request.getRequestDispatcher("joinResult.jsp").forward(request, response);
	}

}
