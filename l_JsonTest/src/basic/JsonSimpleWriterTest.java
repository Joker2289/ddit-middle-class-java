package basic;

import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


//json 객체 저장하기
public class JsonSimpleWriterTest {
	public static void main(String[] args) throws Exception{
		JSONObject jsonObj = new JSONObject();
		
		jsonObj.put("name", "홍길동");
		jsonObj.put("job", "학생");
		jsonObj.put("age", "30");
		jsonObj.put("addr", "대전시 중구 대흥동");
		
		JSONArray singerList = new JSONArray();
		singerList.add("이문세");
		singerList.add("젝스키스");
		singerList.add("신승훈");
		
		jsonObj.put("singerList", singerList);
		
		FileWriter fw = new FileWriter("/Users/pjk/Documents/IO_test/JsonFile.txt");
		fw.write(jsonObj.toString());
		fw.flush();
		fw.close();
		
		System.out.println("JSON객체 내용 출력 : " + jsonObj); 
		
	}	
}
