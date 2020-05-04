package javastudy0504;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class ThreadEx1 extends Thread{
	public void run() {
		try {
			String addr = 
				"https://dapi.kakao.com/v2/search/web?query=";
			addr = addr + URLEncoder.encode("이효리", "utf-8");
			URL url = new URL(addr);
			
			HttpURLConnection con = 
				(HttpURLConnection)url.openConnection();
			con.setConnectTimeout(30000);
			con.setUseCaches(false);
			//권한 설정
			con.addRequestProperty("Authorization",
					"KakaoAK e7c214edc6b2ef45ec4f7e393f70ee41");
			
			BufferedReader br = 
				new BufferedReader(
						new InputStreamReader(
								con.getInputStream()));
			
			String html = "";
			while(true) {
				String line  =  br.readLine();
				if(line == null) {
					break;
				}
				html = html + line + "\n";
			}
			System.out.println(html);
			
			br.close();
			con.disconnect();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}

public class KakaoAPI {

	public static void main(String[] args) {
		ThreadEx1 th = new ThreadEx1();
		th.start();

	}

}
