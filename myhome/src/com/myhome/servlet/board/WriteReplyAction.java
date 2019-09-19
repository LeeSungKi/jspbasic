package com.myhome.servlet.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myhome.beans.ReplyDao;
import com.myhome.beans.ReplyVo;

public class WriteReplyAction implements Action	{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		reNum NUMBER PRIMARY KEY, -- ����� ������ȣ (X)
//		reContent CLOB NOT NULL, -- ��� ����
//		reWriter VARCHAR2(20) NOT NULL, -- ��� �� ����� id
//		reBoardNum NUMBER NOT NULL, -- �� ����� �޸� �Խ��� �� ��ȣ
//		reParentReNum NUMBER NOT NULL, -- �� ����� �θ� ��� ��ȣ(�θ� ����� ������ 0)
//		reDepth NUMBER NOT NULL, -- �� ����� ����
//		reOrder NUMBER NOT NULL, -- �� ����� ����Ʈ�� ����� �� ���� ���� (X)
//		reDeleteFlag NUMBER NOT NULL, -- ���� ����(���� �ƴϸ� 0, �����Ǿ��ٸ� 1�� ǥ��) (X)
//		reRegdate DATE -- ��� ��� ��¥ (X)
		
		ReplyVo vo = new ReplyVo();
		vo.setReContent(request.getParameter("reContent"));
		vo.setReWriter((String)request.getSession().getAttribute("currentId"));
		vo.setReBoardNum(Integer.parseInt(request.getParameter("reBoardNum")));
		vo.setReParentReNum(Integer.parseInt(request.getParameter("reParentReNum")));
		vo.setReDepth(Integer.parseInt(request.getParameter("reDepth")));
		
		ReplyDao.getInstance().insert(vo);
	}
}
