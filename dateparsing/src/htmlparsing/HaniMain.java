package htmlparsing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HaniMain {

	public static void main(String[] args) {
		String html = null;
		
		try {
			//URL 만들기 - 파라미터에 한글이 있으면 파라미터를 인코딩
			//파라미터는 ? 다음에 나오는 문자열
			String addr = "http://www.hani.co.kr/";
			URL url = new URL(addr);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// 옵션 설정
			con.setConnectTimeout(30000);
			con.setUseCaches(true);
			
			//스트림을 사용해서 문자열을 읽어오는 부분
			//읽었는데 한글이 깨지면 InputStreamReader
			//생성자에 euc-kr 추가
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
			
			if(html != null && html.trim().length() >0 ) {
				//문서 구조 가져오기
				Document document = Jsoup.parse(html);
				
				//선택자 이용해서 가져오기
				Elements elements = document.select("#main-top > div.main-top > div.main-top-article > h4 > a");
				//선택자를 이용한 것은 반복문을 수행
				for(int i = 0; i<elements.size(); i= i+1) {
					Element element = elements.get(i);
					//태그 안의 내용을 가져오기
					System.out.println(element.text());
					//시작 태그 안의 href 라는 속성의 값을 가져오기
					System.out.println(element.attr("href"));
				}
				
				
				
			}else {
				System.out.println("읽어온 데이터가 없음");
			}
		}catch(Exception e) {
			System.out.println("html 다운로드 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		//System.out.println(html);
	}

}
