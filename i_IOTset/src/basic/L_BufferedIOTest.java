package basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class L_BufferedIOTest {
	
	public static void main(String[] args) {
		//문자기반의 Buffered 스트림 사용 예제
		try {
			//이클립스에서 만든 자바프로그램이 실행되는 기본위치는 해당 '프로젝트폴더'이다
			FileReader fr = new FileReader("./src/basic/K_BufferedIOtest.java");
			//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192 byte(8Kb)로 설정
			BufferedReader br = new BufferedReader(fr);
			
			String temp = "";
			for(int i=1; (temp=br.readLine()) != null; i++) {
				System.out.printf("%4d : %s\n", i, temp);
			}
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
