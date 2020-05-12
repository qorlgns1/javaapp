package javastudy0422;

public class ExceptionHandleing1 {

	public static void main(String[] args) {
		int x = 20;
		int y = 0;
		try {			
			 int result = x / y;
			 System.out.println("x/y=" + result);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("프로그램 종료!!!!");

	

}
}