package DTO;

	public class MemberDTO {
		   
		   
	    private String num;
	    private String name;
	    private String contact;
	    private String kr;
	    private String en;
	    private String ma ;
	    private String grade;
	    private String average;
	 
	    //이클립스팁 : Getter/Setter 만들기
	    //             우클릭 -> source->Generate Getters And Setters-> [Select AlL] -> [OK]
	   
	   
	   
	    //DTO 객체 확인
	    //이클립스팁 : toString() 자동생성: 우클릭 -> source -> Generate toString->[OK]

		public String getNum() {
			return num;
		}

		public void setNum(String num) {
			this.num = num;
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

		public String getKr() {
			return kr;
		}

		public void setKr(String kr) {
			this.kr = kr;
		}

		public String getEn() {
			return en;
		}

		public void setEn(String en) {
			this.en = en;
		}

		public String getMa() {
			return ma;
		}

		public void setMa(String ma) {
			this.ma = ma;
		}

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public String getAverage() {
			return average;
		}

		public void setAverage(String average) {
			this.average = average;
		}
		
		@Override
		public String toString() {
			return "MemberDTO [num=" + num + ", name=" + name
					+ ", contact=" + contact + ", kr=" + kr + ", en=" + en
					+ ", ma=" + ma + ", grade=" + grade
					+ ", average=" + average + "]";
		}
	}

