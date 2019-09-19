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
		
		// 1. 파라미터 받기
		String id = request.getParameter("user_id");
		String password = request.getParameter("user_password");
		
		// 2. VO 세팅 
		AccountVo vo = new AccountVo();
		vo.setId(id);
		vo.setPassword(password);
		
		// 3. DAO를 사용하여 db 조회 
		AccountDao dao = AccountDao.getInstance();
		String userId = dao.selectId(vo);
		String message = userId == null ? "아이디 혹은 비밀번호를 확인해주세요." :
										  "환영합니다. " + userId + "님!";
		if(userId != null) {
			// session.setAttribute("currentId", userId);
			HttpSession session = request.getSession();
			session.setAttribute("currentId", userId);
			// session 은 jsp에는 내장객체로 존재하지만
			// 서블릿에서는 request 객체를 통해 얻어와야함
			
			// (아이디 기억하기)
			// rememberId 파라미터 받기 
			String rId = request.getParameter("rememberId"); 
			
			
			// 쿠키 객체 생성 (쿠키 저장 혹은 삭제를 위함)
			Cookie c = new Cookie("auto_login", id);
			c.setPath("/");
			
			// 파라미터가 넘어왔는지 확인
			if(rId == null) { // 체크 안되었을 경우
				c.setMaxAge(0); // 쿠키를 삭제하기 위해 수명을 0초로
			}
			else { // 체크 되었을 경우 
				c.setMaxAge(60 * 60 * 24 * 3); // 쿠키의 수명을 3일로 
			}
			response.addCookie(c);
		}
		request.setAttribute("result", message);
		// pageContext.forward("loginResult.jsp");
		// ==> 서블릿은 pageContext 내장 객체 없음 
		RequestDispatcher rd = request.getRequestDispatcher("loginResult.jsp");
		rd.forward(request, response);
	}
}





