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
        super("ȸ������ ���α׷�  v0.1.1");
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
        btnInsert = new JButton("�л����");
        pbtn.add(btnInsert);
        add(pbtn,BorderLayout.NORTH);
       
       
        jTable.addMouseListener(this); //������ ���
        btnInsert.addActionListener(this); //ȸ�����Թ�ư ������ ���
       
        setSize(600,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end ������
   
   
    //JTable�� �÷�
    public Vector getColumn(){
        Vector col = new Vector();
        col.add("��ȣ");
        col.add("�̸�");
        col.add("��ȭ");
        col.add("��������");
        col.add("��������");
        col.add("��������");
        col.add("�г�");
       
        return col;
    }//getColumn
   
   
    //Jtable ���� ���� �޼���
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
        // mouseClicked �� ���
        int r = jTable.getSelectedRow();
        String num = (String) jTable.getValueAt(r, 0);
        //System.out.println("id="+id);
        MemberProc mem = new MemberProc(num,this); //���̵� ���ڷ� ����â ����
               
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
        //��ư�� Ŭ���ϸ�
        if(e.getSource() == btnInsert ){
            new MemberProc(this);
           
            /*�׽�Ʈ*/
            //dao = new MemberDAO();           
            //dao.userSelectAll(model);
            //model.fireTableDataChanged();
            //jTable.updateUI();           
            //jTable.requestFocusInWindow();
           
        }
       
    }
   
}