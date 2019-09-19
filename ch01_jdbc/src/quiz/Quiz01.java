package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Quiz01 {
	// Scanner로 이름, 레벨을 입력 받음
	// 번호는 pok_seq.NEXTVAL로, 체력은 레벨의 1000배로 DB에 저장하기
	// regdate은 SYSDATE
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		String name;
		int lv, hp;
		
		System.out.print("이름 : ");
		name = sc.next();
		
		System.out.print("레벨 : ");
		lv = sc.nextInt();
		
		hp = lv * 1000;
		
		
		///////////////////////////////////////
//		String sql = "INSERT INTO pokemon "
//					+ "VALUES ("
//					+ "pok_seq.NEXTVAL, "
//					+ "'"+name+"', "
//					+ lv + ", "
//					+ hp + ", SYSDATE)";
		String sql = "INSERT INTO pokemon VALUES("
					+ "pok_seq.NEXTVAL, ?, ?, ?, SYSDATE)"; 
		String user = "sungki";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			con = DriverManager.getConnection(url, user, password); 
			ps = con.prepareStatement(sql); 
			ps.setString(1, name);// 1번째 물음표 자리에 name 값을 String형으로 넣어라
			ps.setString(2, String.valueOf(lv)); // 2번째 물음표 자리에 lv 값을 int형으로 넣어라
			ps.setInt(3, hp); // 3번째 물음표 자리에 hp 값을 int형으로 넣어라
			
			ps.execute();
			System.out.println("포켓몬 저장 완료!");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) { ps.close();}
				if(con != null) { con.close();}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
				
	}
}



