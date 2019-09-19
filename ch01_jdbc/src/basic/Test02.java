package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test02 {
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
			
//			모든 레코드의 모든 정보 받아오기
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); // 실행 후 '결과집합'을 받고, 이를 rs에 저장
			
			while(rs.next()) {
				System.out.println("이름 : " + rs.getString("name"));
				System.out.println("번호 : " + rs.getInt("num"));
				System.out.println("레벨 : " + rs.getInt("lv"));
				System.out.println("체력 : " + rs.getInt("hp"));
//				System.out.println("등록일자 : " + rs.getDate("regdate"));
//				System.out.println("등록일자 : " + rs.getString("regdate"));
				java.sql.Date date = rs.getDate("regdate"); 
			}
			
//			< 전체 행 개수 얻어오기 >
//			ps = con.prepareStatement("SELECT count(*) FROM pokemon");
//			rs = ps.executeQuery(); // 실행 후 '결과집합'을 받고, 이를 rs에 저장
//			
//			System.out.println(rs.next());
//			//int total = rs.getInt("COUNT(*)");
//			int total = rs.getInt(1);
//			System.out.println("총 " + total + "마리");
//			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) { rs.close(); }
				if(ps != null) { ps.close(); }
				if(con != null) { con.close(); }
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
