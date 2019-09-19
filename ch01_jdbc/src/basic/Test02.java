package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test02 {
	public static void main(String[] args) {
		final String USER = "web1400"; // DBA ������
		final String PASSWORD = "jsppassword"; // DBA ���
		final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String sql = "SELECT * FROM pokemon"; 
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			
//			��� ���ڵ��� ��� ���� �޾ƿ���
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(); // ���� �� '�������'�� �ް�, �̸� rs�� ����
			
			while(rs.next()) {
				System.out.println("�̸� : " + rs.getString("name"));
				System.out.println("��ȣ : " + rs.getInt("num"));
				System.out.println("���� : " + rs.getInt("lv"));
				System.out.println("ü�� : " + rs.getInt("hp"));
//				System.out.println("������� : " + rs.getDate("regdate"));
//				System.out.println("������� : " + rs.getString("regdate"));
				java.sql.Date date = rs.getDate("regdate"); 
			}
			
//			< ��ü �� ���� ������ >
//			ps = con.prepareStatement("SELECT count(*) FROM pokemon");
//			rs = ps.executeQuery(); // ���� �� '�������'�� �ް�, �̸� rs�� ����
//			
//			System.out.println(rs.next());
//			//int total = rs.getInt("COUNT(*)");
//			int total = rs.getInt(1);
//			System.out.println("�� " + total + "����");
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
