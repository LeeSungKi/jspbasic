package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 	< 서블릿 만들기 >
 * 1. HttpServlet 클래스를 상속 받음 ( 혹은 HttpServlet 하위클래스 )
 * 2. url-mapping 
 *  ( 해당 서블릿 클래스의 고유 URL 부여하기 ) 
 *   방법1) @WebServlet 애너테이션 사용 
 *   방법2) web.xml 에 서블릿을 등록
 *        ( ppt 참고 )
 * 
 * 3. 서비스를 실행하는 메서드 2가지 
 * 	doGet() : get 방식으로 요청되었을 때 실행됨
 *  doPost() : post 방식으로 요청되었을 때 실행됨
 *  
 * 4. Servlet의 주요 메서드 (필요하면 오버라이드 하는 메서드)
 *  1) init() : 서블릿 객체가 생성된 직후 호출됨 
 *  			(객체 초기화하는 작업을 작성함)
 *  2) destroy() : 서블릿 객체가 해제되기 직전 호출됨
 *  			(객체 자원 정리 등의 작업을 작성함)
 *  3) doGet()
 *     doPost()
 *     doDelete()
 * 	   doTrace()
 *     doHead()
 *     doOptions()
 *     doPut()
 *     ==> 요청방식에 따라 실행되는 메서드 
 *     		예) Head 방식으로 요청되었다면 doHead() 실행
 * 5. 서블릿 생명주기 
 *  1) 해당 서블릿의 최초 요청 
 *     --> new 객체 생성 + init() 
 *     --> service (doGet() 혹은 doPost())실행
 *  2) 이후 같은 요청이 또 들어옴 
 *    --> 위에서 생성해둔 객체를 그대로 사용 
 *    --> service (doGet() 혹은 doPost())실행
 *    (참고: 동시에 여러 클라이언트가 같은 URL 을 요청했을 경우? 멀티스레드로 서비스 실행)
 *  3) 해제는 서버 종료 시 해제됨
 *    
 */
@WebServlet("/MyFirst") // http://localhost:9090/ch05_serlvets/MyFirst
public class Test01 extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		System.out.println("Test01의 init()"); // console 창에 출력
	}
	
	@Override
	public void destroy() {
		System.out.println("Test01의 destroy()");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("Test01의 doGet()");
		PrintWriter pw = resp.getWriter();
		pw.write("<!DOCTYPE html>");
		pw.write("<html>");
		pw.write("<body>");
		pw.write("Hello, Servlet! <br>");
		
		String s = new SimpleDateFormat("YYYY/MM/dd HH:mm")
						.format(System.currentTimeMillis());
		pw.write("current Time : " + s);
		pw.write("</body>");
		pw.write("</html>");
		pw.flush();
		pw.close();
	}
}








