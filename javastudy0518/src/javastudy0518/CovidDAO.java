package javastudy0518;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CovidDAO {
	//클래스가 로드될 때 1번만 수행되는 코드
	//제일 먼저 실행되는 코드
	static {
		//MySQL 드라이버 클래스 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.err.println("드라이버 클래스 로드 실패");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	//싱글톤 패턴(인스턴스를 1개만 생성할 수 있도록 하는 패턴)을 위한 코드
	//Back-End Programmer를 주력으로 하고자 하는 경우에는
	//객체지향 디자인 패턴을 학습
	
	//생성자가 private 이므로 외부에서 인스턴스 생성을 못함
	private CovidDAO() {}
	
	//변수를 1개만 생성할 수 있도록 선언
	private static CovidDAO covidDAO;
	
	//외부에서 생성된 인스턴스를 사용할 수 있도록 리턴해주는 메소드
	public static CovidDAO sharedInstance() {
		//static 변수이므로 null을 대입하지 않는 이상
		//맨 처음에만 null이고 이후에는 null이 될 수 없음
		if(covidDAO == null) {
			covidDAO = new CovidDAO();
		}
		return covidDAO;
	}
	//Java Server 개발에는 Spring을 많이 사용하는데 Spring이 자동으로
	//클래스를 Singleton Pattern으로 디자인 해 줍니다.
	
	//여러 메소드에서 공통으로 사용할 변수
	//java.sql 패키지의 클래스를 import
	private Connection con;
	private PreparedStatement pstmt;
	
	//데이터베이스 접속을 위한 메소드 
	private void connect() {
		try {
			//데이터베이스 연결
			con = DriverManager.getConnection(
				"jdbc:mysql://192.168.0.200:3306/sample?useUnicode=true&characterEncoding=utf8",
				"root","*******");
			//System.out.println("데이터베이스 접속 성공");
		}catch(Exception e) {
			System.err.println("데이터베이스 연결 실패");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	//데이터베이스 연결 객체를 정리해주는 메소드
	private void close() {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}catch(Exception e) {
			System.err.println("데이터베이스 해제 실패");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//1.전체보기를 위한 메소드
	//조회는 몇 열의 데이터를 몇 행으로 조회하는지 : 리턴타입
	//where 절이 있는지 확인 : 파라미터
	//select * from covid19;
	public List<Covid> allCovid() {
		//List를 리턴할 때는 인스턴스를 만들고 리턴
		List<Covid> list = new ArrayList<Covid>();
		
		//데이터베이스 연결
		connect();
		
		try {
			//SQL 실행 객체 생성
			pstmt = con.prepareStatement(
				" select * from covid19");
			//SQL 실행
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//List 개별 요소 인스턴스를 생성
				Covid covid = new Covid();
				//인스턴스의 내부 요소를 채우기
				covid.setNum(rs.getInt("num"));
				covid.setNation(rs.getString("nation"));
				covid.setConfirmcount(rs.getInt("confirmcount"));
				covid.setDeathcount(rs.getInt("deathcount"));
				//List에 추가
				list.add(covid);
			}
			
			rs.close();
		}catch(Exception e) {
			System.err.println("전체 데이터 가져오기 실패");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		//데이터베이스 연결 해제
		close();
		
		//데이터가 없을 때는 List의 size가 0
		return list;
	}

	//2.상세보기를 위한 메소드
	//select * from 테이블이름 where 기본키=?
	public Covid getCovid(int num){
		//하나의 행이 리턴되는 경우는 인스턴스를 데이터를 가져왔을 때 생성
		Covid covid = null;
		connect();
		
		try {
			//select 구문의 경우 where절이 있으면
			//데이터를 매개변수로 받아서 바인딩을 해야 합니다.
			pstmt = con.prepareStatement("select * from covid19 where num = ?");
			pstmt.setInt(1, num);
			//SQL 실행
			ResultSet rs = pstmt.executeQuery();
			//리턴되는 데이터가 1개 이하인 경우
			if(rs.next()) {
				//리턴할 데이터의 인스턴스를 생성
				covid = new Covid();
				covid.setNum(rs.getInt("num"));
				covid.setRegion(rs.getString("region"));
				covid.setNation(rs.getString("nation"));
				covid.setPop(rs.getInt("pop"));
				covid.setConfirmcount(
					rs.getInt("confirmcount"));
				covid.setDeathcount(
					rs.getInt("deathcount"));
			}
			
			
		}catch(Exception e) {
			System.out.println("상세보기 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		close();
		return covid;
	}
	
	//covid19테이블에서 가장 큰 num을 찾아오는 메소드
		//select max(num) from covid19;
		public int maxNum() {
			//데이터가 없을 때는 번호가 0이 있다고 가정
			int result = 0;
			connect();
			try {
				pstmt = con.prepareStatement(
					"select max(num) from covid19");
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					//select의 첫번째 컬럼의 값을 정수로 result에 저장
					result = rs.getInt(1);
				}
				
			}catch(Exception e) {
				System.out.println("가장 큰 번호 가져오기 실패");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			close();
			return result;
		}
	
		
		//3.데이터를 삽입하는 메소드
		//insert into 테이블이름(컬럼이름 나열)
		//values(값을 나열)
		//특별한 경우가 아니면 컬럼은 2개 이상
		//select를 제외한 모든 SQL의 실행은 영향받은 행의 개수를 리턴
	public int insertCovid(Covid covid){
		//여기서 -1은 의미없는 값으로 삽입 실패를 의미하는 값
		//어떤 음수라도 가능 - 0은 조심
		int result = -1;
		//가장 큰 num을 찾아서 +1을 한 후 num에 대입
		int num = this.maxNum() + 1;
		//위의 문장이 connect() 뒤에 있으면 데이터베이스 연결을 해제해서
		//예외가 발생
		connect();
		try {
			//SQL 실행 객체 생성 - SQL 생성
			//값을 대입하는 곳은 ?로 설정
			//값을 대입하는 곳 중에서 고정된 값이면 고정된 값을 이용
			pstmt = con.prepareStatement(
				"insert into covid19("
				+ "num, region, nation, pop, "
				+ "confirmcount, deathcount) "
				+ "values(?,?,?,?,?,?)");
			//비어 있는 곳에 값을 채움(바인딩 - Binding)
			//번호를 입력받는 경우
			//pstmt.setInt(1, covid.getNum());
			
			//가장 큰 번호 + 1
			pstmt.setInt(1, num);
			pstmt.setString(2, covid.getRegion());
			pstmt.setString(3, covid.getNation());
			pstmt.setInt(4, covid.getPop());
			pstmt.setInt(5, covid.getConfirmcount());
			pstmt.setInt(6, covid.getDeathcount());
			
			//SQL 실행
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			//자신이 알아볼 수 있는 예외 메시지를 출력
			System.out.println("데이터 삽입 실패");
			//프로그램이 주는 예외 메시지를 출력
			System.out.println(e.getMessage());
			
			//위의 2개 작업은 파일이나 데이터베이스에 기록하고
			//주석 처리
			//예외 발생 지점을 찾기 위한 작업
			e.printStackTrace();
		}
		//데이터베이스 연결 해제
		close();
		return result;
	}
	
	
	//데이터를 삭제하는 메소드
	//delete from 테이블이름 where 기본키 = ?
	//리턴 타입은 정수
	//매개변수는 기본키
	public int deleteCovid(int num){
		int result = -1;
		connect();
		//삽입, 삭제, 갱신은 sql을 생성 부분과 바인딩 하는 부분만 변경
		try {
			pstmt = con.prepareStatement(
				"delete from covid19 where num = ?");
			//데이터 바인딩
			pstmt.setInt(1, num);
			//실행
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("데이터 삭제 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		close();
		return result;
	}
	
	public int updateCovid(Covid covid) {
		
		int result = -1;
		connect();
		//삽입, 삭제, 갱신은 sql을 생성 부분과 바인딩 하는 부분만 변경
		try {
			pstmt = con.prepareStatement(
				"update covid19 set region=?,nation=?,pop=?,confirmcount=?,deathcount=? where num=?");
			//데이터 바인딩
			pstmt.setString(1, covid.getRegion());
			pstmt.setString(2, covid.getNation());
			pstmt.setInt(3, covid.getPop());
			pstmt.setInt(4, covid.getConfirmcount());
			pstmt.setInt(5, covid.getDeathcount());
			pstmt.setInt(6, covid.getNum());

			//실행
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("데이터 수정 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		close();
		return result;
		
	}
	
	//대륙이름이나 국가에 포함된 데이터 찾아오기
	//검색어를 매개변수로 받아서 검색어가 포함된 데이터를 찾아오는 메소드
	public List<Covid> searchCovid(String word){
		List<Covid> list = new ArrayList<>();
		connect();
		try {
			pstmt = con.prepareStatement("select * from Covid19 where region like ? or nation like ?");
			pstmt.setString(1, "%"+word+"%");
			pstmt.setString(2, "%"+word+"%");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Covid covid = new Covid();
				covid.setNum(rs.getInt("num"));
				covid.setRegion(rs.getString("region"));
				covid.setNation(rs.getString("nation"));
				covid.setConfirmcount(rs.getInt("confirmcount"));
				covid.setDeathcount(rs.getInt("deathcount"));
				
				list.add(covid);
			}
			
			
			rs.close();
		}catch(Exception e) {
			System.out.println("데이터 검색 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		close();
		return list;
	}
	
	public int getCount() {
		int result = -1;
		connect();
		try {
		pstmt = con.prepareStatement("select count(*) from covid19");
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			result = rs.getInt(1);
		}
				
		}catch(Exception e) {
			System.out.println("데이터 개수 세기 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		close();
		return result;
		
		//페이지 번호와 페이지 당 데이터 개수를 받아서 
		//페이지 번호에 해당하는 데이터를 조회하는 메소드
		//실제 업무인 경우에는 검색어가 포함됩니다.
	}
		public  List<Covid> pageCovid(int pageno, int pagecnt){
			List<Covid> list = new ArrayList<>();
			connect();
			try {
				pstmt = con.prepareStatement("select * from covid19 limit ?,?" );
				pstmt.setInt(1, pagecnt *(pageno-1));
				pstmt.setInt(2, pagecnt);
				
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Covid covid = new Covid();
					covid.setNum(rs.getInt("num"));
					covid.setNation(rs.getString("nation"));
					covid.setDeathcount(rs.getInt("deathcount"));
					list.add(covid);

				}
			}catch(Exception e) {
				System.out.println("데이터 검색 실패");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			close();
			return list;	
			
			
		}
	}



