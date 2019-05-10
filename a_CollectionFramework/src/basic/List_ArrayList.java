package basic;

import java.util.ArrayList;
import java.util.Iterator;

public class List_ArrayList {

	public static void main(String[] args) {
		// ArrayList는 기본적인 사용법이 Vector와 같다 > 둘다 list인터페이스를 상속받아서
		// add(값); 		> 데이터 추가
		// add(i, 값); 	> i인데스에 데이터 끼워넣기
		// size(); 		> 데이터 개수
		// get(i);		> i인덱스의 데이터값 반환
		// set(i, 값); 	> i인덱스의 데이터값 변경(변경 전 값을 반환)
		// remove(i);	> i인덱스의 값을 삭제(삭제된 값을 반환)
		// remove(값); 	> 입력값과 같은 값을 찾아 삭제(값의 존재여부 true / false 반환)
		
		// contains(비교객체); 	> 리스트에 비교객체가 있으면 true / 없으면 false
		// indexof(비교객체);		> 리스트에서 '비교객체'를 찾아 '비교객체'가 있는 index값을 반환 / 없으면 -1 반환
		// toArray();		 	> 리스트를 배열로 변환 (기본적으로 Object타입) 
		// 일반자료형으로 변환 하는 방법
		// String[] strArr2 = list2.toArray(new String[0]);
		
		ArrayList list1 = new ArrayList();
		
		// add() 데이터 추가
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k');
		list1.add(true);
		list1.add(12.34);
		System.out.println("ArrayList의 API 기능들");
		System.out.println("list1 = Object 타입");
		System.out.println();
		
		// size() 데이터 개수
		System.out.println("size() 데이터 개수");
		System.out.println("size > " + list1.size());
		System.out.println("list1 > " + list1);
		System.out.println("-----------------------------------------");
		
		// get으로 데이터 꺼내오기
		System.out.println("get() 데이터 반환");
		System.out.println("1번째 값 > " + list1.get(1));
		System.out.println("-----------------------------------------");
		
		// add() 데이터 끼워넣기
		System.out.println("add() 데이터 끼워넣기");
		list1.add(0, "zzz");
		System.out.println("0번째에 zzz > " + list1);
		System.out.println("-----------------------------------------");
		
		// set() 데이터 변경
		System.out.println("set() 데이터 변경");
		String temp = (String) list1.set(0, "YYY");
		System.out.println("0번째에 YYY > " + list1);
		System.out.println("변경전 데이터 > " + temp);
		System.out.println("-----------------------------------------");
		
		// remove() 데이터삭제
		System.out.println("remove() 데이터 삭제");
		System.out.println("0번째 삭제");
		temp = (String) list1.remove(0);
		System.out.println("삭제 후 > " + list1);
		System.out.println("삭제된 데이터 > " + temp);
		System.out.println("-----------------------------------------");
		System.out.println("bbb 삭제");
		boolean bl = (boolean)list1.remove("bbb");
		System.out.println("삭제 후 > " + list1);
		System.out.println("존재여부 > " + bl);
		System.out.println("-----------------------------------------");
		
		
		// 제네릭을 지정하여 선언할 수 있다.
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		
		System.out.println("ArrayList 출력방법");
		System.out.println("list2 타입 = String");
		System.out.println("Geceric을 지정하여 선언할 수 있다");
		System.out.println();
		
		
		System.out.println("for문 이용");
		for(int i=0; i<list2.size(); i++){
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println("-----------------------------------------");
		System.out.println("향상된 for문 이용");
		for(String s : list2){
			System.out.println(s);
		}
		System.out.println("-----------------------------------------");
		System.out.println("iterator 이용");
		Iterator<String> s = list2.iterator();
		while(s.hasNext()) {
			System.out.println(s.next());
		}
		System.out.println("-----------------------------------------");
		
		
		// contains(비교객체); > 리스트에 '비교객체'가 있으면 true / 없으면 false
		System.out.println("contains() 값 포함 여부");
		System.out.println("DDD 포함 여부 > " + list2.contains("DDD"));  // true
		System.out.println("ZZZ 포함 여부 > " + list2.contains("ZZZ"));  // false
		System.out.println("-----------------------------------------");
		
		// indexOf(비교객체); > 리스트에서 '비교객체'를 찾아 '비교객체'가 있는 index값을 반환한다 / 리스트에 '비교객체'가 없으면 -1을 반환한다.
		System.out.println("indexof() 비교값 포함된 인덱스 반환");
		System.out.println("DDD의 index값 > " + list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 > " + list2.indexOf("ZZZ"));
		System.out.println("-----------------------------------------");
		
		// toArray() > 리스트 안의 데이터들을 배열로 변환하여 반환한다 (기본적으로 Object형 배열로 변환한다)
		System.out.println("toArray() 리스트를 배열로 변환");
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수 > " + strArr.length);
		
		// toArray()에서 캐스팅은 되지 않는다.
		//String[] strArr2 = (String[]) list2.toArray();
		
		// 리스트의 제네릭 타입에 맞는 자료형의 배열로 변환하는 방법
		// 제네릭타입의 0개짜리 배열을 생성해서 매개변수로 넣어준다.
		// 형식) toArray(new 제네릭타입[0])
		String[] strArr2 = list2.toArray(new String[0]);
		System.out.println("strArr2의 개수 > " + strArr2.length);
		System.out.println("향상된 for문 출력");
		for(String ss : strArr2){
			System.out.println(ss);
		}
		System.out.println("for문 출력");
		for(int i=0; i<strArr2.length; i++){
			System.out.println(strArr2[i]);
		}
		
		
	}

}
















