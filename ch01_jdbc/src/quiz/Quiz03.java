package quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import util.MyConnection;
import util.Student;

/*
 * < 메뉴 >
 * 학생 관리 프로그램 만들기 
 * 1. 새 학생 등록
 * 2. 모든 학생 조회
 * 3. 학생 검색
 * 		1) 번호로 검색
 * 		2) 이름으로 검색
 * 4. 학년별 등수 보기 
 * 		학년 입력 --> 해당 학년 학생들을 점수순으로 출력 
 * 5. 학년별 평균 보기 
 * 		학년 입력 --> 해당 학년 학생들의 전체 평균 점수 출력
 * (6. 학생 수정)
 * 0. 종료 
 * 
 */
public class Quiz03 {
	
	private String menu = "1. 새 학생 등록 \n"
						+ "2. 모든 학생 조회 \n"
						+ "3. 학생 검색 \n"
						+ "4. 학년별 등수 보기\n"
						+ "5. 학년별 평균 보기\n"
						+ "6. 학생 수정\n"
						+ "0. 종료";
	private Connection con;
	private PreparedStatement ps; 
	private ResultSet rs;
	// num name contact kr en ma regdate grade average
	private void menu1() throws Exception {
		String sql = "INSERT INTO student VALUES (?,st_seq.NEXTVAL,?,?,?,?,?,?,SYSDATE)";
		String message;
		Student s = new Student();
		s.setName(JOptionPane.showInputDialog("새 학생의 이름").trim());
		s.setContact(JOptionPane.showInputDialog("새 학생의 연락처").trim());
		s.setGrade(Integer.parseInt(JOptionPane.showInputDialog("새 학생의 학년").trim()));
		s.setKr(Integer.parseInt(JOptionPane.showInputDialog("새 학생의 국어점수").trim()));
		s.setEn(Integer.parseInt(JOptionPane.showInputDialog("새 학생의 영어점수").trim()));
		s.setMa(Integer.parseInt(JOptionPane.showInputDialog("새 학생의 수학점수").trim()));
		
		ps = con.prepareStatement(sql); 
		ps.setString(1, s.getName());
		ps.setString(2, s.getContact());
		ps.setInt(3, s.getKr());
		ps.setInt(4, s.getEn());
		ps.setInt(5, s.getMa());
		ps.setDouble(6, s.getAvg());
		ps.setInt(7, s.getGrade());
		
		
		message = ps.executeUpdate() == 1 ? "학생 추가 완료!" : "학생 추가 실패..";
		MyConnection.closeAllConnection(ps, null);
		
		JOptionPane.showMessageDialog(null, "학생 추가 완료!");
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
		String select = JOptionPane.showInputDialog("1 : 번호로 검색 \n 2 : 이름으로 검색").trim();
		String sql = "SELECT * FROM student WHERE ";
		switch(select) {
		default : JOptionPane.showMessageDialog(null, "잘못된 입력. 메뉴로 돌아가기"); return;
		case "1":
			int num  = Integer.parseInt(JOptionPane.showInputDialog("학생 번호를 입력하세요.").trim());
			sql += "num = ?"; 
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			break;
		case "2":
			String name = JOptionPane.showInputDialog("학생 이름을 입력하세요.").trim();
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
		int num = Integer.parseInt(JOptionPane.showInputDialog("등수를 조회할 학년을 입력하세요.").trim());
		ps = con.prepareStatement(sql); 
		ps.setInt(1, num);
		JOptionPane.showMessageDialog(null, select(ps.executeQuery(), true)); 
		MyConnection.closeAllConnection(ps, rs);
	}
	private void menu5() throws Exception {
		String sql = "SELECT AVG(average) FROM student WHERE grade = ?"; 
		int num = Integer.parseInt(JOptionPane.showInputDialog("평균을 조회할 학년을 입력하세요.").trim());
		ps = con.prepareStatement(sql); 
		ps.setInt(1, num);
		
		rs = ps.executeQuery();
		rs.next();
		JOptionPane.showMessageDialog(null, num + "학년 전체 평균 : " + String.format("%.2f", rs.getDouble("AVG(average)")));
		MyConnection.closeAllConnection(ps, rs);
	}
	private void menu6() throws Exception {
	
		String name = JOptionPane.showInputDialog("수정할 학생의 이름");
		String contact = JOptionPane.showInputDialog("수정할 학생의 연락처 뒷번호 4자리");
		String message = "수정 실패.."; 
		ps = con.prepareStatement("select num from student where name = ? AND contact LIKE ?");
		ps.setString(1, name);
		ps.setString(2, "%" + contact);
		rs = ps.executeQuery();
		rs.next();
		int num = rs.getInt("num");
		if(num == 0) {
			JOptionPane.showMessageDialog(null, "수정할 학생이 없습니다.");
			return;
		}
		
		
		String sql = "UPDATE student "; 
		
		String select = JOptionPane.showInputDialog("무엇을 수정?\n1 : 이름\n2 : 연락처\n3: 점수\n4: 학년").trim();
		switch(select) {
		default : JOptionPane.showMessageDialog(null, "잘못된 입력. 메뉴로 돌아가기"); return;
		case "1":
			String tName = JOptionPane.showInputDialog("새 이름"); 
			sql += "SET name = ? WHERE num = " + num;
			ps = con.prepareStatement(sql);
			ps.setString(1, tName);
			break;
		case "2":
			String tContact = JOptionPane.showInputDialog("새 연락처");
			sql += "SET contact = ? WHERE num = " + num;
			ps = con.prepareStatement(sql);
			ps.setString(1, tContact);
			break;
		case "3":
			int tKr = Integer.parseInt(JOptionPane.showInputDialog("새 국어점수"));
			int tEn = Integer.parseInt(JOptionPane.showInputDialog("새 영어점수"));
			int tMa = Integer.parseInt(JOptionPane.showInputDialog("새 수학점수"));
			sql += "SET kr = ?, en = ?, ma = ?, average = ? WHERE num = " + num;
			ps = con.prepareStatement(sql);
			ps.setInt(1, tKr);
			ps.setInt(2, tEn);
			ps.setInt(3, tMa);
			ps.setDouble(4, (tKr + tEn + tMa)/3.0) ;
			break;
		case "4":
			int tGrade = Integer.parseInt(JOptionPane.showInputDialog("새 학년"));
			sql += "SET grade = ? WHERE num = " + num;
			ps = con.prepareStatement(sql); 
			ps.setInt(1, tGrade);
			break;
		}
		if(ps.executeUpdate() == 1) {
			message = "수정 완료!"; 
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
				default: JOptionPane.showMessageDialog(null, "다시 입력하세요.");
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
				message += index++ + "등 : " 
						+ rs.getInt("num") + "번 / " 
						+ rs.getString("name") + " / " 
						+ rs.getString("contact") + " / "
						+ rs.getInt("grade") + "학년" + " / "
						+ String.format("%.2f", rs.getDouble("average")) + "점 / "
						+ rs.getString("regdate") + "\n";
			}
		} else {
			while(rs.next()) {
				message += rs.getInt("num") + "번 / " 
						+ rs.getString("name") + " / " 
						+ rs.getString("contact") + " / "
						+ rs.getInt("grade") + "학년" + " / "
						+ String.format("%.2f", rs.getDouble("average")) + "점 / "
						+ rs.getString("regdate") + "\n";
			}
		}
		return message.isEmpty() ? "조회결과가 없습니다." : message;
	}

	
	
	public static void main(String[] args) {
		new Quiz03();
	}
}



