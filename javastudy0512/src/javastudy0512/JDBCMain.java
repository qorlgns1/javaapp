package javastudy0512;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCMain {

	public static void main(String[] args) {
		try {
			//1. 데이터베이스 드라이버 클래스 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//2. 데이터베이스 접속 인스턴스 생성
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.200:1521:xe","user09","user09");
			
			//3. SQL 실행 객체 생성
			PreparedStatement pstmt = con.prepareStatement(
					"delete from item where num = ?");
			
			
			pstmt.setInt(1, 2);
			
			//4. SQL을 실행하고 결과를 result에 저장
			//select 구문일 때는 executeQuery로 실행하고 ResultSet으로 받음
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("임수정1 성공");
			}else if(result == 0) {
				System.out.println("조건에 맞는 데이터가 없음");
			}else {
				System.out.println("임수정 실패");
			}
			
			// 6.사용한 인스턴스 연결 해제
			pstmt.close();
			con.close();
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
