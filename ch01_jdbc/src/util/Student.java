package util;

public class Student {
	private String name;
	private int num;
	private String contact;
	private int kr, en, ma;
	private double avg;
	private int grade;
	private String regdate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getKr() {
		return kr;
	}
	public void setKr(int kr) {
		this.kr = kr;
		setAvg();
	}
	public int getEn() {
		return en;
	}
	public void setEn(int en) {
		this.en = en;
		setAvg();
	}
	public int getMa() {
		return ma;
	}
	public void setMa(int ma) {
		this.ma = ma;
		setAvg();
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public void setAvg() {
		this.avg = (kr + en + ma)/ 3.0;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
