package javastudy0518;

import java.util.List;
import java.util.Scanner;

public class CovidMain {

	public static void main(String[] args) {
		//데이터베이스 사용 객체를 생성
		CovidDAO dao = CovidDAO.sharedInstance();
		//키보드 입력 객체를 생성
		Scanner sc = new Scanner(System.in);
		
		//여러 개의 데이터를 저장하기 위한 변수
		List<Covid> list = null;
		//하나의 데이터를 저장하기 위한 변수
		Covid covid = null;
		//삽입, 삭제, 갱신의 결과를 저장하기 위한 변수
		int result = -1;
		
		//Covid 각각을 위한 변수
		int num = -1;
		String region = null;
		String nation = null;
		int pop = -1;
		int confirmcount = -1;
		int deathcount = -1;
		
		//mainloop 라고 이름을 붙인 이유는 
		//내부에서 switch 를 사용할 것이고 
		//7번을 누를 때 반복문을 한번에 빠져나오기 위해서입니다.
		mainloop : while(true) {
			System.out.println(
				"1.전체보기  2.2개씩 보기 3.상세보기 "
				+ "4.대륙이나 국가로 검색\n"
				+ "5.데이터삽입 6.데이터수정 7.데이터삭제 "
				+ "8.프로그램 종료");
			System.out.print("메뉴 입력:");
			String menu = sc.nextLine();
			switch(menu) {
			case "1":
				//전체 가져오기를 호출
				list = dao.allCovid();
				//데이터 출력
				System.out.printf("%5s%15s%10s\n",
						"번호", "국가", "사망자수");
				for(Covid imsi : list) {
					System.out.printf("%5d%15s%10d\n",
							imsi.getNum(),
							imsi.getNation(),
							imsi.getConfirmcount());
				}
				
				break;
			case "2":
				System.out.println("2개씩 보기");
				break;
			case "3":
				//조회할 번호를 입력받기
				System.out.print("조회할 번호:");
				String temp = sc.nextLine();
				try {
					num = Integer.parseInt(temp);
					//데이터 가져오기
					covid = dao.getCovid(num);
					if(covid == null) {
						System.out.println(
							"해당하는 번호의 데이터가 없습니다.");
					}else {
						System.out.println(covid);
					}
				}catch(Exception e) {
					System.out.println("정수를 입력하세요!!");
					break;
				}
				
				break;
			case "4":
				System.out.println("대륙이나 국가로 검색");
				break;
			case "5":
				System.out.println("데이터 삽입");
				break;
			case "6":
				System.out.println("데이터 수정");
				break;
			case "7":
				System.out.println("데이터 삭제");
				break;
			case "8":
				System.out.println("프로그램 종료");
				break mainloop;	
			default:
				System.out.println("잘못된 메뉴 선택 !!!");
				break;
			}
		}
		//키보드 입력 객체 닫기
		sc.close();
	}
}








