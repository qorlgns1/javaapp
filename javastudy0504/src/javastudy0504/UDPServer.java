package javastudy0504;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	public static void main(String[] args) {
		try {
			//UDP 받는 소켓 생성
			DatagramSocket ds = new DatagramSocket(9001);
			while(true) {
				System.out.println("서버 대기 중...");
				byte[] data = new byte[1024];
/*방법2*/			DatagramPacket dp = new DatagramPacket(data, 1024);
//방법 1			ds.receive(new DatagramPacket(data, 1024));
/*방법2*/			ds.receive(dp);
				//보낸 곳의 주소와 내용 출력
				System.out.println("보낸 곳: " + dp.getAddress().getHostAddress());
				//바이트 배열을 문자열로 만들기
				String msg = new String(data);
				System.out.println("보낸 메세지: " + msg);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
