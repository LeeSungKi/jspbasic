package com.myhome.servlet.res;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/res/upload")
public class Upload extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String realPath = req.getServletContext().getRealPath("/storage");
		MultipartRequest mr = new MultipartRequest(req, 		 	  //���� �Ķ���Ͱ� ����ִ�  request��ü
														realPath,	  //������ ������ ���� ���
													5*1024*1024*1024, //�ִ� ��� �뷮(Byte) 5GB
														"UTF-8", 	  //���ڵ� ���� (������ ������ ��Ÿ Ÿ���� �Ķ���͵� ���� �Ѿ���� ���)
														new DefaultFileRenamePolicy());//�ߺ� �̸��� ������ ���ε� �Ǿ��� ��� �̸� �ٲٱ�
																					   //DefaultFileRenamePolicy : ���ϸ� + ���� �������� ���ڴ�! 
	//String fileName1 = mr.getParameter("file1");
	//String fileName2 = mr.getParameter("file2");
	
	File file1 = null;
	File file2 = null;
	
	String orginName1 = mr.getOriginalFileName("file1");
	String orginName2 = mr.getOriginalFileName("file2");
	
	String changedName1 = mr.getFilesystemName("file1");
	String changedName2 = mr.getFilesystemName("file2");
	
	String contentType = mr.getContentType("file1");
	
	int fileSize1 = 0;
	int fileSize2 = 0;
	
	if(orginName1 != null) {
		file1 = mr.getFile("file1");
		fileSize1 =(int) file1.length();
	}
	
	if(orginName2 != null) {
		file2 =mr.getFile("file2");
		fileSize2 =(int) file2.length();
	}
	
	req.setAttribute("orginName1", orginName1);
	req.setAttribute("orginName2", orginName2);

	req.setAttribute("changedName1", changedName1);
	req.setAttribute("changedName2", changedName2);
	
	req.setAttribute("contentType", contentType);
	
	req.setAttribute("fileSize1", fileSize1);
	req.setAttribute("fileSize2", fileSize2);
	
	req.getRequestDispatcher("uploadResult.jsp").forward(req, resp);
	}
	

}
