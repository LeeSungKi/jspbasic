package DTO;

public class Dto {
	private String name;
	private String contact;
	private int kr;
	private int en;
	private int ma;
	private int grade;
	private int average;
	
	public Dto() {
		
	}
	public Dto(String name, String contact, int kr, int en, int ma ,int grade, int average) {
		this.name =name;
		this.contact= contact;
		this.kr= kr;
		this.en= en;
		this.ma=ma;
		this.grade = grade;
		this.average = average;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		getAverage();
	}
	public int getEn() {
		return en;
	}
	public void setEn(int en) {
		this.en = en;
		getAverage();
	}
	public int getMa() {
		return ma;
	}
	public void setMa(int ma) {
		this.ma = ma;
		getAverage();
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = (kr+en+ma)/3;
	}
	
}
