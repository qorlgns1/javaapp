package javastudy0521;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IPTraffic {

	public static void main(String[] args) {
		
		// 텍스트 파일을 읽을 수 있는 스트림을 생성
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./log (1).txt")))){
			
			//저장할 맵을 생성
			Map<String, Object>map = new HashMap<>();
			
			//데이터를 한 줄 씩 읽기
			while(true) {
				//한 줄을 가져오기
				String line = br.readLine();
				//가져온 것이 없으면 종료
				if(line == null) {
					break;
				}
				
				//줄 단위 출력
				//System.out.println(line);
				
				//공백단위로 분할
				String [] ar = line.split(" ");
				//IP 와 Traffic 을 출력
				//System.out.println(ar[0]+ " : " + ar[9]);
				
				//예외처리를 한 이유는 잘못된 데이터가 있어도 계속 진행하기 위해서
				try {
					String ip = ar[0];
					//문자열을 정수로 변경해서 traffic에 저장
					int traffic = Integer.parseInt(ar[9]);
					
					//처음 본 IP이면 traffic을 저장
					if(map.get(ip)==null) {
						map.put(ip,traffic);
						
					}else {
						//데이터가 있는 경우는 가져오기
						int sum = (Integer)map.get("ip");
						//기존값에 새로운 값을 추가
						sum = sum + traffic;
						//데이터를 수정
						//ip가 기존에 있는 값이면 덮어씌운다.
						map.put(ip, sum);
					}
				}catch(Exception e) {
					
				}
			}
			
			//Map의 데이터 전부를 출력
			Set<String>keySet = map.keySet();
			for(String key : keySet) {
				System.out.println(key + " : " + map.get(key));
			}
		}catch(Exception e) {
			System.out.println("읽기 에러");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
