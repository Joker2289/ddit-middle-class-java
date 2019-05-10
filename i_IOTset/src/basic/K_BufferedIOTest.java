package basic;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class K_BufferedIOTest {
	
	public static void main(String[] args) {
		FileOutputStream fout = null;
		//입출력의 성능 향상을 위해서 버퍼를 이용하는 보조 스트림
		BufferedOutputStream bout = null;
		
		try {
			fout = new FileOutputStream("/Users/pjk/Documents/IO_test/bufferTest.txt");
			
			//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192 byte(8Kb)로 설정된다
			//버퍼의 크기가 5인 스트림 생성
			bout = new BufferedOutputStream(fout, 5);
			for(int i='1'; i<='9'; i++) {
				bout.write(i);	//버퍼용량인 5까지 남기면 disk에 쏴줌 그리고 다시 채운다
			}
			//허나 위에서 6, 7, 8, 9 4개가 차고 1개가 더찰때까지 기다리기 때문에 flush를 호출하면...
			bout.flush(); //작업을 종료하기 전에 남아있는 데이터를 모두 출력 시킨다
			bout.close();
			System.out.println("작업 끝..");
		} catch(IOException	e) {
			e.printStackTrace();
		}
	}
}
