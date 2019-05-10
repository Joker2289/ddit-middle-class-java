package basic;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
/*
 		# 네트워킹(NetWorking)
		- 두대 이상의 컴퓨터를 케이블로 연결하여 네트워크(NetWork)를 구성하는것
		
		# InetAddress 클래스 		: IP주소를 다루기 위한 클래스
		- getByName("주소")		: 입력한 주소값의 hostname / IPaddress 를 반환
		- getLocalHost(); 		: 자신의 컴퓨터의 hostname / IPaddress 를 반환
			- getHostName() 	: hostname 값만 반환
			- getHostAddress()	: hostaddress 값만 반환
			
		- getAllByName("주소")  	: 모든 ip 주소를 배열형태로 반환			
 */
public class A_InetAddress {
	public static void main(String[] args) throws UnknownHostException{
		//InetAddress 클래스 > IP주소를 다루기 위한 클래스
		
		//naver 사이트의 ip정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		System.out.println(naverIp);
		System.out.println("Host Name : " + naverIp.getHostName());
		System.out.println("Host Address : " + naverIp.getHostAddress());
		System.out.println();
		
		//자기 자신 컴퓨터의 IP주소 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name : " + localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address : " + localIp.getHostAddress());
		System.out.println();
		
		//ip주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		
		for(InetAddress nIp : naverIps) {
			System.out.println(nIp.toString() );
		}
		System.out.println(naverIps[0].toString());
		
	}
}
