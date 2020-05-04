package javastudy0504;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DaumIPMain {

	public static void main(String[] args) {
		try {
			// hostName에는 프로토콜이 포함되지 않습니다.
			// 프로토콜이 들어가면 오류발생
			/* 1번 test */ // InetAddress daum = InetAddress.getByName("www.daum.net");
			/* 2번 test */ InetAddress[] daum = InetAddress.getAllByName("www.naver.com");

			/* 1번 test */ // System.out.println(daum);
			/* 2번 test */ for (InetAddress imsi : daum) {
				System.out.println(imsi);
			}
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
