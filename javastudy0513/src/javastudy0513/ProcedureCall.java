package javastudy0513;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class ProcedureCall {

			public static void main(String[] args) {
				try {
					//1. 드라이버 클래스 로드
					Class.forName("oracle.jdbc.driver.OracleDriver");
					//2. 데이터베이스 접속 인스턴스 생성
					Connection con = DriverManager.getConnection(
							"jdbc:oracle:thin:@192.168.0.200:1521:xe","user09","user09");
					
					//메뉴얼 Commit으로 변경
					con.setAutoCommit(false);
					
					//3. SQL 실행 객체 생성					
					CallableStatement cstmt = con.prepareCall("{call insert_dept_proc(?,?,?)}");
					cstmt.setInt(1, 2);
					cstmt.setString(2, "설계");
					cstmt.setString(3, "독도");
					
					//4. SQL 실행
					int rownum = cstmt.executeUpdate();
					
					//5. 결과 사용
					//삽입은 성공한 경우 1이상의 정수가 리턴됩니다.
					if(rownum > 0) {
						System.out.println("데이터 삽입 성공");
					}else {
						System.out.println("데이터 삽입 실패");
					}
					
					//6. 사용이 끝난 객체 정리
					cstmt.close();
					con.close();
				}catch(Exception e) {
					//예외 메세지를 빨간색으로 출력
					System.err.println(e.getMessage());
				}

			}

		}

