package basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class J_FileEncodingTest {
	
	public static void main(String[] args) throws IOException {
		//키보드로 입력한 내용을 파일로 저장하는데
		//out_utf8.txt 파일은 'utf-8' 인코딩 방식으로
		//out_ansi.txt 파일은 'ms949' 인코딩 방식으로 저장한다
		
		//InputStreamReader 바이트 입력 스트림에 연결되어 
		//문자 입력 스트림인 Reader로 변환시키는 보조스트림
		InputStreamReader isr = new InputStreamReader(System.in);
		
		//파일 출력용
		FileOutputStream fos1 = new FileOutputStream("/Users/pjk/Documents/IO_test/dd.txt");
		FileOutputStream fos2 = new FileOutputStream("/Users/pjk/Documents/IO_test/hello.txt");
		
		//OutputStreamWriter 은 바이트출력 스트림에 연결되어
		//문자 출력 스트림인 Writer로 변환시키는 보조스트림
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "utf-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "ms949");
		
		int c;
		System.out.println("아무거나 입력하세요");
		
		while((c=isr.read()) != -1) {
			osw1.write(c);
			osw2.write(c);
		}
		
		System.out.println("작업 완료!!");
		
		isr.close();
		osw1.close();
		osw2.close();
	}
}
