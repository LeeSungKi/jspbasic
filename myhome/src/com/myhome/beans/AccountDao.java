package com.myhome.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.jni.OS;

import com.sun.istack.internal.Nullable;

public class AccountDao {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;

	// DBCP ����

	private static DataSource ds;
	static { // static �ʱ�ȭ ��� (�����ڿ��� �ص� ��)
		try {
			// javax.naming.Context
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
			// lookup() : DataSource ��ü(Ŀ�ؼ� ��ü)�� ���Ѵ�.
			// Tomcat�� ���� "java:comp/env/"�� �ٿ��� �Ѵ�.
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	} // DBCP ���� ��

	// �̱��� ����(Singleton pattern)
	// - �ʿ��� �� ���ʷ� ��ü�� 1���� ������ �ΰ�
	// ���Ŀ��� ������ ��ü�� ����ϴ� ��
	// ���� ����)
	// 1. �ڱ� �ڽ� Ÿ���� ���������� static���� 1�� ������ ( private )
	private static AccountDao instance;

	// 2. �����ڸ� private���� ������ ( �ٸ� Ŭ���������� �� �����ڸ� ȣ���� �� ������ )
	private AccountDao() {
		
	}

	// 3. �ٸ� Ŭ�������� AccountDao ��ü�� ����� �� �ְ� getInstance() ����
	public static AccountDao getInstance() {
		if (instance == null) {
			instance = new AccountDao();
		}
		return instance;
	}
	////////////////////////// �̱��� �� /////////////////////////////

	// Login �ϴ� �޼���
	@Nullable // ���û���; null �� �� ������ �����϶�!
	public String selectId(AccountVo vo) {
		sql = "SELECT id FROM account WHERE id = ? AND password = ?";
		String id = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPassword());
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return id;
	}

	// ȸ��Ż���ϴ� �޼���
	public boolean delete(AccountVo vo) {
		boolean result = false;
		sql = "DELETE FROM account WHERE id = ? AND password = ?";
		try {
			con = getConnection();
			if (vo.getNum() != 0) {
				ps = con.prepareStatement("DELETE FROM account WHERE num = ?");
				ps.setInt(1, vo.getNum());
			} else {
				ps = con.prepareStatement(sql);
				ps.setString(1, vo.getId());
				ps.setString(2, vo.getPassword());
			}
			result = ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	// ȸ�������ϴ� �޼���
	public boolean join(AccountVo vo) {
		sql = "INSERT INTO account VALUES (acc_seq.NEXTVAL, ?, ?, ?, ?, ?,?,?,SYSDATE)";
		boolean result = false;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPassword());
			ps.setString(3, vo.getEmail1());
			ps.setString(4, vo.getEmail2());
			ps.setString(5, vo.getZip());
			ps.setString(6, vo.getAddress1());
			ps.setString(7, vo.getAddress2());
			result = ps.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	/**
	 * ȸ���� ��� ������ ��ȸ�ϴ� �޼���
	 * 
	 * @param id
	 *            ȸ�� ���̵� (String)
	 * @return ȸ�� ���� (AccountVo), �ش� ���̵� DB�� ���� ��� null
	 * @author �̼���
	 */
	public AccountVo select(String id) {
		sql = "SELECT * FROM account WHERE id = ?";
		AccountVo vo = null;
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				vo = new AccountVo();
				vo.setNum(rs.getInt("num"));
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setEmail1(rs.getString("email1"));
				vo.setEmail2(rs.getString("email2"));
				vo.setZip(rs.getString("zip"));
				vo.setAddress1(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
				vo.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	public ArrayList<AccountVo> selectAll() {
		sql = "SELECT num, id, email1, email2 FROM account";
		ArrayList<AccountVo> list = new ArrayList<>();
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				AccountVo vo = new AccountVo();
				vo.setNum(rs.getInt("num"));
				vo.setId(rs.getString("id"));
				vo.setEmail1(rs.getString("email1"));
				vo.setEmail2(rs.getString("email2"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println("list From DAO : " + list);
		return list.isEmpty() ? null : list;
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

	// ���̵� �����ϴ� �� Ȯ���ϴ� �޼���
	public boolean isExist(String id) {
		boolean result = false;
		sql = "SELECT id FROM account WHERE id = ?";
		try {
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			result = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
