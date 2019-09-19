package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test2 {
	public static void main(String[] args) {
		 final String USER = "web1400"; // DBA 계정명
		 final String PASSWORD = "jsppassword"; // DBA 비번
		final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
		
		String sql = "SELECT * FROM pokemon";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();// 실행 후 '결과집합'을 받고 이를 rs에저장
			
			while(rs.next()) {
				System.out.println("이름:"+rs.getString("name"));
				System.out.println("번호:" + rs.getInt("num"));
				System.out.println("레벨:"+rs.getInt("lv"));
				System.out.println("체력:"+ rs.getInt("hp"));
				System.out.println("등록일자:"+rs.getDate("regdate"));
				System.out.println("등록일자:"+ rs.getString("regdate"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {rs.close();}
				if(ps != null) {ps.close();}
				if(con != null) {con.close();}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	}
}
