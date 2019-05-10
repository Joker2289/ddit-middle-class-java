package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesTest {
	// Properties > 오로지 String 값만 올수 있다
	// 외부의 Properties 파일을 읽어와 Properties 객체로 처리하기
	
	public static void main(String[] args) {
		//읽어온 정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		
		//읽어올 파일명을 이용한 File 객체 생성
		File file = new File("res/db.properties");
		
		try {
			// 파일 읽기를 수행할 FileInputStream 객체 생성
			FileInputStream fin = new FileInputStream(file);
			
			//Properties 객체로 파일 내용 읽이
			prop.load(fin); //파일 내용을 읽어와 key 와 value 값으로 분류 한 후, Properties 객체에 담아준다
			
			//읽어온 자료 출력하기
			
			//key값만 읽어와 Enumeration객체로 변환하기
			Enumeration<String> keys = (Enumeration<String>) prop.propertyNames();
			
			//key값 개수 만큼 반복해서 값 출력하기
			
			//keys.hasMoreElements() > 다음 포인터 위치에 자료가 있으면 true, 없으면 false 반환
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key + " --> " + value);
			}
			System.out.println("출력 끝!!");
			
		} catch(IOException e) {
			System.out.println("파일 읽기 실패!!");
		}
	}
}
