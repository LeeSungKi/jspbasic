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

	// DBCP 연동

	private static DataSource ds;
	static { // static 초기화 블록 (생성자에서 해도 됨)
		try {
			// javax.naming.Context
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
			// lookup() : DataSource 객체(커넥션 객체)를 구한다.
			// Tomcat인 경우는 "java:comp/env/"를 붙여야 한다.
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	} // DBCP 연동 끝

	// 싱글톤 패턴(Singleton pattern)
	// - 필요할 때 최초로 객체를 1개만 생성해 두고
	// 이후에는 생성된 객체만 사용하는 것
	// 구현 순서)
	// 1. 자기 자신 타입의 참조변수를 static으로 1개 선언함 ( private )
	private static AccountDao instance;

	// 2. 생성자를 private으로 선언함 ( 다른 클래스에서는 이 생성자를 호출할 수 없도록 )
	private AccountDao() {
		
	}

	// 3. 다른 클래스에서 AccountDao 객체를 사용할 수 있게 getInstance() 정의
	public static AccountDao getInstance() {
		if (instance == null) {
			instance = new AccountDao();
		}
		return instance;
	}
	////////////////////////// 싱글톤 끝 /////////////////////////////

	// Login 하는 메서드
	@Nullable // 선택사항; null 일 수 있으니 참고하라!
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

	// 회원탈퇴하는 메서드
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

	// 회원가입하는 메서드
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
	 * 회원의 모든 정보를 조회하는 메서드
	 * 
	 * @param id
	 *            회원 아이디 (String)
	 * @return 회원 정보 (AccountVo), 해당 아이디가 DB에 없을 경우 null
	 * @author 이세라
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

	// 아이디가 존재하는 지 확인하는 메서드
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
