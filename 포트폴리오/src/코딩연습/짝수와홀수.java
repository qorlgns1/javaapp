package 코딩연습;

public class 짝수와홀수 {
	    public String solution(int num) {
	    	String result = ""; 	
	       if(num%2==0){
	           result= "Even";
	       }else{
	    	   result= "Odd";
	       }
	       return result;
	}
	    
	   public static void main(String[] args) {
		   짝수와홀수 짝수홀수 = new 짝수와홀수();
		   System.out.println(짝수홀수.solution(1));
		   System.out.println(짝수홀수.solution(102));
		   
	   }
}
