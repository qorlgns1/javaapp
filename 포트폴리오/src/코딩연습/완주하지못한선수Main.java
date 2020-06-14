package 코딩연습;

import java.util.Arrays;

public class 완주하지못한선수Main {

	//https://programmers.co.kr/learn/courses/30/lessons/42576/solution_groups?language=java
	public static void main(String[] args) {
		String [] participant = {"배기훈","임성현","이상엽","강준희","배성민"};
		String [] completion = {"배기훈","임성현","배성민","강준희"};
		
		Arrays.sort(participant);
		Arrays.sort(completion);
		int i;
		for (i = 0; i < completion.length; i++) {
			if(!participant[i].equals(completion[i])){
				System.out.println(participant[i]);
			}
		}
		
	}

}
