package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test01 {
	private final String USER = "web1400"; // DBA 계정명
	private final String PASSWORD = "jsppassword"; // DBA 비번
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	// 커넥트할 종류, 서비스, 위치, 포트 번호 등
	
	public Test01() {
		
		Connection con = null; // DB와 연결된 통로
		PreparedStatement ps = null; // 쿼리문이 들어있는 객체
		
		try { 
			// 클래스 로드 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Class.forName(String 클래스명) : 해당 클래스를 로드해라
			
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			// DB과 커넥팅한 후 그 커넥션(통로) 객체를 반환 + con 변수에 주소 저장
			
			
			ps = con.prepareStatement("INSERT INTO pokemon VALUES(10, '뮤', 100, 100, SYSDATE)");
			// 명령문 준비 
			
			ps.execute();
			ps.close();
			// 쿼리 실행
			ps = con.prepareStatement("INSERT INTO pokemon VALUES(11, '냄새꼬', 100, 100, SYSDATE)");
			ps.execute();
			
			System.out.println("새 포켓몬 등록 완료!");
			
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) { ps.close(); }
				if(con != null) { con.close(); }
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new Test01();
	}
}
