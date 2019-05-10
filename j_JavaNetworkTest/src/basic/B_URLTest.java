package basic;

import java.io.IOException;
/*
		# URI = URL + URN
		# URL 클래스 > 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 관리하는 클래스
 */
import java.net.URL;

public class B_URLTest {
	public static void main(String[] args) throws Exception{
		
		URL url = new URL("http", "naver.com", 80, "webtoon/detail.nhn?titleId=703846&no=34&weekday=tue");
		//전체주소 : http://naver.com/webtoon/detail.nhn?titleId=703846&no=34&weekday=tue
		
		
		System.out.println("protocol : " + url.getProtocol());
		System.out.println("host : " + url.getHost());
		System.out.println("file : " + url.getFile());
		System.out.println("query : " + url.getQuery());
		System.out.println("path : " + url.getPath());
		System.out.println("port : " + url.getPort());
		System.out.println("ref : " + url.getRef());
		System.out.println();
		
		System.out.println(url.toExternalForm());
		System.out.println(url.toString());
		System.out.println(url.toURI().toString());
	}
}
