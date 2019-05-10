package basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Map_HashMap {

	/*
	 Map > Key값과 value값을 한 쌍으로 관리하는 객체
	  	 > key값을 중복을 허용하지 않고 순서가 없다(Set의 특징)
	  	 > value값은 중복을 허용한다
	 */
	
	public static void main(String[] args) {
		
		HashMap<String, String> map = new HashMap<>();
		
		//자료추가 > put(key, value);
		map.put("name",	"박정권");
		map.put("addr",	"대전");
		map.put("tel",	"010-4433-2289");
		
		System.out.println("map > " + map);
		
		//자료수정 > 데이터를 저장할 때 key값이 같으면 나중에 입력한 value값으로 덮어씀
		//		 >  put(수정할 key, 새로운 value);
		map.put("addr", "서울");
		System.out.println("map > " + map);
		
		//자료삭제 > remove(삭제할 key);
		map.remove("name");
		System.out.println("map > " + map);
		
		//자료읽기 > get(key);
		System.out.println("name > " + map.get("name"));
		System.out.println("------------------------------");
		
		// key값들을 읽어와 자료를 출력하는 방법
		// 방법1 > keySet() 메서드 이용 > Map의 key값들만 읽어와 Set형으로 반환
		Set<String> keySet = map.keySet();
		System.out.println("출력 방법");
		System.out.println();
		System.out.println("1.Iterator");
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("------------------------------");
		
		//방법2 > Set형의 데이터를 '향상된 for문'으로 처리하면 Iterator를 사용안해도돰
		System.out.println("2.향상된 for문");
		for(String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("------------------------------");
		
		//방법3 > value값만 읽어와 출력하기 > values()메서드
		System.out.println("3.values()");
		for(String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("------------------------------");
		
		//방법4 > Map에는 Entry라는 내부class가 만들어져 있다 > 이 Entry클래스는 key와 value라는 멤버변수로 구성되어 있다
		//		> Map에서 이 Entry클래스들을 Set형식으로 저장하여 관리한다
		// 내부class > 캡슐화 (클래스내용을 숨기거나, 접근제어, 내부클래스를 포함한 클래스에서만 메서드를 사용하고 싶을 때
		
		
		//Entry객체 전체를 가져오기(가져온 Entry들은 Set형식으로 되어있다) > entrySet()메서드를 이용하여 가져온다
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		System.out.println("4.entrySet()");
		//가져온 Entry객체들은 순서대로 처리하기 위해서 Iterator객체로 변환
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		System.out.println("Iterator");
		while(entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next();
			System.out.println("key > " + entry.getKey());
			System.out.println("value > " + entry.getValue());
		}
		
		System.out.println("향상된 for문");
		for(Map.Entry<String, String> entry : mapSet) {
			System.out.println("key > " + entry.getKey());
			System.out.println("value > " + entry.getValue());
		}
		
		
	}
}
