package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MyConnection {
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	private static final String DB_USER = "sungki";
	private static final String DB_PASSWORD = "1234";
	
	private static Connection con; 
	
	public static Connection getMyConnection() {
		try {
			if(con == null) {
				Class.forName("oracle.jdbc.driver.OracleDriver"); 
				con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeAllConnection() {
		closeAllConnection(con, null, null);
	}
	public static void closeAllConnection(Connection con, PreparedStatement ps) {
		closeAllConnection(con, ps, null);
	}
	public static void closeAllConnection(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void closeAllConnection(PreparedStatement ps, ResultSet rs) {
		closeAllConnection(null, ps, rs);
	}
}
