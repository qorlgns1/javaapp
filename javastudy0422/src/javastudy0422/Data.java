package javastudy0422;

import java.util.Arrays;

//여러개의 속성을 모아서 하나로 표현하기 위한 클래스
	//VO(Variable Object)
	//DTO(Data Transfer Object)
	//Domain 클래스 라고도 합니다.
public class Data {
	private int num;
	private int [] ar;

	//생성자 추가
	//데이터를 직접 입력하는 경우에 생성자를 추가하고 
	//데이터를 외부에서 읽어오는 경우에는 추가를 하지 않는 경우도 있음
	public Data() {
		super();		
	}
	
	public Data(int num, int[] ar) {
		super();
		this.num = num;
		this.ar = ar;
	}
	
	//접근자 메소드
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int[] getAr() {
		return ar;
	}

	public void setAr(int[] ar) {
		this.ar = ar;
	}

	//프로퍼티를 하나의 문자열로 리턴하는 메소드
	public String getData() {
		String result = "num: " + num + " ar: ";
		for (int i = 0; i < ar.length; i++) {
			result = result + ar[i] + "\t";
		}
		return result;
	}

	@Override
	public String toString() {
		return "Data [num=" + num + ", ar=" + Arrays.toString(ar) + "]";
	}
	
	
	//인스턴스의 내용을 비교하는 메소드
	public boolean equals(Data other) {
		return this.ar == other.ar;
	
	}
	
	//내용을 복사해서 새로운 인스턴스를 만들어서 리턴해주는 메소드
	public Data clone() {
		//새로운 인스턴스를 생성
		Data other = new Data();
		//자신의 데이터들을 새로운 인스턴스에 대입 -약한 복사 (weak copy)
		other.num= this.num;
		//other.ar= this.ar;

		//깊은 복사를 할 때는 참조형은 다시 복제를 해주어야 합니다.
		//배열의 데이터 복사
		other.ar = new int[this.ar.length];
		for (int i = 0; i < ar.length; i++) {
			other.ar[i] = this.ar[i];
		}
		return other;
	}
	
	
	
}
	
