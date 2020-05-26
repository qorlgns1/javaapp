package xmlparsing;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class KahnEconomyRSS {

	public static void main(String[] args) {
		// 다운로드 받을 문자열을 저장할 변수
		String xmlString = null;

		// title 과 link 여러개를 저장할 자료구조를 생성
		// List는 처음 만들 때 인스턴스를 생성해야 합니다.
		// List는 특별한 이변이 없는한 반복문을 돌려야 합니다.
		// List가 null을 가지면 반복문에서 예외가 발생합니다.
		// 출력할 때 예외가 발생하지 않도록 하기 위해서 인스턴스를 항상 생성해야 합니다!
		List<Map<String, Object>> list = new ArrayList<>();
		//파일 경로 생성
		String filepath = "./updatefile.dat";
		//파일이 존재하는 경우에만
		File f = new File(filepath);
		
		//f.exists() -> 파일이 존재한다라는 뜻
		if(f.exists()) {
			//파일이 있으면 파일의 내용을 읽어오기
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
				
				//한 줄을 읽어서 오늘 날짜인지 확인
				String line = br.readLine();
				java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
				
				//오늘 날짜면 프로그램 종료
				if(date.toString().equals(line)) {
					System.out.println("이미 다운로드 받음");
					System.exit(0);
				}
				br.close();
				
			} catch (Exception e) {
				System.out.println("파일 읽기 실패");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		try {
			System.out.println("다운로드");
			//다운로드 받은 날짜를 기록
			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
			//파일에 오늘 날짜를 기록
			PrintWriter pw = new PrintWriter(filepath);
			pw.print(date.toString());
			pw.flush();
			pw.close();
			
			
			// 1. 주소 만들기
			xmlString = "http://www.khan.co.kr/rss/rssdata/economy_news.xml";
			// 필요시 추가사항 - 파라미터가 있다면 인코딩
			// xmlString = URLEncoder.encode(xmlString, "utf-8");
			URL url = new URL(xmlString);

			// 2. 연결 객체 만들기
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			// 옵션 설정
			con.setConnectTimeout(30000);// 최대 연결 시간 설정
			con.setUseCaches(false);// 이전에 받은 데이터 사용 여부

			// 3. 스트림을 만들어서 문자열 읽어오기
			// 확인한 후 한글이 깨지면 수정하기
			// ﻿new BufferedReader(new
			// InputStreamReader(connection.getInputStream(),"인코딩방식"));
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			// 문자열을 임시로 저장할 인스턴스
			StringBuilder sb = new StringBuilder();
			while (true) {
				// 한 줄 읽어오기
				String line = br.readLine();
				// 읽은 데이터가 없으면 종료
				if (line == null) {
					break;
				}
				sb.append(line + "\n");
			}

			// 4. 정리하기

			// 연결 종료
			br.close();
			con.disconnect();
			// 읽은 내용을 String으로 변환
			xmlString = sb.toString();

		} catch (Exception e) {
			System.out.println("문자열 다운로드 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		// System.out.println(xmlString);

		// 다운로드 받을 문자열이 있을 때만 파싱
		if (xmlString != null && xmlString.trim().length() != 0) {
			// 루트 태그를 찾기
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));
				Element root = document.getDocumentElement();

				// 원하는 태그를 추출 : title 과 link 태그의 값을 가져오기
				NodeList titles = root.getElementsByTagName("title");
				NodeList links = root.getElementsByTagName("link");
				// 반복문을 돌려서 하나씩 순회
				for (int i = 1; i < titles.getLength(); i++) {
					Map<String, Object> map = new HashMap<>();

					// title 저장
					Node node = titles.item(i);
					Node title = node.getFirstChild();
					map.put("title", title.getNodeValue());

					node = links.item(i);
					title = node.getFirstChild();
					map.put("link", title.getNodeValue());

					// list에 map을 추가
					list.add(map);
				}

				for (Map<String, Object> map : list) {
					System.out.println(map.get("title") + " : " + map.get("link"));
				}
			} catch (Exception e) {
				System.out.println("xml 파싱 실패");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println("다운로드 받은 데이터 없음");
			System.exit(0);
		}

	}

}
