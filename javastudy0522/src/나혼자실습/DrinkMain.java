package 나혼자실습;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import javastudy0522.Player;

public class DrinkMain {

	public static void main(String[] args) {
				//현재 디렉토리에 있는 volleyball.csv 파일을
				//읽을 수 있는 CsvReader 인스턴스 생성
			try (CsvBeanReader reader = new CsvBeanReader(
					new BufferedReader(
							new InputStreamReader(
									new FileInputStream("C:\\Users\\30409\\Downloads\\경찰청_음주운전적발기록_20200518.csv"))),
												CsvPreference.STANDARD_PREFERENCE)){
				
				
				//헤더 만들기
				//첫번째 행이 열 제목이라고 설정하고 그 내용을
				//headers에 저장
				reader.getHeader(true);
				
				String [] headers = {"num","gender","detection","age","alcohol","date","station"};
								
				
				/*
				//확인
				for(String title : headers) {
					System.out.println(title);
				}
				*/
				//연번,성별,적발횟수,나이,알콜농도,측정일시,관할경찰서
				
				CellProcessor [] processors = new CellProcessor[] {
						new ParseInt(new NotNull()),
						new NotNull(),
						new NotNull(),
						new NotNull(),
						new NotNull(),
						new ParseDate("yyyy-mm-dd"),
						new NotNull()
				};
				
				//데이터를 저장할 List 생성
				List<Drink> list = new ArrayList<Drink>();
				//순회하면서 데이터를 읽어서 list에 추가
				while(true) {
					//1줄 읽기
					Drink drink = reader.read(Drink.class, headers, processors);
					
					//읽은 데이터가 없으면 종료
					if(drink == null) {
						break;
					}
					//읽은 데이터가 있으면 List에 저장
					list.add(drink);
	
					
				}
				
				for(Drink drink : list) {
					System.out.println("음주운전자 성별 : "+ drink.getGender() +"\t"+ "알콜 농도: "+drink.getAlcohol());
				}
					
			}catch(Exception e) {
				System.out.println("csv 읽기 실패");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

	}

}
