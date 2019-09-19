package com.myhome.servlet.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myhome.beans.AccountDao;
import com.myhome.beans.AccountVo;

@WebServlet("/login/loginLogic.jsp") 
// ..../myhome/login/loginLogic.jsp
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. �Ķ���� �ޱ�
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");
		
		// 2. VO ���� 
		AccountVo vo = new AccountVo();
		vo.setId(id);
		vo.setPassword(password);
		
		// 3. DAO�� ����Ͽ� db ��ȸ 
		AccountDao dao = AccountDao.getInstance();
		String userId = dao.selectId(vo);
		String message = userId == null ? "���̵� Ȥ�� ��й�ȣ�� Ȯ�����ּ���." :
										  "ȯ���մϴ�. " + userId + "��!";
		if(userId != null) {
			// session.setAttribute("currentId", userId);
			HttpSession session = request.getSession();
			session.setAttribute("currentId", userId);
			// session �� jsp���� ���尴ü�� ����������
			// ���������� request ��ü�� ���� ���;���
			
			// (���̵� ����ϱ�)
			// rememberId �Ķ���� �ޱ� 
			String rId = request.getParameter("rememberId"); 
			
			
			// ��Ű ��ü ���� (��Ű ���� Ȥ�� ������ ����)
			Cookie c = new Cookie("auto_login", id);
			c.setPath("/");
			
			// �Ķ���Ͱ� �Ѿ�Դ��� Ȯ��
			if(rId == null) { // üũ �ȵǾ��� ���
				c.setMaxAge(0); // ��Ű�� �����ϱ� ���� ������ 0�ʷ�
			}
			else { // üũ �Ǿ��� ��� 
				c.setMaxAge(60 * 60 * 24 * 3); // ��Ű�� ������ 3�Ϸ� 
			}
			response.addCookie(c);
		}
		request.setAttribute("result", message);
		// pageContext.forward("loginResult.jsp");
		// ==> ������ pageContext ���� ��ü ���� 
		RequestDispatcher rd = request.getRequestDispatcher("loginResult.jsp");
		rd.forward(request, response);
	}
}





