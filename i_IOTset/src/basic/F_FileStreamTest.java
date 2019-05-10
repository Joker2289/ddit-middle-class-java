package basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class F_FileStreamTest {
	public static void main(String[] args) {
		//파일에 출력하기
		
		FileOutputStream fout = null;
		
		try {
			//출력용 OutputStream 객체 생성
			fout = new FileOutputStream("/Users/pjk/Documents/IO_test/out.txt");
			for(char ch='a'; ch <='z'; ch++) {
				fout.write(ch);
			}
			System.out.println("파일에 쓰기 작업 완료..");
			
			//쓰기작업 완료 후 Stream 닫기
			fout.close();
			
			//저장된 파일의 내용을 읽어와 화면에 출력하기
			FileInputStream fin = new FileInputStream("/Users/pjk/Documents/IO_test/out.txt");
			
			System.out.println("파일 읽기 작업 시작..");
			int c;
			while((c=fin.read())!=-1) {
				System.out.print((char) c);
			}
			System.out.println();
			System.out.println("출력 끝..");
			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
