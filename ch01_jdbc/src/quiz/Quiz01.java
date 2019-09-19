package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Quiz01 {
	// Scanner�� �̸�, ������ �Է� ����
	// ��ȣ�� pok_seq.NEXTVAL��, ü���� ������ 1000��� DB�� �����ϱ�
	// regdate�� SYSDATE
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		String name;
		int lv, hp;
		
		System.out.print("�̸� : ");
		name = sc.next();
		
		System.out.print("���� : ");
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
			ps.setString(1, name);// 1��° ����ǥ �ڸ��� name ���� String������ �־��
			ps.setString(2, String.valueOf(lv)); // 2��° ����ǥ �ڸ��� lv ���� int������ �־��
			ps.setInt(3, hp); // 3��° ����ǥ �ڸ��� hp ���� int������ �־��
			
			ps.execute();
			System.out.println("���ϸ� ���� �Ϸ�!");
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



