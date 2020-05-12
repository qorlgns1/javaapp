package javastudy0512;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class OracleConnectPractice {

	public static void main(String[] args) {
		//오라클 드라이버 클래스 로드
		//예외가 발생하면 오라클 드라이버 파일을 프로젝트의
		//build path에 추가하지 않았거나
		//드라이버 클래스 이름이 틀린 것임
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 클래스 로드 성공");
			//데이터베이스 접속
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.200:1521:xe",
					"user09","user09");
			//데이터 삽입
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO ITEM(NUM,NAME,MANUFACTURE,PRICE,RECEIVEDATE) "
			+ "VALUES(?,?,?,?,?)");
			//물음표에 데이터 바인딩
			pstmt.setInt(1, 5);
			pstmt.setString(2, "세발낙지");
			pstmt.setString(3, "무안");
			pstmt.setInt(4, 5000);
			//오늘 날짜를 갖는 java.sql.Date 인스턴스 만들기
			Date date = new Date(System.currentTimeMillis());
			pstmt.setDate(5, date);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("삽입 성공");
			}else {
				System.out.println("삽입 실패");
			}
			//System.out.println("접속 성공");
			//데이터베이스 접속 해제
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.err.println("드라이버 클래스 로드 실패");
			System.err.println(e.getMessage());
		}
		

	}

}
