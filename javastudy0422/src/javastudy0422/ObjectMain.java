package javastudy0422;

public class ObjectMain {

	public static void main(String[] args) {
		Data data1 = new Data();
		data1.setNum(1);
		int [] ar = {10,20,30};
		data1.setAr(ar);
		
		Data data2 = new Data(1, ar);
		//System.out.println(data2.getNum());
		//System.out.println(data2.getAr());
		
		//System.out.println(data2.getData());
		
		//출력하는 메소드에 인스턴스를 대입하면
		//toString()을 호출해서 결과를 출력
		System.out.println(data1);
		System.out.println(data2);
		
		//data1 과 data2는 내용이 같음
		//2개의 데이터는 내용은 같지만 각각 new를 호출했기 때문에
		//별도의 메모리 영역을 할당받았습니다.
		//===로 비교하면 메모리 영역의 위치를 비교하므로 false
		System.out.println(data1==data2);
		System.out.println(data1.hashCode());
		System.out.println(data2.hashCode());
		System.out.println(data1.equals(data2));
		
		//data1이 저장하고 있는 참조를 data3에 복사
		Data data3 = data1;
		//data3이 자신의 내용을 변경하면 data1의 것도 변경
		data3.setNum(2);
		System.out.println(data1.getNum());
		
		//데이터를 복제해서 대입
		Data data4 = data1.clone();
		System.out.println("===복제해서 내용 같은 ===");
		System.out.println(data1);
		System.out.println(data4);
		
		//data4의 내용을 변경해도 data1이 변경되지 않음
		System.out.println("===변경해도 영향없음? ===");
		data4.setNum(3);
		data4.getAr()[1] = 10000;
		System.out.println(data1);
		System.out.println(data4);
		
		String str = "Hello Object";
		System.out.println(str.toUpperCase());
		Object obj = str;
		//사용을 할 때는 원래의 자료형으로 강제 형 변환해서 사용
		System.out.println(((String) obj).toUpperCase());
		//끝
	}
}



