package javastudy0504;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastServer {

	public static void main(String[] args) {
		try {
			MulticastSocket ms = new MulticastSocket(9999);
			//그룹에 참여
			ms.joinGroup(InetAddress.getByName("FF01:0:0:0:0:0:0:175"));
			System.out.println("대기중....");
			while(true) {
				byte[] data = new byte[1024];
				DatagramPacket dp = new DatagramPacket(data, 1024);
				ms.receive(dp);
				//전송받는 메시지 출력
				String msg = new String(data);
				System.out.println(msg);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
