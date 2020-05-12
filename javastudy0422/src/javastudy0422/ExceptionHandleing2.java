package javastudy0422;

public class ExceptionHandleing2 {

	public static void main(String[] args) {
		int x = 20;
		if(x > 10) {
			//프로그램을 강제로 중단시키기 위해서
			//예외를 발생시킴
			throw new ArithmeticException("강제로 예외 발생");
		}
		// 예외 발생 가능성이 있는 코드
		try {
			int n1 = 10;
			int n2 = Integer.parseInt("12F");
			int result = n1 / n2;

			System.out.println("결과: " + result);
		}
		// 예외가 발생했을 때 수행할 내용
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// 예외 발생 여부에 상관없이 무조건 수행
		// finally에 작성한 내용은 무조건 수행되지만
		// finally 외부에 작성된 내용은 수행되지 않을 수도 있다.
		finally {
			System.out.println("무조건 수행");
		}

		System.out.println("무조건 수행?");
	}

}
