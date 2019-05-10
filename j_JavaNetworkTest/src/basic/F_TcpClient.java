package basic;

import java.net.Socket;

public class F_TcpClient {
	public static void main(String[] args) throws Exception{
		Socket socket = new Socket("127.0.0.1", 7777);
		System.out.println("서버에 연결되었습니다");
		
		F_Sender sender = new F_Sender(socket);
		F_Receiver receiver = new F_Receiver(socket);
		
		sender.start();
		receiver.start();
	}
}
