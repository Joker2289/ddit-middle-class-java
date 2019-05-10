package basic;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonSimpleReaderRest {
	public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("/Users/pjk/Documents/IO_test/JsonFile.txt");
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(fr); //parse = 분석
		
		JSONObject jsonFile = (JSONObject) obj;
		
		String name = (String)jsonFile.get("name");
		String job = (String)jsonFile.get("job");
		String age = (String)jsonFile.get("age");
		String addr = (String)jsonFile.get("addr");
		
		JSONArray list = (JSONArray) jsonFile.get("singerList");
		
		System.out.println("name : " + name);
		System.out.println("job : " + job);
		System.out.println("age : " + age);
		System.out.println("addr : " + addr);
		
		Iterator<String> it = list.iterator();
		
		System.out.println("===========");
		System.out.println("  가수목록");
		System.out.println("===========");
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		
	}
}
