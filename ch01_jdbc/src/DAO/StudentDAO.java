package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import DTO.Dto;

public class StudentDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
   
   
   
    public StudentDAO(){
        try{
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String user="web1400",pwd="jsppassword";
           
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            con = DriverManager.getConnection(url,user,pwd);
            System.out.println("DB���� ����: "+con);
           
           
        }catch(Exception e){
            System.out.println(e+"----- ���� ����");
        }
    }//End ������ ------------
   
    public void dbClose(){
        try {
            if(ps!=null) ps.close();
            if(rs!=null) rs.close();
            if(con!=null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e+"===> �ݱ����");
        }
    }
   
    public int insertStudent(String name, String contact, int kr ,int en,int ma,int grade,int average) throws SQLException{
       
        try{
            String sql = "insert into student values(student_seq.NEXTVAL,?,?,?,?,?,?,SYSDATE)";
            ps=con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, contact);
            ps.setInt(3, kr);
            ps.setInt(4, en);
            ps.setInt(5, ma);
            ps.setInt(6, grade);
            ps.setInt(7, average);
           
            int n = ps.executeUpdate();
           
            return n;
       
        }finally{
           
            dbClose();
        }
       
    }//End insertStudent -------------
   
    public int insertStudent(Dto dto) throws SQLException{
       
        String name = dto.getName();
        String contact = dto.getContact();
        int kr = dto.getKr();
        int en = dto.getEn();
        int ma = dto.getMa();
        int grade = dto.getGrade();
        int average= dto.getAverage();
       
        int n = this.insertStudent(name,contact,kr,en,ma,grade, average);
        return n;
       
    }
   
    public Dto[] selectAll()
        throws SQLException{
        try{
            String sql = "select * from student";
            ps = con.prepareStatement(sql);
            rs= ps.executeQuery();
           
            Dto arr[] = makeArray(rs);
            //����� ���� �޼ҵ� -- rs�� ���� ������ StudentDTO[] �迭��
            //����� �����ִ� ���
           
            return arr;
        }finally{
            dbClose();
        }      
       
   
    }//End selectAll()--------------
 
    private Dto[] makeArray(ResultSet rs) throws SQLException {
       
        Vector<Dto> vo = new Vector<Dto>();
       
        while(rs.next()){
           
            String name = rs.getString(1);
            String contact = rs.getString(2);
            int kr = rs.getInt(3);
            int en = rs.getInt(4);
            int ma = rs.getInt(5);
            int grade = rs.getInt(6);
            int average = rs.getInt(7);
           
            Dto record = new Dto(name, contact,kr,en,ma,grade,average);
            vo.add(record);
           
        }//end while--------------
        int size = vo.size();
        Dto arr[] = new Dto[size];
        vo.copyInto(arr); //���Ϳ� ����� �����Ͱ� �迭 arr�� ī�ǵ�
       
        return arr;
    }//end makeArray()-----------------
   
    public Dto[] findStudent(String name) throws SQLException{
       
        try{
                String sql="Select * from student where name=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, name);
                rs = ps.executeQuery();
                Dto[] arr = makeArray(rs);
               
                return arr;
           
        }finally{
           
            dbClose();
        }
       
    }//end findStudent()------------
   
    public int deleteStudent(String id) throws SQLException{
        try{
           
            String sql ="delete from student where id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            int n = ps.executeUpdate();
           
            return n;                  
        }finally{          
            dbClose();
        }      
    }
}

