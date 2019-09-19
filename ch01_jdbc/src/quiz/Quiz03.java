package quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import util.MyConnection;
import util.Student;

/*
 * < �޴� >
 * �л� ���� ���α׷� ����� 
 * 1. �� �л� ���
 * 2. ��� �л� ��ȸ
 * 3. �л� �˻�
 * 		1) ��ȣ�� �˻�
 * 		2) �̸����� �˻�
 * 4. �г⺰ ��� ���� 
 * 		�г� �Է� --> �ش� �г� �л����� ���������� ��� 
 * 5. �г⺰ ��� ���� 
 * 		�г� �Է� --> �ش� �г� �л����� ��ü ��� ���� ���
 * (6. �л� ����)
 * 0. ���� 
 * 
 */
public class Quiz03 {
	
	private String menu = "1. �� �л� ��� \n"
						+ "2. ��� �л� ��ȸ \n"
						+ "3. �л� �˻� \n"
						+ "4. �г⺰ ��� ����\n"
						+ "5. �г⺰ ��� ����\n"
						+ "6. �л� ����\n"
						+ "0. ����";
	private Connection con;
	private PreparedStatement ps; 
	private ResultSet rs;
	// num name contact kr en ma regdate grade average
	private void menu1() throws Exception {
		String sql = "INSERT INTO student VALUES (?,st_seq.NEXTVAL,?,?,?,?,?,?,SYSDATE)";
		String message;
		Student s = new Student();
		s.setName(JOptionPane.showInputDialog("�� �л��� �̸�").trim());
		s.setContact(JOptionPane.showInputDialog("�� �л��� ����ó").trim());
		s.setGrade(Integer.parseInt(JOptionPane.showInputDialog("�� �л��� �г�").trim()));
		s.setKr(Integer.parseInt(JOptionPane.showInputDialog("�� �л��� ��������").trim()));
		s.setEn(Integer.parseInt(JOptionPane.showInputDialog("�� �л��� ��������").trim()));
		s.setMa(Integer.parseInt(JOptionPane.showInputDialog("�� �л��� ��������").trim()));
		
		ps = con.prepareStatement(sql); 
		ps.setString(1, s.getName());
		ps.setString(2, s.getContact());
		ps.setInt(3, s.getKr());
		ps.setInt(4, s.getEn());
		ps.setInt(5, s.getMa());
		ps.setDouble(6, s.getAvg());
		ps.setInt(7, s.getGrade());
		
		
		message = ps.executeUpdate() == 1 ? "�л� �߰� �Ϸ�!" : "�л� �߰� ����..";
		MyConnection.closeAllConnection(ps, null);
		
		JOptionPane.showMessageDialog(null, "�л� �߰� �Ϸ�!");
	}
	private void menu2() throws Exception {
		String sql = "SELECT * FROM student";
		String message = ""; 
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		message = select(rs);
		MyConnection.closeAllConnection(ps, rs);
		JOptionPane.showMessageDialog(null, message);
	}
	private void menu3() throws Exception {
		String select = JOptionPane.showInputDialog("1 : ��ȣ�� �˻� \n 2 : �̸����� �˻�").trim();
		String sql = "SELECT * FROM student WHERE ";
		switch(select) {
		default : JOptionPane.showMessageDialog(null, "�߸��� �Է�. �޴��� ���ư���"); return;
		case "1":
			int num  = Integer.parseInt(JOptionPane.showInputDialog("�л� ��ȣ�� �Է��ϼ���.").trim());
			sql += "num = ?"; 
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			break;
		case "2":
			String name = JOptionPane.showInputDialog("�л� �̸��� �Է��ϼ���.").trim();
			sql += "name = ?"; 
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			break;
		}
		rs = ps.executeQuery();
		JOptionPane.showMessageDialog(null, select(rs));
		MyConnection.closeAllConnection(ps, rs);
		
	}
	private void menu4() throws Exception {
		String sql = "SELECT * FROM student WHERE grade = ? ORDER BY average DESC"; 
		int num = Integer.parseInt(JOptionPane.showInputDialog("����� ��ȸ�� �г��� �Է��ϼ���.").trim());
		ps = con.prepareStatement(sql); 
		ps.setInt(1, num);
		JOptionPane.showMessageDialog(null, select(ps.executeQuery(), true)); 
		MyConnection.closeAllConnection(ps, rs);
	}
	private void menu5() throws Exception {
		String sql = "SELECT AVG(average) FROM student WHERE grade = ?"; 
		int num = Integer.parseInt(JOptionPane.showInputDialog("����� ��ȸ�� �г��� �Է��ϼ���.").trim());
		ps = con.prepareStatement(sql); 
		ps.setInt(1, num);
		
		rs = ps.executeQuery();
		rs.next();
		JOptionPane.showMessageDialog(null, num + "�г� ��ü ��� : " + String.format("%.2f", rs.getDouble("AVG(average)")));
		MyConnection.closeAllConnection(ps, rs);
	}
	private void menu6() throws Exception {
	
		String name = JOptionPane.showInputDialog("������ �л��� �̸�");
		String contact = JOptionPane.showInputDialog("������ �л��� ����ó �޹�ȣ 4�ڸ�");
		String message = "���� ����.."; 
		ps = con.prepareStatement("select num from student where name = ? AND contact LIKE ?");
		ps.setString(1, name);
		ps.setString(2, "%" + contact);
		rs = ps.executeQuery();
		rs.next();
		int num = rs.getInt("num");
		if(num == 0) {
			JOptionPane.showMessageDialog(null, "������ �л��� �����ϴ�.");
			return;
		}
		
		
		String sql = "UPDATE student "; 
		
		String select = JOptionPane.showInputDialog("������ ����?\n1 : �̸�\n2 : ����ó\n3: ����\n4: �г�").trim();
		switch(select) {
		default : JOptionPane.showMessageDialog(null, "�߸��� �Է�. �޴��� ���ư���"); return;
		case "1":
			String tName = JOptionPane.showInputDialog("�� �̸�"); 
			sql += "SET name = ? WHERE num = " + num;
			ps = con.prepareStatement(sql);
			ps.setString(1, tName);
			break;
		case "2":
			String tContact = JOptionPane.showInputDialog("�� ����ó");
			sql += "SET contact = ? WHERE num = " + num;
			ps = con.prepareStatement(sql);
			ps.setString(1, tContact);
			break;
		case "3":
			int tKr = Integer.parseInt(JOptionPane.showInputDialog("�� ��������"));
			int tEn = Integer.parseInt(JOptionPane.showInputDialog("�� ��������"));
			int tMa = Integer.parseInt(JOptionPane.showInputDialog("�� ��������"));
			sql += "SET kr = ?, en = ?, ma = ?, average = ? WHERE num = " + num;
			ps = con.prepareStatement(sql);
			ps.setInt(1, tKr);
			ps.setInt(2, tEn);
			ps.setInt(3, tMa);
			ps.setDouble(4, (tKr + tEn + tMa)/3.0) ;
			break;
		case "4":
			int tGrade = Integer.parseInt(JOptionPane.showInputDialog("�� �г�"));
			sql += "SET grade = ? WHERE num = " + num;
			ps = con.prepareStatement(sql); 
			ps.setInt(1, tGrade);
			break;
		}
		if(ps.executeUpdate() == 1) {
			message = "���� �Ϸ�!"; 
		}
		JOptionPane.showMessageDialog(null, message);
		
		MyConnection.closeAllConnection(ps, rs);
	}
	
						
	public Quiz03() {
		con = MyConnection.getMyConnection();
		menu: while(true) {
			try {
				switch(JOptionPane.showInputDialog(menu)) {
				case "1": menu1(); break;
				case "2": menu2(); break;
				case "3": menu3(); break;
				case "4": menu4(); break;
				case "5": menu5(); break;
				case "6": menu6(); break;
				case "0": break menu;
				default: JOptionPane.showMessageDialog(null, "�ٽ� �Է��ϼ���.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // while
		MyConnection.closeAllConnection();
	} // Quiz03()
	
	private String select (ResultSet rs) throws Exception{
		return select(rs, false);
	}
	private String select (ResultSet rs, boolean rank) throws Exception{
		String message = "";
		if(rank) {
			int index = 1;
			while(rs.next()) {
				message += index++ + "�� : " 
						+ rs.getInt("num") + "�� / " 
						+ rs.getString("name") + " / " 
						+ rs.getString("contact") + " / "
						+ rs.getInt("grade") + "�г�" + " / "
						+ String.format("%.2f", rs.getDouble("average")) + "�� / "
						+ rs.getString("regdate") + "\n";
			}
		} else {
			while(rs.next()) {
				message += rs.getInt("num") + "�� / " 
						+ rs.getString("name") + " / " 
						+ rs.getString("contact") + " / "
						+ rs.getInt("grade") + "�г�" + " / "
						+ String.format("%.2f", rs.getDouble("average")) + "�� / "
						+ rs.getString("regdate") + "\n";
			}
		}
		return message.isEmpty() ? "��ȸ����� �����ϴ�." : message;
	}

	
	
	public static void main(String[] args) {
		new Quiz03();
	}
}



