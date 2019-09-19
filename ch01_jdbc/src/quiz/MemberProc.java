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
    JTextField tfTel1, tfTel2, tfTel3; //전화
    JButton btnInsert, btnCancel, btnUpdate,btnDelete; //가입, 취소, 수정 , 탈퇴 버튼
   
    GridBagLayout gb;
    GridBagConstraints gbc;
    Member_List mList ;
   
    public MemberProc(){ //가입용 생성자
       
        createUI(); // UI작성해주는 메소드
        btnUpdate.setEnabled(false);
        btnUpdate.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
       
       
    }//생성자
   
    public MemberProc(Member_List mList){ //가입용 생성자
       
        createUI(); // UI작성해주는 메소드
        btnUpdate.setEnabled(false);
        btnUpdate.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
        this.mList = mList;
       
    }//생성자
    public MemberProc(String id,Member_List mList){ // 수정/삭제용 생성자
        createUI();
        btnInsert.setEnabled(false);
        btnInsert.setVisible(false);
        this.mList = mList;
       
       
        System.out.println("id="+id);
       
        MemberDAO dao = new MemberDAO();
        MemberDTO vMem = dao.getMemberDTO(id);
        viewData(vMem);
       
       
    }//id를 가지고 생성
 
       
    //MemberDTO 의 회원 정보를 가지고 화면에 셋팅해주는 메소드
    private void viewData(MemberDTO vMem){
       
        
        String num = vMem.getNum();
        String name = vMem.getName();
        String contact = vMem.getContact();
        String kr = vMem.getKr();
        String en = vMem.getEn();
        String ma = vMem.getMa();
        String grade= vMem.getGrade();
        String average = vMem.getAverage();    
       
        //화면에 세팅
        tfNum.setText(num);
        tfNum.setEditable(false); //편집 안되게
       
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
        this.setTitle("학생정보");
        gb = new GridBagLayout();
        setLayout(gb);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
       
       
        //아이디
        JLabel bId = new JLabel("번호 : ");
        tfNum = new JTextField(20);     
        //그리드백에 붙이기
        gbAdd(bId, 0, 0, 1, 1);
        gbAdd(tfNum, 1, 0, 3, 1);
       
       
        //이름
        JLabel bName = new JLabel("이름 :");
        tfName = new JTextField(20);
        gbAdd(bName,0,2,1,1);
        gbAdd(tfName,1,2,3,1);
       
        //전화
        JLabel bTel = new JLabel("전화 :");
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
       
       
        //버튼
        JPanel pButton = new JPanel();
        btnInsert = new JButton("가입");
        btnUpdate = new JButton("수정"); 
        btnDelete = new JButton("탈퇴");
        btnCancel = new JButton("취소");     
        pButton.add(btnInsert);
        pButton.add(btnUpdate);
        pButton.add(btnDelete);
        pButton.add(btnCancel);    
        gbAdd(pButton, 0, 10, 4, 1);
       
        //버튼에 감지기를 붙이자
        btnInsert.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnCancel.addActionListener(this);
        btnDelete.addActionListener(this);
       
        setSize(350,500);
        setVisible(true);
        //setDefaultCloseOperation(EXIT_ON_CLOSE); //System.exit(0) //프로그램종료
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //dispose(); //현재창만 닫는다.
       
       
    }//createUI
   
    //그리드백레이아웃에 붙이는 메소드
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
            System.out.println("insertMember() 호출 종료");
        }else if(ae.getSource() == btnCancel){
            this.dispose(); //창닫기 (현재창만 닫힘)
            //system.exit(0)=> 내가 띄운 모든 창이 다 닫힘          
        }else if(ae.getSource() == btnUpdate){
            UpdateMember();            
        }else if(ae.getSource() == btnDelete){
            //int x = JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?");
            int x = JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION);
           
            if (x == JOptionPane.OK_OPTION){
                
            }else{
                JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
            }
        }
       
        //jTable내용 갱신 메소드 호출
        mList.jTableRefresh();
       
    }//actionPerformed 
   
   
    
   
    private void UpdateMember() {
       
        //1. 화면의 정보를 얻는다.
        MemberDTO dto = getViewData();     
        //2. 그정보로 DB를 수정
        MemberDAO dao = new MemberDAO();
        boolean ok = dao.updateMember(dto);
       
        if(ok){
            JOptionPane.showMessageDialog(this, "수정되었습니다.");
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "수정실패: 값을 확인하세요");   
        }
    }
 
    private void insertMember(){
       
        //화면에서 사용자가 입력한 내용을 얻는다.
        MemberDTO dto = getViewData();
        MemberDAO dao = new MemberDAO();       
        boolean ok = dao.insertMember(dto);
       
        if(ok){
           
            JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
            dispose();
           
        }else{
           
            JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
        }
       
       
       
    }//insertMember
   
    public MemberDTO getViewData(){
       
        //화면에서 사용자가 입력한 내용을 얻는다.
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
        
       
       
       
        //dto에 담는다.
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