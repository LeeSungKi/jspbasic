package com.myhome.servlet.res;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/res/download")
public class Download extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fileName = req.getParameter("file");
		String realPath = req.getServletContext().getRealPath("/storage");
		File targetFile = new File(realPath + "/" + fileName);
		
		byte[] arr = new byte[(int)targetFile.length()];
		
		BufferedInputStream fIn = null;
		BufferedOutputStream fOut = null;
		
		try {
			//response 메세지의 헤더 설정
			fileName = "attachment;fileName=" + new String(URLEncoder.encode(fileName, "UTF-8")).replaceAll("\\+", " ");
			resp.setHeader("Content-Disposition", fileName);
			resp.setHeader("Content-Length", targetFile.length()+"");
			
			fIn = new BufferedInputStream(new FileInputStream(targetFile));
			fOut = new BufferedOutputStream(resp.getOutputStream());
			
			fIn.read(arr);
			fOut.write(arr);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fIn !=null) {fIn.close();}
			if(fOut != null) {fOut.close();}
		}
 		
		
	}

}
