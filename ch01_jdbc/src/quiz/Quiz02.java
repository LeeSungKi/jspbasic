package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// 포켓몬 번호를 입력 받고 해당 포켓몬 삭제
// (결과 출력 X) 
public class Quiz02 {
	public static void main(String[] args) {
		String s = "DELETE FROM pokemon WHERE num = ?"; 
		String USER = "sungki"; // DBA 계정명
		String PASSWORD = "1234"; // DBA 비번
		String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
		Scanner sc = new Scanner(System.in); 
		
		System.out.print("삭제할 포켓몬 번호 : ");
		int n = sc.nextInt();
		
		Connection con = null; // DB와 연결된 통로
		PreparedStatement ps = null; // 쿼리문이 들어있는 객체
		
		try { 
			// 클래스 로드 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Class.forName(String 클래스명) : 해당 클래스를 로드해라
			
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			// DB과 커넥팅한 후 그 커넥션(통로) 객체를 반환 + con 변수에 주소 저장
			
			
			ps = con.prepareStatement(s);
			
			ps.setInt(1, n);
			int deletedRows = ps.executeUpdate();
			if(deletedRows > 0) {
				System.out.println(deletedRows + "마리의 포켓몬 삭제!");
			}
			else {
				System.out.println(n + "번 포켓몬은 존재하지 않습니다.");
			}
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
	
	
}
