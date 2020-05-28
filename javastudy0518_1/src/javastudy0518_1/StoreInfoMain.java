package javastudy0518_1;

import java.util.List;
import java.util.Scanner;

public class StoreInfoMain {

	public static void main(String[] args) {
		StoreInfoDAO dao = StoreInfoDAO.sharedInstance();
		StoreInfo storeinfo = null;
		Scanner sc = new Scanner(System.in);
		
		List<StoreInfo> list = null;
		
		int num = -1;
		String name = null;
		String bm = null;
		String addr = null;
		String storenum = null;
		String intro = null;
		int tablecount = -1;
		
		String temp = null;
		int result = -1;
		
		mainloop : while(true) {
			System.out.println("1.전체보기 2. 2개씩보기 3.상세보기 4.가게 이름이나 주소로 검색 "
					+ "5.데이터 삽입 6.데이터 수정 7.데이터 삭제 8.프로그램 종료");
		System.out.println("메뉴 입력: ");
		String menu = sc.nextLine();
		
		switch(menu) {
		case "1" :
			list = dao.allStoreInfo();
			
			System.out.printf("가게이름" + "\t" +"주소"+"\t\t\t"+ "전화번호"+"\t\t"+ "소개" + "\n");
			
			for(StoreInfo imsi : list) {
				
				System.out.println(imsi.getName()+"\t"+imsi.getAddr()+"\t\t"+imsi.getStorenum()+"\t"+imsi.getIntro());
			
			}
				
			break;
		case "2" :
			System.out.println("2개씩보기");
			break;
		case "3" :
			//조회할 번호를 입력받기
			System.out.print("조회할 번호:");
			temp = sc.nextLine();
			try {
				num = Integer.parseInt(temp);
				//데이터 가져오기
				storeinfo = dao.getStoreInfo(num);
				if(storeinfo == null) {
					System.out.println(
						"해당하는 번호의 데이터가 없습니다.");
				}else {
					System.out.println(storeinfo);
					//System.out.printf("%5d\t%15s\n",covid.getNum(), covid.getNation());
				}
			}catch(Exception e) {
				System.out.println("정수를 입력하세요!!");
				
			}
			
			break;
			
		case "4" :
			System.out.println("가게 이름이나 주소로 검색");
			break;
		case "5" :
			System.out.println("데이터 삽입");
			break;
		case "6" :
			//기본키 값 입력받기
			System.out.print("수정할 번호:");
			num = sc.nextInt();
			sc.nextLine(); //Enter를 가져가기 위해서 추가
			//데이터 찾아오기
			storeinfo = dao.getStoreInfo(num);
			if(storeinfo == null) {
				System.out.println("없는 번호입니다.");
			}else {
				
				System.out.println("수정하지 않으려면 Enter");
				System.out.print("가게 이름 입력(이전-" + 
						storeinfo.getName() + "):");
				name = sc.nextLine();
				//글자 수가 0이면 이전 값 갖기
				if(name.trim().length() == 0) {
					name = storeinfo.getName();
				}
				//주소입력받기
				System.out.println("수정하지 않으려면 Enter");
				System.out.print("주소 입력(이전-" + 
					storeinfo.getAddr() + "):");
				addr = sc.nextLine();
				//글자 수가 0이면 이전 값 갖기
				if(addr.trim().length() == 0) {
					addr = storeinfo.getAddr();
				}
				//관리자 입력받기
				System.out.println("수정하지 않으려면 Enter");
				System.out.print("관리자 입력(이전-" + 
						storeinfo.getBm()+ "):");
				bm = sc.nextLine();
				if(bm.trim().length() == 0) {
					bm = storeinfo.getBm();
				}
				
				//테이블개수 입력받기
				System.out.println("수정하지 않으려면 Enter");
				System.out.print("테이블개수 입력(이전-" + 
					storeinfo.getTablecount() + "):");
				temp = sc.nextLine();
				if(temp.trim().length() == 0) {
					tablecount = storeinfo.getTablecount();
				}else {
					tablecount = Integer.parseInt(temp);
				}
				//가게소개  입력받기
				System.out.println("수정하지 않으려면 Enter");
				System.out.print("가게소개 입력(이전-" + 
					storeinfo.getIntro() + "):");
				temp = sc.nextLine();
				if(temp.trim().length() == 0) {
					intro = storeinfo.getIntro();
				}
				//입력받은 데이터를 하나로 만들기
				storeinfo.setName(name);
				storeinfo.setAddr(addr);
				storeinfo.setBm(bm);
				storeinfo.setTablecount(tablecount);
				storeinfo.setIntro(intro);
				
				
				result = dao.updateCovid(storeinfo);
				
				if(result > 0) {
					System.out.println("수정 성공");
				}else {
					System.out.println("수정 실패");
				}
			}
			
			break;
		case "7" :
			System.out.println("데이터 삭제");
			break;
		case "8" :
			System.out.println("프로그램 종료");
			break mainloop;
		default :	
			System.out.println("잘못된 메뉴를 입력하셨습니다.");
			break;	
		}	
		}
		sc.close();
		
		
	}

}
