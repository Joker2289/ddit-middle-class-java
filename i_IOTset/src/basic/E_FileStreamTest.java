package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class E_FileStreamTest {
	
	public static void main(String[] args) {
		// FileInputStream 객체를 이용한 파일 내용 읽기
		FileInputStream fin = null; //Stream 선언
		
		try {
			//방법1(파일정보를 문자열로 지정하기)
			//fin = new FileInputStream("/Users/pjk/Documents/IO_test/sample.txt"); //생성
			
			//방법2(파일정보를 File객체를 이용하여 지정하기)
			File file = new File("/Users/pjk/Documents/IO_test/sample.txt");
			fin = new FileInputStream(file); //생성
			
			int c; 	//읽어온 데이터를 저장할 변수
			
			//읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미이다
			while((c=fin.read())!=-1) {
				//읽어온 자료 출력
				System.out.print((char)c);
			}
			
			fin.close(); //작업 완료 후 Stream	 닫기
		} catch (FileNotFoundException e) {
			System.out.println("지정한 파일이 없습니다");
		} catch (IOException e) {
			System.out.println("알수 없는 입출력 오류 입니다");
		}
	}
}
