package goods;

import java.util.ArrayList;
import java.util.List;

//데이터 베이스 연동 메소드를 소유한 클래스
public class GoodDAO {
	//싱글톤 패턴 디자인 - 객체를 1개만 생성할 수 있도록 만든 패턴
	//외부에서 생성할 수 없도록 생성자를 private으로
	private GoodDAO() {}
	//만들어진 객체의 참조를 저장하기 위한 변수
	private static GoodDAO goodDAO = null;
	//객체가 없으면 만들어서 리턴하고
	//있으면 있는 것을 리턴하도록 하는 객체 사용을 위한 메소드
	public static GoodDAO sharedInstance() {
		if(goodDAO == null) {
			goodDAO = new GoodDAO();
		}
		return goodDAO;
	}
	
	//여러 개의 데이터를 가져오는 메소드
	//select는 where 절을 확인해서 매개변수를 생성
	//List를 리턴할 때는 인스턴스를 만들고 데이터를 추가한 후 리턴
	
	
	//****외우자!!
	//여여러 개의 데이터를 가져올 때는 데이터가 없는 경우 size가 0
	public List<Good> allGood(){
		List<Good> list = new ArrayList<Good>();
		
		
		return list;
		
	}
	
	//code를 가지고 데이터를 찾아오는 메소드
	//code는 기본키
	
	//*****외우자!!!
	//하나의 행만 리턴하는 경우에는 데이터가 없으면 null을 리턴
	public Good getGood(String code){
		Good good = null;
		
		return good;
		
	}
	
	//데이터를 삽입하는 메소드
	//삽입이나 수정은 매개변수가 무조건 DTO 아니면 Map이다
	//-1이 리턴되면 실패
	//0이 리턴되면 조건에 맞는 데이터가 없음
	//양수가 리턴되면 작업을 수행한 것임
	
	public int insertGood(Good good) {
		int result = -1;
		
		return result;
	}
	
	//데이터를 수정하는 메소드
	//삽입하는 메소드와 동일한 모양
	public int updateGood(Good good) {
		int result = -1;
		
		return result;
	}
	
	//데이터를 삭제하는 메소드
	
	//생각해보자!!
	//삭제는 대부분 기본키로만 수행
	//기본키만 찾으면 바로 삭제할수 있다!
	public int deleteGood(String code) {
		int result = -1;
		
		return result;
	}
}
