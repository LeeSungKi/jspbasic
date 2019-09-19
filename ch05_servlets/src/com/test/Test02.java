package com.test;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MySecond")
public class Test02 extends HttpServlet{

	@Override
	public void init() throws ServletException {
		System.out.println("Test02의 init()"); // console 창에 출력
	}
	
	@Override
	public void destroy() {
		System.out.println("Test02의 destroy()");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("Test02의 doGet()");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = resp.getWriter();
		pw.write("<!DOCTYPE html>");
		pw.write("<html>");
		pw.write("<head><meta charset='utf-8'></head>");
		pw.write("<body>");
		pw.write("<h1>이곳은 Test02</h1>");
		pw.write("</body>");
		pw.write("</html>");
		pw.flush();
		pw.close();
	}
}
