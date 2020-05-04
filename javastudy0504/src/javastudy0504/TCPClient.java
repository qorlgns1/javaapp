package javastudy0504;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

	public static void main(String[] args) {
		try {
			//소켓 만들기
			//127.0.0.1 이라고 해도 된다.
			InetAddress addr = InetAddress.getByName("192.168.0.112");
			Scanner sc = new Scanner(System.in);
			while(true) {
				
				//소켓연결
				Socket socket = new Socket(addr, 9999);
				//전송할 메시지 입력
				System.out.print("메시지 : ");
				String msg = sc.nextLine();
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				pw.println(msg);
				pw.flush();
				//pw.close();
				
				//메시지 읽기
				BufferedReader br = 
						new BufferedReader(
								new InputStreamReader(
										socket.getInputStream()));
				String receiveMsg = br.readLine();
				System.out.println(receiveMsg);
				br.close();
				socket.close();
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
