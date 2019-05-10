package basic;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
/**
 * 이 클래스는 소켓을 통해서 메시지를 보내는 역할을 담당함
 * @author pjk
 *
 */
public class F_Sender extends Thread {
	Socket socket;
	DataOutputStream dos;
	String name;
	
	public F_Sender(Socket socket) {
		this.socket = socket;
		name = "[" + socket.getInetAddress() + " : " + socket.getPort() + "]";
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		Scanner s = new Scanner(System.in);
		while(dos != null) {
		
			try {
				dos.writeUTF(name + " >>> " + s.nextLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
