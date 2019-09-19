package com.myhome.servlet.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.beans.AccountDao;
import com.myhome.beans.AccountVo;

@WebServlet("/signout/signoutLogic.jsp")
public class Signout extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountVo vo = new AccountVo();
		vo.setId(request.getParameter("user_id"));
		vo.setPassword(request.getParameter("user_password"));
		
		AccountDao dao = AccountDao.getInstance();
		boolean result = dao.delete(vo);
		
		
		String message = "���̵� Ȥ�� ��й�ȣ�� �ٽ� Ȯ�����ּ���."; 
		if(result){
			message = "ȸ�� Ż�� �Ϸ��Ͽ����ϴ�.";
		}
		
		request.setAttribute("result", message);
		//pageContext.forward("signoutResult.jsp");
		request.getRequestDispatcher("signoutResult.jsp")
				.forward(request, response);
	}

}





