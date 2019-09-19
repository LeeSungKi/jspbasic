package quiz;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import DAO.MemberDAO;
import DTO.MemberDTO;

public class MemberProc extends JFrame implements ActionListener {
	   
	   
    JPanel p;
    JTextField tfNum, tfName, tfKr, tfEn, tfMa,tfGrade,tfAverage;
    JTextField tfTel1, tfTel2, tfTel3; //��ȭ
    JButton btnInsert, btnCancel, btnUpdate,btnDelete; //����, ���, ���� , Ż�� ��ư
   
    GridBagLayout gb;
    GridBagConstraints gbc;
    Member_List mList ;
   
    public MemberProc(){ //���Կ� ������
       
        createUI(); // UI�ۼ����ִ� �޼ҵ�
        btnUpdate.setEnabled(false);
        btnUpdate.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
       
       
    }//������
   
    public MemberProc(Member_List mList){ //���Կ� ������
       
        createUI(); // UI�ۼ����ִ� �޼ҵ�
        btnUpdate.setEnabled(false);
        btnUpdate.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
        this.mList = mList;
       
    }//������
    public MemberProc(String id,Member_List mList){ // ����/������ ������
        createUI();
        btnInsert.setEnabled(false);
        btnInsert.setVisible(false);
        this.mList = mList;
       
       
        System.out.println("id="+id);
       
        MemberDAO dao = new MemberDAO();
        MemberDTO vMem = dao.getMemberDTO(id);
        viewData(vMem);
       
       
    }//id�� ������ ����
 
       
    //MemberDTO �� ȸ�� ������ ������ ȭ�鿡 �������ִ� �޼ҵ�
    private void viewData(MemberDTO vMem){
       
        
        String num = vMem.getNum();
        String name = vMem.getName();
        String contact = vMem.getContact();
        String kr = vMem.getKr();
        String en = vMem.getEn();
        String ma = vMem.getMa();
        String grade= vMem.getGrade();
        String average = vMem.getAverage();    
       
        //ȭ�鿡 ����
        tfNum.setText(num);
        tfNum.setEditable(false); //���� �ȵǰ�
       
        tfName.setText(name);
        String[] contacts = contact.split("-");
        tfTel1.setText(contacts[0]);
        tfTel2.setText(contacts[1]);
        tfTel3.setText(contacts[2]);
        tfKr.setText(kr);
        tfEn.setText(en);
        tfMa.setText(ma);
        tfGrade.setText(grade);
        tfAverage.setText(average);
        
   
       
    }//viewData
   
   
   
    private void createUI(){
        this.setTitle("�л�����");
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
       
       
        //���̵�
        JLabel bId = new JLabel("��ȣ : ");
        tfNum = new JTextField(20);     
        //�׸���鿡 ���̱�
        gbAdd(bId, 0, 0, 1, 1);
        gbAdd(tfNum, 1, 0, 3, 1);
       
       
        //�̸�
        JLabel bName = new JLabel("�̸� :");
        tfName = new JTextField(20);
        gbAdd(bName,0,2,1,1);
        gbAdd(tfName,1,2,3,1);
       
        //��ȭ
        JLabel bTel = new JLabel("��ȭ :");
        JPanel pTel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfTel1 = new JTextField(6);    
        tfTel2 = new JTextField(6);    
        tfTel3 = new JTextField(6);
        pTel.add(tfTel1);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel2);
        pTel.add(new JLabel(" - "));
        pTel.add(tfTel3);
        gbAdd(bTel, 0, 3, 1,1);
        gbAdd(pTel, 1, 3, 3,1);
       
       
        //��ư
        JPanel pButton = new JPanel();
        btnInsert = new JButton("����");
        btnUpdate = new JButton("����"); 
        btnDelete = new JButton("Ż��");
        btnCancel = new JButton("���");     
        pButton.add(btnInsert);
        pButton.add(btnUpdate);
        pButton.add(btnDelete);
        pButton.add(btnCancel);    
        gbAdd(pButton, 0, 10, 4, 1);
       
        //��ư�� �����⸦ ������
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnCancel.addActionListener(this);
        btnDelete.addActionListener(this);
       
        setSize(350,500);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) //���α׷�����
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //dispose(); //����â�� �ݴ´�.
       
       
    }//createUI
   
    //�׸���鷹�̾ƿ��� ���̴� �޼ҵ�
    private void gbAdd(JComponent c, int x, int y, int w, int h){
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gb.setConstraints(c, gbc);
        gbc.insets = new Insets(2, 2, 2, 2);
        add(c, gbc);
    }//gbAdd
   
    public static void main(String[] args) {
       
        new MemberProc();
    }
   
 
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == btnInsert){
            insertMember();
            System.out.println("insertMember() ȣ�� ����");
        }else if(ae.getSource() == btnCancel){
            this.dispose(); //â�ݱ� (����â�� ����)
            //system.exit(0)=> ���� ��� ��� â�� �� ����          
        }else if(ae.getSource() == btnUpdate){
            UpdateMember();            
        }else if(ae.getSource() == btnDelete){
            //int x = JOptionPane.showConfirmDialog(this,"���� �����Ͻðڽ��ϱ�?");
            int x = JOptionPane.showConfirmDialog(this,"���� �����Ͻðڽ��ϱ�?","����",JOptionPane.YES_NO_OPTION);
           
            if (x == JOptionPane.OK_OPTION){
                
            }else{
                JOptionPane.showMessageDialog(this, "������ ����Ͽ����ϴ�.");
            }
        }
       
        //jTable���� ���� �޼ҵ� ȣ��
        mList.jTableRefresh();
       
    }//actionPerformed 
   
   
    
   
    private void UpdateMember() {
       
        //1. ȭ���� ������ ��´�.
        MemberDTO dto = getViewData();     
        //2. �������� DB�� ����
        MemberDAO dao = new MemberDAO();
        boolean ok = dao.updateMember(dto);
       
        if(ok){
            JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "��������: ���� Ȯ���ϼ���");   
        }
    }
 
    private void insertMember(){
       
        //ȭ�鿡�� ����ڰ� �Է��� ������ ��´�.
        MemberDTO dto = getViewData();
        MemberDAO dao = new MemberDAO();       
        boolean ok = dao.insertMember(dto);
       
        if(ok){
           
            JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.");
            dispose();
           
        }else{
           
            JOptionPane.showMessageDialog(this, "������ ���������� ó������ �ʾҽ��ϴ�.");
        }
       
       
       
    }//insertMember
   
    public MemberDTO getViewData(){
       
        //ȭ�鿡�� ����ڰ� �Է��� ������ ��´�.
        MemberDTO dto = new MemberDTO();
        String num = tfNum.getText();
        @SuppressWarnings("deprecation")
		
        String name = tfName.getText();
        String tel1 = tfTel1.getText();
        String tel2 = tfTel2.getText();
        String tel3 = tfTel3.getText();
        String tel = tel1+"-"+tel2+"-"+tel3;
        String kr = tfKr.getText();
        String en = tfEn.getText();
        String ma = tfMa.getText();
        String grade = tfGrade.getText();
        String average = tfAverage.getText();
        
       
       
       
        //dto�� ��´�.
        dto.setNum(num);
        dto.setName(name);
        dto.setContact(tel);
        dto.setKr(kr);
        dto.setEn(en);
        dto.setMa(ma);
        dto.setGrade(grade);
        dto.setAverage(average);
       
        return dto;
    }
   
}//end