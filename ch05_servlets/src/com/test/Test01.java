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
 * 	< ���� ����� >
 * 1. HttpServlet Ŭ������ ��� ���� ( Ȥ�� HttpServlet ����Ŭ���� )
 * 2. url-mapping 
 *  ( �ش� ���� Ŭ������ ���� URL �ο��ϱ� ) 
 *   ���1) @WebServlet �ֳ����̼� ��� 
 *   ���2) web.xml �� ������ ���
 *        ( ppt ���� )
 * 
 * 3. ���񽺸� �����ϴ� �޼��� 2���� 
 * 	doGet() : get ������� ��û�Ǿ��� �� �����
 *  doPost() : post ������� ��û�Ǿ��� �� �����
 *  
 * 4. Servlet�� �ֿ� �޼��� (�ʿ��ϸ� �������̵� �ϴ� �޼���)
 *  1) init() : ���� ��ü�� ������ ���� ȣ��� 
 *  			(��ü �ʱ�ȭ�ϴ� �۾��� �ۼ���)
 *  2) destroy() : ���� ��ü�� �����Ǳ� ���� ȣ���
 *  			(��ü �ڿ� ���� ���� �۾��� �ۼ���)
 *  3) doGet()
 *     doPost()
 *     doDelete()
 * 	   doTrace()
 *     doHead()
 *     doOptions()
 *     doPut()
 *     ==> ��û��Ŀ� ���� ����Ǵ� �޼��� 
 *     		��) Head ������� ��û�Ǿ��ٸ� doHead() ����
 * 5. ���� �����ֱ� 
 *  1) �ش� ������ ���� ��û 
 *     --> new ��ü ���� + init() 
 *     --> service (doGet() Ȥ�� doPost())����
 *  2) ���� ���� ��û�� �� ���� 
 *    --> ������ �����ص� ��ü�� �״�� ��� 
 *    --> service (doGet() Ȥ�� doPost())����
 *    (����: ���ÿ� ���� Ŭ���̾�Ʈ�� ���� URL �� ��û���� ���? ��Ƽ������� ���� ����)
 *  3) ������ ���� ���� �� ������
 *    
 */
@WebServlet("/MyFirst") // http://localhost:9090/ch05_serlvets/MyFirst
public class Test01 extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		System.out.println("Test01�� init()"); // console â�� ���
	}
	
	@Override
	public void destroy() {
		System.out.println("Test01�� destroy()");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("Test01�� doGet()");
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








