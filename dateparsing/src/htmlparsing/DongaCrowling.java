package htmlparsing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DongaCrowling {

	public static void main(String[] args) {
		String html = null;

		try {
			// URL 만들기 - 파라미터에 한글이 있으면 파라미터를 인코딩
			// 파라미터는 ? 다음에 나오는 문자열
			String query = "에이치엘비";
			//파라미터 인코딩
			query = URLEncoder.encode(query, "utf-8");
			
			String addr = "https://www.donga.com/news/search?p=1&query="+query+"&check_news=1&more=1&sorting=1&search_date=1&v1=&v2=&range=1";
			URL url = new URL(addr);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// 옵션 설정
			con.setConnectTimeout(30000);
			con.setUseCaches(true);

			// 스트림을 사용해서 문자열을 읽어오는 부분
			// 읽었는데 한글이 깨지면 InputStreamReader
			// 생성자에
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			StringBuilder sb = new StringBuilder();
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				sb.append(line + "\n");
			}
			// 읽은 내용을 String으로 변환
			html = sb.toString();
			br.close();
			con.disconnect();

		} catch (Exception e) {
			System.out.println("html 다운로드 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		System.out.println(html);
	}

}
