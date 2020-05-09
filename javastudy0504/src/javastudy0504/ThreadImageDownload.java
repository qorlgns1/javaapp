package javastudy0504;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

class ThreadEx extends Thread{
	public void run() {
		try {
			// 다운로드 받을 URL
			URL url = new URL("http://img.etoday.co.kr/pto_db/2017/10/600/20171031045658_1144679_714_470.JPG");
			// 연결 객체 생성 하고 옵션 설정
			// 연결 객체 만들기
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// 연결옵션 만들기
			con.setConnectTimeout(30000);
			con.setUseCaches(false);
			
			//바이트 단위로 데이터를 전송받을 스트림을 생성
			BufferedInputStream bis = 
					new BufferedInputStream(
							con.getInputStream());
			
			//바이트 배열을 저장할 파일 출력 스트림을 생성
			PrintStream ps = new PrintStream("./ImageDownloadClass.jpg");
			
			while(true) {
				//데이터를 저장할 바이트 배열 생성
				byte[] b = new byte[512];
				//데이터를 읽어서 b에 저장하고 읽은 개수를
				//len에 저장
				int len = bis.read(b);
				//읽은 데이터가 없으면 종료
				if(len<=0) {
					break;
				}
				//b 배열에서 읽은 개수만큼만 기록
				ps.write(b,0,len);
			}
			//버퍼를 이용하기 때문에 모아서 출력을 합니다.
			//종료할 때는 혹시 버퍼에 내용이 남아있을수 있기 때문에
			//flush를 호출해서 남은 내용을 출력
			ps.flush();
			//정리작업
			ps.close();
			bis.close();
			con.disconnect();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
			
		}

	}
}
public class ThreadImageDownload {

	public static void main(String[] args) {
		//ImageDownloadClass + 파일 존재 여부 확인 + Thread
		//----------------------------------------
		//파일의 존재 여부 확인
		File f = new File("./ImageDownloadClass.jpg");
		if(f.exists() == true) {
			System.out.println("파일이 이미 존재합니다.");
			System.exit(0);
		}else {
			System.out.println("파일이 존재하지 않아서" + "다운로드를 받습니다");
			//스레드를 이용해서 다운로드
			ThreadEx th = new ThreadEx();
			th.start();
		}
		
		
		
	}

}
