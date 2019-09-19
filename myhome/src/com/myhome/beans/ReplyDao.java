package com.myhome.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReplyDao {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private DataSource ds;
	private static ReplyDao instance;
	
	// 기본 생성자
	private ReplyDao() {
		try {
			Context ctx = new InitialContext();
			ds =(DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 객체를 리턴하는 메서드
	public static ReplyDao getInstance() {
		if(instance == null) {
			instance = new ReplyDao();
		}
		return instance;
	}
	
	// Connect 하는 메서드
	private Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	// Close 하는 메서드
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ReplyVo> getList(int boardNum){
		ArrayList<ReplyVo> list = new ArrayList<>();
		String sql = "SELECT * " + 
				" FROM (SELECT * FROM reply WHERE reboardnum=?)" + 
				" START WITH reparentrenum = 0" + 
				"CONNECT BY PRIOR  renum = reparentrenum";
		try {
			con = getConnection(); 
			ps = con.prepareStatement(sql); 
			ps.setInt(1, boardNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReplyVo vo = new ReplyVo();
				vo.setReNum(rs.getInt("renum"));
				vo.setReContent(rs.getString("recontent"));
				vo.setReWriter(rs.getString("rewriter"));
				vo.setReBoardNum(rs.getInt("reboardnum"));
				vo.setReParentReNum(rs.getInt("reparentrenum"));
				vo.setReDepth(rs.getInt("redepth"));
				vo.setReOrder(rs.getInt("reorder"));
				vo.setReDeleteFlag(0);
				vo.setReRegdate(rs.getString("reregdate"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list.isEmpty() ? null : list;
	}
	
	public boolean insert(ReplyVo vo) {
		boolean result = false;
		String sql;
		try {
			con = getConnection();
			
			// STEP 1. 같은 부모(댓글)을 가진 형제 댓글의 최대 order값을 구한다.
			////////////////////////
			sql = "SELECT MAX(reOrder) FROM (SELECT reOrder FROM reply WHERE reparentrenum = ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getReParentReNum());
			rs = ps.executeQuery(); // DB 실행
			
			int maxOrder = 0;
			if(rs.next()) {
				maxOrder = rs.getInt(1); 
			}
			vo.setReOrder(maxOrder+1); // 최대 order값 + 1 을 vo에 담는다.
			rs.close();
			ps.close();
			
			// STEP 2. vo를 본격적으로 추가 한다.
			sql = "INSERT INTO reply VALUES(" + 
				"re_seq.NEXTVAL," + 
				"?," + // 댓글 내용
				"?," + // 댓글쓴이 id
				"?," + // 이 댓글이 달린 게시판 글 번호
				"?," + // 이 댓글의 부모 댓글 번호(부모 댓글이 없으면 0) 
				"?," + // 이 댓글의 깊이 
				"?," + // 이 댓글을 리스트로 출력할 때 보일 순서 
				"0," + // 삭제 여부(삭제 아니면 0, 삭제되었다면 1로 표시)
				"SYSDATE)";
			con = getConnection(); 
			ps = con.prepareStatement(sql); 
			ps.setString(1, vo.getReContent());
			ps.setString(2, vo.getReWriter());
			ps.setInt(3, vo.getReBoardNum());
			ps.setInt(4, vo.getReParentReNum());
			ps.setInt(5, vo.getReDepth());
			ps.setInt(6, vo.getReOrder());
			result = ps.executeUpdate() == 1;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}





