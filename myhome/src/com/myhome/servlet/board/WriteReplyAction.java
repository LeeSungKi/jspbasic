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
//		reNum NUMBER PRIMARY KEY, -- 댓글의 고유번호 (X)
//		reContent CLOB NOT NULL, -- 댓글 내용
//		reWriter VARCHAR2(20) NOT NULL, -- 댓글 단 사람의 id
//		reBoardNum NUMBER NOT NULL, -- 이 댓글이 달린 게시판 글 번호
//		reParentReNum NUMBER NOT NULL, -- 이 댓글의 부모 댓글 번호(부모 댓글이 없으면 0)
//		reDepth NUMBER NOT NULL, -- 이 댓글의 깊이
//		reOrder NUMBER NOT NULL, -- 이 댓글을 리스트로 출력할 때 보일 순서 (X)
//		reDeleteFlag NUMBER NOT NULL, -- 삭제 여부(삭제 아니면 0, 삭제되었다면 1로 표시) (X)
//		reRegdate DATE -- 댓글 등록 날짜 (X)
		
		ReplyVo vo = new ReplyVo();
		vo.setReContent(request.getParameter("reContent"));
		vo.setReWriter((String)request.getSession().getAttribute("currentId"));
		vo.setReBoardNum(Integer.parseInt(request.getParameter("reBoardNum")));
		vo.setReParentReNum(Integer.parseInt(request.getParameter("reParentReNum")));
		vo.setReDepth(Integer.parseInt(request.getParameter("reDepth")));
		
		ReplyDao.getInstance().insert(vo);
	}
}
