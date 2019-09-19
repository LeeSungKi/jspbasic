package com.myhome.servlet.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myhome.beans.BoardDAO;
import com.myhome.beans.BoardVO; 

public class WriteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// <form>에서 전달받은 parameter 받기 
		String title = request.getParameter("brd_title"); 
		String content = request.getParameter("brd_content");
		
		// 세션에서 작성자 id 꺼내기
		HttpSession session = request.getSession();
		String writer = (String)session.getAttribute("currentId"); 
		
		// BoardVo로 위 데이터를 포장 
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		
		// Dao의 insert()를 통해 DB에 저장 
		boolean result = false;
		
		result = writer != null && 
				!title.trim().isEmpty() && 
				!content.trim().isEmpty() && 
				BoardDAO.getInstance().insert(vo);
		// 1. writer == null : 아이디가 없는 경우  
		// (그 사이 세션이 만료되어 로그아웃 되었거나 로그인 없이 주소창에 url을 치고 들어왔을 경우)
		// 2. title.trim().isEmpty() : title이 공백인 경우
		// 3. content.trim().isEmpty() : content가 공백인 경우 
		// 4. BoardDao.getInstance().insert(vo) : insert(DB저장)의 실행결과
		
		// DB 저장에 성공하면 : true 
		//         실패하면(공백만 있을 경우) : false 
		// 를 attribute 형태로 저장
		request.setAttribute("result", result);
		return;
	}
}




