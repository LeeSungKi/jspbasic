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

public class BoardDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private DataSource ds;
	private static BoardDAO instance;
	
	// �⺻ ������
	private BoardDAO() {
		try { 
			Context ctx = new InitialContext();
			ds =(DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	// ��ü�� �����ϴ� �޼���
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	// Connect �ϴ� �޼���
	private Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	// Close �ϴ� �޼���
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
	
	// �Խ��� �� �߰� : insert()
	public boolean insert(BoardVO vo) {
		// vo�� ��� ������ db�� ����
		String sql = "INSERT INTO board VALUES(brd_seq.NEXTVAL, ?, ?, 0, ?, SYSDATE)";
		boolean result = false;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getWriter());
			result = ps.executeUpdate() == 1;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// �Խ��� �� ���� : update() 
	public boolean update(int num, String newTitle, String newContent){
		// num�� ���� ������ newTitle��, ������ newContent�� ����
		String sql = "UPDATE board SET title = ?, content = ? WHERE num = ?";
		boolean result = false;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, newTitle);
			ps.setString(2, newContent);
			ps.setInt(3, num); 
			result = ps.executeUpdate() == 1;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// �Խ��� �� ���� : delete()
	public boolean delete(int num) {
		// num�� �� ����
		String sql = "DELETE FROM board WHERE num = ?";
		boolean result = false;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			result = ps.executeUpdate() == 1;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// ��ȸ�� ����
	public void incrementHit(int num) {
		// num�� ���� ��ȸ���� 1����
		String sql = "UPDATE board SET hit = hit+1 WHERE num = ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			ps.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	// �Խñ� ��ȸ : read() 
	public BoardVO read(int num) {
		// num�� ���� ��� ������ vo�� �����Ͽ� �̸� return 
		String sql = "SELECT * FROM board WHERE num = ?";
		BoardVO vo = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setHit(rs.getInt("hit"));
				vo.setRegdate(rs.getString("regdate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}
	
	// �Խñ� ��� : getList()
	public ArrayList<BoardVO> getList(int page){
		String sql = "SELECT * FROM ("
				+ "SELECT rownum rn, tt.* FROM ("
				+ "SELECT * FROM board ORDER BY num DESC) "
				+ "tt) "
				+ "WHERE rn >= ? AND rn <= ?";
		int end = page * 5;
		int begin = end - (5-1); 
		ArrayList<BoardVO> list = new ArrayList<>();
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, begin); 
			ps.setInt(2, end);
			rs = ps.executeQuery();
			while(rs.next()){
				BoardVO vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				// vo.setContent(rs.getString("content")); (����Ʈ�� ������ �ʿ�����Ƿ�)
				vo.setWriter(rs.getString("writer"));
				vo.setHit(rs.getInt("hit"));
				vo.setRegdate(rs.getString("regdate"));
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list.isEmpty() ? null : list;
	}
	
	public int getTotalCount () {
		String sql = "SELECT COUNT(*) FROM board"; 
		int count = 0;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return count;
	}
}
 















