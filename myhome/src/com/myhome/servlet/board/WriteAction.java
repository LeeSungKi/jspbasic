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
		// <form>���� ���޹��� parameter �ޱ� 
		String title = request.getParameter("brd_title"); 
		String content = request.getParameter("brd_content");
		
		// ���ǿ��� �ۼ��� id ������
		HttpSession session = request.getSession();
		String writer = (String)session.getAttribute("currentId"); 
		
		// BoardVo�� �� �����͸� ���� 
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		
		// Dao�� insert()�� ���� DB�� ���� 
		boolean result = false;
		
		result = writer != null && 
				!title.trim().isEmpty() && 
				!content.trim().isEmpty() && 
				BoardDAO.getInstance().insert(vo);
		// 1. writer == null : ���̵� ���� ���  
		// (�� ���� ������ ����Ǿ� �α׾ƿ� �Ǿ��ų� �α��� ���� �ּ�â�� url�� ġ�� ������ ���)
		// 2. title.trim().isEmpty() : title�� ������ ���
		// 3. content.trim().isEmpty() : content�� ������ ��� 
		// 4. BoardDao.getInstance().insert(vo) : insert(DB����)�� ������
		
		// DB ���忡 �����ϸ� : true 
		//         �����ϸ�(���鸸 ���� ���) : false 
		// �� attribute ���·� ����
		request.setAttribute("result", result);
		return;
	}
}




