package dateparsing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class CSVRead {

	public static void main(String[] args) {
		//CSV 파일 읽기
		try(CsvBeanReader reader = new CsvBeanReader(
				new BufferedReader(
						new InputStreamReader(
								new FileInputStream("C:\\Users\\30409\\Desktop\\2018년 관서별 청소년 5대범죄 현황.csv"))),CsvPreference.STANDARD_PREFERENCE) ){
			
			//파일 읽기 객체가 생성되었는지 확인
			//System.out.println(reader);
			
			
			//첫번째 줄이 헤더인지 여부를 설정
			//첫번째 줄은 읽지 않음
			
			reader.getHeader(true);
			
			//DTO 클래스의 변수들을 순서대로 문자열 배열로 생성
			//이 부분을 가지고 한 줄의 데이터를 읽었을 때
			//변수에 대입
			String [] headers = {"classification","salin","gangdo","ganggan","juldo","pokhang"};
			
			CellProcessor [] processors = new CellProcessor[] {
					new Optional(),
					new ParseInt(new Optional()),
					new ParseInt(new Optional()),
					new ParseInt(new Optional()),
					new ParseInt(new Optional()),
					new ParseInt(new Optional())
		
			};
			
			//읽은 데이터를 저장할 DTO의 List를 생성
			List<Crime> list = new ArrayList<Crime>();
			
			while(true) {
				//데이터를 1개 읽음
				Crime crime = reader.read(Crime.class, headers, processors);
				
				//더 이상 읽지 못하면 종료
				if(crime == null ) {
					break;
				}
				list.add(crime);
			}
			for(Crime crime : list) {
				System.out.println(crime.getClassification() +" : " + crime.getJuldo());
			}
		}catch(Exception e) {
			System.out.println("CVS 읽기 오류");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
