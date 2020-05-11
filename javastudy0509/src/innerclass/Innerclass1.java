package innerclass;



class OutClass1 {					//외부클래스

	private int num = 10; 			//외부 클래스 private  변수
	private static int sNum = 20;	//외부클래스 정적 변수
	
	static class InstaticClass{		//정적 내부 클래스
			int inNum = 100;		//정적 내부 클래스의 인스턴스 변수
			static int sInNum = 200;//정적 내부 클래스의 정적 변수
	
		void inTest(){
		//num += 10; -외부 클래스의 인스턴스 변수는 사용할 수 없으므로 주석 처리
			System.out.println("InstaticClass inNum = " + inNum + "(내부 클래스의 인스턴스 변수 사용)");
			System.out.println("InstaticClass sInNum = " + sInNum + "(내부 클래스의 정적 변수 사용)");
			System.out.println("OutClass1 sNum = " + sNum + "(외부 클래스의 정적 변수 사용)");
	
		}
	    
		static void sTest() {
			//num += 10;	//외부 클래스와 내부 클래스의 인스턴스 변수는 사용할 수 없으므로 주석처리
			//inNum += 10;  //외부 클래스와 내부 클래스의 인스턴스 변수는 사용할 수 없으므로 주석처리
			
			System.out.println("OutClass1 sNum = " + sNum + "(외부 클래스의 정적 변수 사용)");
			System.out.println("InstaticClass sInNum = " + sInNum + "(내부 클래스의 정적 변수 사용)");
		}
		
	}
	
}

public class Innerclass1{
	
	public static void main(String[] args){
	
		OutClass1.InstaticClass sInClass = new OutClass1.InstaticClass();
		System.out.println("정적 내부 클래스 일반 메서드 호출");
		sInClass.inTest();
		System.out.println();
		System.out.println("정적 내부 클래스의 정적 메서드 호출");
		OutClass1.InstaticClass.sTest();
	}

}