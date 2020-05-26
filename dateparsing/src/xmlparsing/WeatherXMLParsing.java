package xmlparsing;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class WeatherXMLParsing {

	public static void main(String[] args) {
		// Anonymous Class를 이용해서
		// Thread 클래스로부터 상속받는 클래스의 인스턴스 생성
		Thread th = new Thread() {
			// 스레드로 수행할 내용
			public void run() {
				// run 메소드 안에서 예외가 발생했을 때
				// return 하도록 만들면 스레드를 중지시킬 수 있습니다.
				try {
					String weatherString = null;
					try {
						String addr = "https://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108";
						URL url = new URL(addr);

						HttpURLConnection con = (HttpURLConnection) url.openConnection();
						// 옵션 설정
						con.setConnectTimeout(30000);
						con.setUseCaches(false);

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
						weatherString = sb.toString();
						br.close();
						con.disconnect();

					} catch (Exception e) {
						System.out.println("데이터 가져오기 실패");
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					// 데이터 확인
					// System.out.println(weatherString);

					List<Map<String, Object>> list = new ArrayList<>();

					if (weatherString != null && weatherString.trim().length() > 0) {

						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = factory.newDocumentBuilder();
						Document document = builder.parse(new ByteArrayInputStream(weatherString.getBytes()));
						Element root = document.getDocumentElement();

						List<String> cities = new ArrayList<>();
						NodeList cityList = root.getElementsByTagName("city");

						for (int i = 0; i < cityList.getLength(); i++) {

							Node node = cityList.item(i);
							Node city = node.getFirstChild();
							cities.add(city.getNodeValue());

						}
						// System.out.println(cities);

						// 날짜, 날씨, 최고온도, 최저온도를 가져오기
						NodeList dateList = root.getElementsByTagName("tmEf");
						NodeList wfList = root.getElementsByTagName("wf");
						NodeList tmxList = root.getElementsByTagName("tmx");
						NodeList tmnList = root.getElementsByTagName("tmn");
						// 날짜, 날씨, 최고온도, 최저온도를 저장할 임시 리스트
						List<String> list1 = new ArrayList<>();
						List<String> list2 = new ArrayList<>();
						List<String> list3 = new ArrayList<>();
						List<String> list4 = new ArrayList<>();

						for (int i = 0; i < dateList.getLength(); i++) {
							// 날짜를 list1 에 저장하기
							Node node = dateList.item(i);
							Node temp = node.getFirstChild();
							list1.add(temp.getNodeValue());
							// 날씨를 list2 에 저장하기
							// wf만 초기데이터가 1개 더 있어서 하나 뒤의 데이터 가져오기
							node = wfList.item(i + 1);
							temp = node.getFirstChild();
							list2.add(temp.getNodeValue());
							// 최고온도를 list3 에 저장하기
							node = tmxList.item(i);
							temp = node.getFirstChild();
							list3.add(temp.getNodeValue());
							// 최저온도를 list4 에 저장하기
							node = tmnList.item(i);
							temp = node.getFirstChild();
							list4.add(temp.getNodeValue());
						}

//						for (int i = 0; i < dateList.getLength(); i++) {
//							System.out.println(list1.get(i)+"\t"+list2.get(i)+"\t"+list3.get(i)+"도\t"+list4.get(i)+"도");
//						}

						// cityList 와 list1, list2, list3, list4
						// 의 데이터를 모아서 하나의 List로 만들기
						// city 1개에 각 데이터가 13개씩 존재

						for (int i = 0; i < cities.size(); i++) {
							String city = cities.get(i);
							// System.out.println(city);
							for (int j = 0; j < 13; j++) {
								String date = list1.get(i * 13 + j);
								String wf = list2.get(i * 13 + j);
								String tmx = list3.get(i * 13 + j);
								String tmn = list4.get(i * 13 + j);
								// System.out.println(date+"\t"+wf+"\t"+tmx+"도\t"+tmn+"도");

								// 맵 생성
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("city", city);
								map.put("date", date);
								map.put("wf", wf);
								map.put("tmx", tmx);
								map.put("tmn", tmn);

								list.add(map);
							}
						}

					} else {
						System.out.println("읽어온 데이터가 없습니다.");

					}
					// System.out.println(list);
					// city의 값이 목포인 경우만 출력
					/*
					 * for (Map<String, Object> map : list) {
					 * if(((String)map.get("city")).equals("목포")) { System.out.println(map); } }
					 */

					// MySQL에 저장
					try {
						// 드라이버 클래스 로드
						Class.forName("com.mysql.cj.jdbc.Driver");
						// System.out.println("드라이버 클래스 로드");

						// 데이터베이스 연결
						Connection con = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/sample?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC&useSSL = false",
								"root", "Gnfnfn21!!");
						// System.out.println(con);

						// SQL 실행
						// list를 순회하면서 데이터를 테이블에 삽입하기
						for (Map<String, Object> map : list) {
							PreparedStatement pstmt = con.prepareStatement(
									"insert into weather(weathercity,weatherdate,weatherwf,weathertmx,weathertmn) values(?,?,?,?,?)");
							pstmt.setString(1, (String) map.get("city"));
							pstmt.setString(2, (String) map.get("date"));
							pstmt.setString(3, (String) map.get("wf"));
							pstmt.setString(4, (String) map.get("tmx"));
							pstmt.setString(5, (String) map.get("tmn"));

							// SQL 실행
							pstmt.executeUpdate();
							
							// 사용한 객체 정리
							pstmt.close();
							//con.close();

						}
					} catch (Exception e) {
						System.out.println("데이터 저장 실패");
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				} catch (Exception e) {

					return;
				}
			}
		};
		// 스레드 시작
		th.start();

	}

}
