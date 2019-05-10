package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class D_ByteArrayIOTest {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; //자료를 읽을때 사용할 배열
		
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			// available() > 읽어 올 수 있는 byte수를 반환 + (또는 생략할 수 있는) 나머지 바이트 수를 돌려줌
			while(input.available() > 0) {
//				input.read(temp); 	//temp배열 크기만큼 자료를 읽어와 temp배열에 저장한다
//				output.write(temp); //temp배열의 내용을 출력한다
				
				//실제 읽어온 갯수(byte수)를 반환한다
				int len = input.read(temp);
				System.out.println("len : " + len);
				//temp 배열의 내용중에서 0번째 부터 len 개수만큼 출력한다
				output.write(temp, 0 , len);
				
				System.out.println("temp : " + Arrays.toString(temp));
			}
			
			System.out.println();
			
			//byte를 배열로 변환
			outSrc = output.toByteArray();
			System.out.println("inSrc  : " + Arrays.toString(inSrc));
			System.out.println("outSrc : " + Arrays.toString(outSrc));
			
			input.close();
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
