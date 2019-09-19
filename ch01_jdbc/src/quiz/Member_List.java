package quiz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.MemberDAO;

public class Member_List extends JFrame implements MouseListener,ActionListener {
	Vector v;  
    Vector cols;
    DefaultTableModel model;
    JTable jTable;
    JScrollPane pane;
    JPanel pbtn;
    JButton btnInsert;
       
   
    public Member_List(){
        super("회원관리 프로그램  v0.1.1");
        //v=getMemberList();
        //MemberDAO
        MemberDAO dao = new MemberDAO();
        v = dao.getMemberList();
        System.out.println("v="+v);
        cols = getColumn();
       
        //public DefaultTableModel()
        //public DefaultTableModel(int rowCount, int columnCount)
        //public DefaultTableModel(Vector columnNames, int rowCount)
        //public DefaultTableModel(Object[] columnNames, int rowCount)
        //public DefaultTableModel(Vector data,Vector columnNames)
        //public DefaultTableModel(Object[][] data,Object[] columnNames)
       
        model = new DefaultTableModel(v, cols);
       
        //JTable()
        //JTable(int numRows, int numColumns)
        //JTable(Object[][] rowData, Object[] columnNames)
        //JTable(TableModel dm)
        //JTable(TableModel dm, TableColumnModel cm)
        //JTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm)
        //JTable(Vector rowData, Vector columnNames)
       
        //jTable = new JTable(v,cols);
        jTable = new JTable(model);
        pane = new JScrollPane(jTable);
        add(pane);
       
        pbtn = new JPanel();
        btnInsert = new JButton("학생등록");
        pbtn.add(btnInsert);
        add(pbtn,BorderLayout.NORTH);
       
       
        jTable.addMouseListener(this); //리스너 등록
        btnInsert.addActionListener(this); //회원가입버튼 리스너 등록
       
        setSize(600,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end 생성자
   
   
    //JTable의 컬럼
    public Vector getColumn(){
        Vector col = new Vector();
        col.add("번호");
        col.add("이름");
        col.add("전화");
        col.add("국어점수");
        col.add("영어점수");
        col.add("수학점수");
        col.add("학년");
       
        return col;
    }//getColumn
   
   
    //Jtable 내용 갱신 메서드
    public void jTableRefresh(){
       
        MemberDAO dao = new MemberDAO();
        DefaultTableModel model= new DefaultTableModel(dao.getMemberList(), getColumn());
        jTable.setModel(model);    
       
    }
   
    public static void main(String[] args) {
        new Member_List();
    }//main
    @Override
    public void mouseClicked(MouseEvent e) {
        // mouseClicked 만 사용
        int r = jTable.getSelectedRow();
        String num = (String) jTable.getValueAt(r, 0);
        //System.out.println("id="+id);
        MemberProc mem = new MemberProc(num,this); //아이디를 인자로 수정창 생성
               
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
       
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //버튼을 클릭하면
        if(e.getSource() == btnInsert ){
            new MemberProc(this);
           
            /*테스트*/
            //dao = new MemberDAO();           
            //dao.userSelectAll(model);
            //model.fireTableDataChanged();
            //jTable.updateUI();           
            //jTable.requestFocusInWindow();
           
        }
       
    }
   
}
