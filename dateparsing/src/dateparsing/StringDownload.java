package dateparsing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StringDownload {

	public static void main(String[] args) {
		try {
		
		String addr = "https://www.naver.com";
		URL url = new URL(addr);
		
		//연결
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		
		//스트림 만들기
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		
		
		//줄 단위로 읽기
		while(true) {
			String line = br.readLine();
			if(line == null) {
				break;
			}
			
			System.out.println(line);
		}
		
		br.close();
		con.disconnect();
		}catch(Exception e) {}
	}

}
