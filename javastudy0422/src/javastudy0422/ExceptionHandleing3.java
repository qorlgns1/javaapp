package javastudy0422;

public class ExceptionHandleing3 {

	public static void main(String[] args) {
		//1부터 10까지 숫자를 1초씩 쉬면서 출력
		//현재 스레드를 대기시키는 메소드는
		//java.lang.Thread 클래스의 sleep(int millis)
		
		for(int i =1;i<=10; i++) {
			Thread.sleep(1000);
			System.out.println(i);
		}

	}

}
