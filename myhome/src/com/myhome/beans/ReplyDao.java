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
	
	// �⺻ ������
	private ReplyDao() {
		try {
			Context ctx = new InitialContext();
			ds =(DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	// ��ü�� �����ϴ� �޼���
	public static ReplyDao getInstance() {
		if(instance == null) {
			instance = new ReplyDao();
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
			
			// STEP 1. ���� �θ�(���)�� ���� ���� ����� �ִ� order���� ���Ѵ�.
			////////////////////////
			sql = "SELECT MAX(reOrder) FROM (SELECT reOrder FROM reply WHERE reparentrenum = ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, vo.getReParentReNum());
			rs = ps.executeQuery(); // DB ����
			
			int maxOrder = 0;
			if(rs.next()) {
				maxOrder = rs.getInt(1); 
			}
			vo.setReOrder(maxOrder+1); // �ִ� order�� + 1 �� vo�� ��´�.
			rs.close();
			ps.close();
			
			// STEP 2. vo�� ���������� �߰� �Ѵ�.
			sql = "INSERT INTO reply VALUES(" + 
				"re_seq.NEXTVAL," + 
				"?," + // ��� ����
				"?," + // ��۾��� id
				"?," + // �� ����� �޸� �Խ��� �� ��ȣ
				"?," + // �� ����� �θ� ��� ��ȣ(�θ� ����� ������ 0) 
				"?," + // �� ����� ���� 
				"?," + // �� ����� ����Ʈ�� ����� �� ���� ���� 
				"0," + // ���� ����(���� �ƴϸ� 0, �����Ǿ��ٸ� 1�� ǥ��)
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





