package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import DTO.MemberDTO;
import quiz.Member_List;

//DB ó��
	public class MemberDAO {
	 
	    private static final String DRIVER
	        = "oracle.jdbc.driver.OracleDriver";
	    private static final String URL
	        = "jdbc:oracle:thin:@localhost:1521:ORCL";
	   
	    private static final String USER = "web1400"; //DB ID
	    private static final String PASS = "jsppassword"; //DB �н�����
	    Member_List mList;
	   
	    public MemberDAO() {
	   
	    }
	   
	    public MemberDAO(Member_List mList){
	        this.mList = mList;
	        System.out.println("DAO=>"+mList);
	    }
	   
	    /**DB���� �޼ҵ�*/
	    public Connection getConn(){
	        Connection con = null;
	       
	        try {
	            Class.forName(DRIVER); //1. ����̹� �ε�
	            con = DriverManager.getConnection(URL,USER,PASS); //2. ����̹� ����
	           
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       
	        return con;
	    }
	   
	   
	    /**�ѻ���� ȸ�� ������ ��� �޼ҵ�*/
	    public MemberDTO getMemberDTO(String id){
	       
	        MemberDTO dto = new MemberDTO();
	       
	        Connection con = null;       //����
	        PreparedStatement ps = null; //���
	        ResultSet rs = null;         //���
	       
	        try {
	           
	            con = getConn();
	            String sql = "select * from tb_member where id=?";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, id);
	           
	            rs = ps.executeQuery();
	           
	            if(rs.next()){
	                dto.setNum(rs.getString("num"));
	                dto.setName(rs.getString("Name"));
	                dto.setContact(rs.getString("contact"));
	                dto.setKr(rs.getString("kr"));
	                dto.setEn(rs.getString("en"));
	                dto.setMa(rs.getString("ma"));
	                dto.setGrade(rs.getString("grade"));
	                dto.setAverage(rs.getString("average"));
	               
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }      
	       
	        return dto;    
	    }
	   
	    /**�������Ʈ ���*/
	    public Vector getMemberList(){
	       
	        Vector data = new Vector();  //Jtable�� ���� ���� �ִ� ��� 1. 2�����迭   2. Vector �� vector�߰�
	       
	           
	        Connection con = null;       //����
	        PreparedStatement ps = null; //���
	        ResultSet rs = null;         //���
	       
	        try{
	           
	            con = getConn();
	            String sql = "select * from tb_member order by name asc";
	            ps = con.prepareStatement(sql);
	            rs = ps.executeQuery();
	           
	            while(rs.next()){
	                String num = rs.getString("num");
	                String name = rs.getString("name");
	                String contact = rs.getString("contact");
	                String kr = rs.getString("kr");
	                String en = rs.getString("en");
	                String ma = rs.getString("ma");
	                String grade = rs.getString("grade");
	                String average = rs.getString("average");
	               
	                Vector row = new Vector();
	                row.add(num);
	                row.add(name);
	                row.add(contact);
	                row.add(kr);
	                row.add(en);
	                row.add(ma);
	                row.add(grade);
	                row.add(average);
	               
	                data.add(row);             
	            }//while
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return data;
	    }//getMemberList()
	   
	 
	 
	    /**ȸ�� ���*/
	    public boolean insertMember(MemberDTO dto){
	       
	        boolean ok = false;
	       
	        Connection con = null;       //����
	        PreparedStatement ps = null; //���
	       
	        try{
	           
	            con = getConn();
	            String sql = "insert into student(" +
	                        "num,name,contact,kr,en," +
	                        "ma,grade,average) "+
	                        "values(?,?,?,?,?,?,?,?)";
	           
	            ps = con.prepareStatement(sql);
	            ps.setString(1, dto.getNum());
	            ps.setString(3, dto.getName());
	            ps.setString(4, dto.getContact());
	            ps.setString(5, dto.getKr());
	            ps.setString(6, dto.getEn());
	            ps.setString(7, dto.getMa());
	            ps.setString(8, dto.getGrade());
	            ps.setString(9, dto.getAverage());          
	            int r = ps.executeUpdate(); //���� -> ����
	           
	           
	            if(r>0){
	                System.out.println("��� ����");   
	                ok=true;
	            }else{
	                System.out.println("��� ����");
	            }
	           
	               
	           
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	       
	        return ok;
	    }//insertMmeber
	   
	   
	    /**ȸ������ ����*/
	    public boolean updateMember(MemberDTO vMem){
	        System.out.println("dto="+vMem.toString());
	        boolean ok = false;
	        Connection con = null;
	        PreparedStatement ps = null;
	        try{
	           
	            con = getConn();           
	            String sql = "update student set name=?, contact=?, kr=?, en=?, ma=?, grade=?" +
	                    ", average=?"+ "where num=?";
	           
	            ps = con.prepareStatement(sql);
	           
	            ps.setString(1, vMem.getName());
	            ps.setString(2, vMem.getContact());
	            ps.setString(3, vMem.getKr());
	            ps.setString(4, vMem.getEn());
	            ps.setString(5, vMem.getMa());
	            ps.setString(6, vMem.getGrade());
	            ps.setString(7, vMem.getAverage());
	            
	            ps.setString(9, vMem.getNum());
	           
	            int r = ps.executeUpdate(); //���� -> ����
	            // 1~n: ���� , 0 : ����
	           
	            if(r>0) ok = true; //������ �����Ǹ� ok���� true�� ����
	           
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	       
	        return ok;
	    }
	   
	    /**ȸ������ ���� :
	     *tip: �ǹ������� ȸ�������� Delete ���� �ʰ� Ż�𿩺θ� üũ�Ѵ�.*/
	    public boolean deleteMember(String id, String pwd){
	       
	        boolean ok =false ;
	        Connection con =null;
	        PreparedStatement ps =null;
	       
	        try {
	            con = getConn();
	            String sql = "delete from student where id=? and pwd=?";
	           
	            ps = con.prepareStatement(sql);
	            ps.setString(1, id);
	            ps.setString(2, pwd);
	            int r = ps.executeUpdate(); // ���� -> ����
	           
	            if (r>0) ok=true; //������;
	           
	        } catch (Exception e) {
	            System.out.println(e + "-> �����߻�");
	        }      
	        return ok;
	    }
	   
	   
	    /**DB������ �ٽ� �ҷ�����*/   
	    public void userSelectAll(DefaultTableModel model) {
	       
	       
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	       
	        try {
	            con = getConn();
	            String sql = "select * from student order by name asc";
	            ps = con.prepareStatement(sql);
	            rs = ps.executeQuery();
	           
	            // DefaultTableModel�� �ִ� ������ �����
	            for (int i = 0; i < model.getRowCount();) {
	                model.removeRow(0);
	            }
	 
	            while (rs.next()) {
	                Object data[] = { rs.getString(1), rs.getString(2),
	                        rs.getString(3), rs.getString(4),
	                       rs.getString(5),
	                        rs.getString(6),
	                        rs.getString(7),
	                        rs.getString(8),
	                        rs.getString(9),
	                        rs.getString(10)};
	 
	                model.addRow(data);                
	            }
	 
	        } catch (SQLException e) {
	            System.out.println(e + "=> userSelectAll fail");
	        } finally{
	           
	            if(rs!=null)
	                try {
	                    rs.close();
	                } catch (SQLException e2) {
	                    // TODO Auto-generated catch block
	                    e2.printStackTrace();
	                }
	            if(ps!=null)
	                try {
	                    ps.close();
	                } catch (SQLException e1) {
	                    // TODO Auto-generated catch block
	                    e1.printStackTrace();
	                }
	            if(con!=null)
	                try {
	                    con.close();
	                } catch (SQLException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	        }
	    }
	}


