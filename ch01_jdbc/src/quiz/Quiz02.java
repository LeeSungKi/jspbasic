package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

// ���ϸ� ��ȣ�� �Է� �ް� �ش� ���ϸ� ����
// (��� ��� X) 
public class Quiz02 {
	public static void main(String[] args) {
		String s = "DELETE FROM pokemon WHERE num = ?"; 
		String USER = "sungki"; // DBA ������
		String PASSWORD = "1234"; // DBA ���
		String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
		Scanner sc = new Scanner(System.in); 
		
		System.out.print("������ ���ϸ� ��ȣ : ");
		int n = sc.nextInt();
		
		Connection con = null; // DB�� ����� ���
		PreparedStatement ps = null; // �������� ����ִ� ��ü
		
		try { 
			// Ŭ���� �ε� 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Class.forName(String Ŭ������) : �ش� Ŭ������ �ε��ض�
			
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			// DB�� Ŀ������ �� �� Ŀ�ؼ�(���) ��ü�� ��ȯ + con ������ �ּ� ����
			
			
			ps = con.prepareStatement(s);
			
			ps.setInt(1, n);
			int deletedRows = ps.executeUpdate();
			if(deletedRows > 0) {
				System.out.println(deletedRows + "������ ���ϸ� ����!");
			}
			else {
				System.out.println(n + "�� ���ϸ��� �������� �ʽ��ϴ�.");
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
