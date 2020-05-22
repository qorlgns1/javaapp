package javastudy0522;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

public class CSVMain {

	public static void main(String[] args) {
		try (CsvBeanReader reader = new CsvBeanReader(
				new BufferedReader(
						new InputStreamReader(
								new FileInputStream("./volleyball.csv"))),
											CsvPreference.STANDARD_PREFERENCE)){
			
			//헤더 만들기
			//첫번째 행이 열 제목이라고 설정하고 그 내용을
			//headers에 저장
			String [] headers = reader.getHeader(true);
			
			
			/*
			//확인
			for(String title : headers) {
				System.out.println(title);
			}
			*/
			CellProcessor [] processors = new CellProcessor[] {
					new NotNull(),
					new ParseDate("yyyy-mm-dd"),
					new NotNull(),
					new Optional(),
					new ParseInt(new NotNull())
			};
			
			//데이터를 저장할 List 생성
			List<Player> list = new ArrayList<Player>();
			//순회하면서 데이터를 읽어서 list에 추가
			while(true) {
				//1줄 읽기
				Player player = reader.read(Player.class, headers, processors);
				
				//읽은 데이터가 없으면 종료
				if(player == null) {
					break;
				}
				//읽은 데이터가 있으면 List에 저장
				list.add(player);
				
				
				
			}
			
			for(Player pl : list) {
				System.out.println(pl);
			}
			
			
		}catch(Exception e) {
			System.out.println("csv 읽기 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
