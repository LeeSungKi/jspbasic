package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test01 {
	private final String USER = "web1400"; // DBA ������
	private final String PASSWORD = "jsppassword"; // DBA ���
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; 
	// Ŀ��Ʈ�� ����, ����, ��ġ, ��Ʈ ��ȣ ��
	
	public Test01() {
		
		Connection con = null; // DB�� ����� ���
		PreparedStatement ps = null; // �������� ����ִ� ��ü
		
		try { 
			// Ŭ���� �ε� 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Class.forName(String Ŭ������) : �ش� Ŭ������ �ε��ض�
			
			
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			// DB�� Ŀ������ �� �� Ŀ�ؼ�(���) ��ü�� ��ȯ + con ������ �ּ� ����
			
			
			ps = con.prepareStatement("INSERT INTO pokemon VALUES(10, '��', 100, 100, SYSDATE)");
			// ��ɹ� �غ� 
			
			ps.execute();
			ps.close();
			// ���� ����
			ps = con.prepareStatement("INSERT INTO pokemon VALUES(11, '������', 100, 100, SYSDATE)");
			ps.execute();
			
			System.out.println("�� ���ϸ� ��� �Ϸ�!");
			
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
