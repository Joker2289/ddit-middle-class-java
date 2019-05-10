package basic;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class H_UdpClient {
	public void start() throws Exception {
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
		
		//데이터가 저장될 공간으로 byte배열을 생성한다
		byte[] msg = new byte[100];
		
		DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddress, 8888);
		DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
		
		datagramSocket.send(outPacket); //DatagramPacket 을 전송한다
		datagramSocket.receive(inPacket); //DatagramPacket 을 수신한다
		
		System.out.println("현재 서버 시간 : " + new String(inPacket.getData()));
		
		datagramSocket.close();
	}
	
	public static void main(String[] args) throws Exception {
		new H_UdpClient().start();
	}
}
