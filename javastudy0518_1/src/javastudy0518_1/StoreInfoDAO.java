package javastudy0518_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;




public class StoreInfoDAO {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("드라이버 클래스 로드 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 클래스 로드 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	private StoreInfoDAO() {}
	
	private static StoreInfoDAO storeinfoDAO;
	
	public static StoreInfoDAO sharedInstance() {
		if(storeinfoDAO==null) {
			storeinfoDAO = new StoreInfoDAO();
		}
		return storeinfoDAO;
	}
	
	private Connection con;
	private PreparedStatement pstmt;
	
	//데이터베이스 접속을 위한 메소드
	private void connect() {
			try {
				//데이터베이스 연결
				con = DriverManager.getConnection("jdbc:mysql://localhost/mysql","root","Gnfnfn21!!");
			System.out.println("데이터베이스 접속 성공");
			}catch(Exception e) {
				System.err.println("데이터베이스 연결 실패");
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		//데이터베이스 연결 해제 하는 메소드
		private void close() {
			try {
				if(pstmt != null) 
					pstmt.close();			
				if(con != null)
					con.close();
			}catch(Exception e) {
				System.err.println("데이터베이스 연결 해제 실패");
				System.err.println(e.getMessage());
			}
		}
		
		public List<StoreInfo> allStoreInfo(){
			
			List<StoreInfo> list = new ArrayList<>();
			
			connect();
			try {
				//SQL 실행 객체 생성
				pstmt = con.prepareStatement(
					"select * from storeinfo");
				//SQL 실행
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					//List 개별 요소 인스턴스를 생성
					StoreInfo storeinfo = new StoreInfo();
					//인스턴스의 내부 요소를 채우기
					storeinfo.setNum(rs.getInt("num"));
					storeinfo.setName(rs.getString("name"));
					storeinfo.setBm(rs.getString("bm"));
					storeinfo.setAddr(rs.getString("addr"));
					storeinfo.setParking(rs.getBoolean("parking"));
					storeinfo.setStorenum(rs.getString("storenum").trim());
					storeinfo.setIntro(rs.getString("intro"));
					storeinfo.setTablecount(rs.getInt("tablecount"));
					//List에 추가
					list.add(storeinfo);
				}
				
				rs.close();
			}catch(Exception e) {
				System.err.println("전체 데이터 가져오기 실패");
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			close();
			return list;
		}
		
		public StoreInfo getStoreInfo(int num){
			//하나의 행이 리턴되는 경우는 인스턴스를 데이터를 가져왔을 때 생성
			StoreInfo storeinfo = null;
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
					storeinfo = new StoreInfo();
					
					storeinfo.setNum(rs.getInt("num"));
					storeinfo.setName(rs.getString("name"));
					storeinfo.setBm(rs.getString("bm"));
					storeinfo.setAddr(rs.getString("addr"));
					storeinfo.setStorenum(rs.getString("storenum"));
					storeinfo.setIntro(rs.getString("intro"));
					storeinfo.setTablecount(rs.getInt("tablecount"));
				}
				
				
			}catch(Exception e) {
				System.out.println("상세보기 실패");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			close();
			return storeinfo;
		}
		
		public int updateCovid(StoreInfo storeinfo) {
			
			int result = -1;
			connect();
			try {
				pstmt = con.prepareStatement(
					"update storeinfo set name=?,bm=?,addr=?,storenum=?,intro=?,tablecount=? where num=?");
				//데이터 바인딩
				pstmt.setString(1, storeinfo.getName());
				pstmt.setString(2, storeinfo.getBm());
				pstmt.setString(3, storeinfo.getAddr());
				pstmt.setString(4, storeinfo.getStorenum());
				pstmt.setString(5, storeinfo.getIntro());
				pstmt.setInt(6, storeinfo.getTablecount());
				pstmt.setInt(7, storeinfo.getNum());

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
		
}
