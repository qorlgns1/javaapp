

public class DTO {
	private int num;
	private String name;
	private int score;
	private String dob;
	
	public DTO() {
		super();
		
	}

	public DTO(int num, String name, int score, String dob) {
		super();
		this.num = num;
		this.name = name;
		this.score = score;
		this.dob = dob;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "DTO [num=" + num + ", name=" + name + ", score=" + score + ", dob=" + dob + "]";
	}
	
	
	
}
