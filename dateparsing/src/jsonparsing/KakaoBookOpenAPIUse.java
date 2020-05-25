package jsonparsing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class KakaoBookOpenAPIUse {

	public static void main(String[] args) {
		//1.Web에서 문자열 다운로드 받기
		String jsonString = null;
		String keyword = null;
		try {
			//다운로드 받을 URL을 생성
			Scanner sc = new Scanner(System.in);
			System.out.print("조회할 도서명:");
			keyword = sc.nextLine();
			//영문과 숫자 이외의 데이터가 있을 수 있으므로 인코딩
			keyword = URLEncoder.encode(keyword, "utf-8");
			
			String addr = 
				"https://dapi.kakao.com/v3/search/book?"
				+ "target=title&size=50&query=" + keyword;
			URL url = new URL(addr);
			
			//URL에 연결 객체 생성
			HttpURLConnection con = 
					(HttpURLConnection)url.openConnection();
			con.setConnectTimeout(30000);
			con.setUseCaches(false);
			//헤더 설정
			con.addRequestProperty("Authorization",
				"KakaoAK ae6b2875ee4452804ed4e01890078a7e");
			
			//문자열 읽어오기
			StringBuilder sb = new StringBuilder();
			BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(
						con.getInputStream()));
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				sb.append(line + "\n");
			}
			
			jsonString = sb.toString();
			
			br.close();
			con.disconnect();
			sc.close();
		}catch(Exception e) {
			System.out.println("다운로드 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		//System.out.println(jsonString);
		
		//읽어온 데이터에서 조회된 데이터 건수 찾아오기
		//조회된 데이터 개수를 저장할 변수
		int total_count = -1;
		try {
			//문자열을 JSONObject로 변경
			JSONObject json = 
				new JSONObject(jsonString);
			//meta 속성의 내용을 JSONObject로 가져오기
			JSONObject meta = 
					json.getJSONObject("meta");
			//System.out.println(meta);
			//total_count 속성의 값을 정수로 가져오기
			total_count = meta.getInt("total_count");
			//System.out.println(total_count);
			
		}catch(Exception e) {
			System.out.println("데이터 개수 가져오기 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		//3.페이지 개수 구하기
		//페이지 당 출력할 개수를 설정
		int perPage = 50;
		//페이지 개수를 계산 
		//전체 데이터 개수(total_count)와 
		//페이지 당 출력 개수(perPage)를 이용
		//서버 프로그래밍에서는 출력할 페이지 개수를 설정하는데
		//이용하고 클라이언트 프로그래밍에서는 읽어야 할 페이지 개수를
		//설정하는데 이용
		int pageCnt = (int)
				((double)total_count/perPage + 
				(double)(perPage-1)/perPage);
		//System.out.println(pageCnt);

		
		//데이터를 저장할 변수
		List<Map<String, Object>> list = 
				new ArrayList<>();
		try {
			//반복해서 데이터 가져와서 파싱
			//예외가 발생한 경우 다음 작업을 수행하도록 하는 경우에는
			//반복문 안에 예외 처리 구문을 만들어야 합니다.
			//웹의 데이터를 수집하는 경우 이런 방식으로 처리하지 않으면
			//처음 조회할 때는 있었는데 수집할 때 없어지면
			//예외가 발생해서 데이터를 수집하지 못하는 경우가 발생
			for(int i=1; i<=pageCnt; i=i+1) {
				try {
					//다운로드 받을 URL 만들기 - 파라미터에 한글있는지 확인
					String addr = 
						"https://dapi.kakao.com/v3/search/book?"
						+ "target=title&query=" + keyword 
						+ "&page=" + i + "&size=" + perPage;
					URL url = new URL(addr);
					//연결 객체 만들고 옵션 설정
					//문자열을 읽어서 출력해보고 한글이 깨지는지 확인
					//헤더 설정을 해야 하는지 확인
					HttpURLConnection con = 
						(HttpURLConnection)url.openConnection();
					con.setConnectTimeout(30000);
					con.setUseCaches(false);
					//헤더 설정 - 옵션
					con.addRequestProperty("Authorization",
				"KakaoAK ae6b2875ee4452804ed4e01890078a7e");
					
					//문자열 가져오기
					String imsiString = null;
					StringBuilder sb = new StringBuilder();
					//읽어온 데이터의 한글이 깨질 때 utf-8을 euc-kr로 변경
					BufferedReader br = 
						new BufferedReader(
							new InputStreamReader(
								con.getInputStream(), "utf-8"));
					while(true) {
						String line = br.readLine();
						if(line == null) {
							break;
						}
						sb.append(line + "\n");
					}
					imsiString = sb.toString();
					//System.out.println(imsiString);
					br.close();
					con.disconnect();
					
					//읽어온 문자열을 JSONObject로 변환
					JSONObject obj = new JSONObject(imsiString);
					//documents 속성의 내용을 배열로 가져오기
					JSONArray documents = 
							obj.getJSONArray("documents");
					//배열 순회
					for(int j=0; j<documents.length(); j=j+1 ) {
						//배열을 순회하면서 데이터 가져오기
						JSONObject document = 
							documents.getJSONObject(i);
						//객체에서 데이터 읽어오기
						String title = 
							document.getString("title");
						String urlStr = 
							document.getString("url");
						int price = 
							document.getInt("price");
						//Map으로 위의 데이터 묶기
						Map<String, Object> map = 
							new HashMap<>();
						map.put("title", title);
						map.put("url", urlStr);
						map.put("price", price);
						//list에 추가
						list.add(map);
					}
				}catch(Exception e) {
					System.out.println(
							"실패 하면 다음 페이지도 이동");
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			}
		}catch(Exception e) {
			System.out.println("페이지 별로 가져오기 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		for(Map<String, Object> map : list) {
			System.out.println(map.get("title"));
		}
	}
}







