package javastudy0504;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class DaumHTMLDownload {

	public static void main(String[] args) {
		try {
			//접속할 곳의 IP주소를 생성
			InetAddress daum = 
				InetAddress.getByName("www.daum.net");
			//접속하기
			Socket socket = new Socket(daum, 80);
			//메시지 전송
			
			//문자열 전송을 위한 출력 스트림 생성
			//문자열이 아니고 파일인 경우에는
			//PrintStream을 생성
			PrintWriter pw = 
				new PrintWriter(
						socket.getOutputStream());
			//메시지 전송
			pw.println("GET http://www.daum.net");
			pw.flush();
			
			//문자열을 입력받는 스트림 생성
			//파일을 입력받는 스트림은 BufferedInputStream
			BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(
						socket.getInputStream()));
			
			//전송된 메시지 읽기
			String html = "";
			while(true) {
				//한 줄 읽어오기 - 읽은게 없으면 null 리턴
				String line =  br.readLine();
				//읽은게 없으면 반복문 중단
				if(line == null) {
					break;
				}
				//읽은 데이터를 html에 추가
				html = html  + line + "\n";
			}
			//읽은 데이터 확인
			System.out.println(html);
			
			//입력 스트림 닫기
			br.close();
			//출력 스트림 닫기
			pw.close();
			//소켓닫기
			socket.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
