package basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Set_HashSet {

	public static void main(String[] args) {
		/*
		 * List와 Set의 차이점
		 * 
		 * 1.List(배열)
		 * - 입력한 데이터의 순서가 있다
		 * - 중복되는 데이터를 저장할 수 있다
		 * 
		 * 2.Set(집합)
		 * - 입력한 데이터의 순서가 없다
		 * - 중복되는 데이터를 저장할 수 없다
		 */
		
		HashSet hs1 = new HashSet();
		
		//add() 데이터 추가
		hs1.add("red");
		hs1.add(1);
		hs1.add("blue");
		hs1.add(100);
		hs1.add(false);
		System.out.println("HashSet > " + hs1);
		System.out.println("----------------------------------------------------------------");
		
		//Set은 데이터의 중복을 허용하지 않는다 > add하면 중복여부에 따라 true(추가 O) / false반환(추가 X)
		System.out.println("Set 데이터 중복 삽입");
		System.out.println();
		boolean tf = hs1.add("red");
		System.out.println("add(red) > " + tf);
		tf = hs1.add("green");
		System.out.println("add(green) > " + tf);
		System.out.println("HashSet > " + hs1);
		System.out.println("----------------------------------------------------------------");
		
		//Set은 데이터 수정 API가 따로 없음 > 삭제 후 추가
		// 데이터 삭제
		// 1) clear() > 전체 삭제
		// 2) remove(삭제할 값) > 선택 삭제
		System.out.println("데이터 삭제 ");
		System.out.println();
		System.out.println("blue 삭제 > yellow 추가");
		hs1.remove("blue");
		System.out.println("remove > blue");
		System.out.println("Hash Set > " + hs1);
		hs1.add("yellow");
		System.out.println("add > yellow");
		System.out.println("Hash Set > " + hs1);
		hs1.clear();
		System.out.println("clear()");
		System.out.println("HashSet > " + hs1);
		System.out.println("HashSet.size > " + hs1.size());
		System.out.println("----------------------------------------------------------------");
		
		
		HashSet hs2 = new HashSet();
		hs2.add("red");
		hs2.add("pink");
		hs2.add("gray");
		hs2.add(300);
		hs2.add(true);
		
		//Set은 데이터의 순서가 없으므로 인덱스로 데이터를 하나씩 불러 올 수 없다 > iterator 이용
		//hasNext() > 다음자료 존재여부에 따라 true / false
		//next()    > 포인터를 다음 값위치로 이동, 이동한 값 반환
		System.out.println("iterator로 출력");
		System.out.println();
		Iterator it = hs2.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("----------------------------------------------------------------");
		
		//Set은 집합과 비슷하다
		hs1.add("red");
		hs1.add(1);
		hs1.add("blue");
		hs1.add(100);
		hs1.add(false);
		System.out.println("hs1 > " + hs1);
		System.out.println("hs2 > " + hs2);
		System.out.println("hs2는 hs1의 부분집합이다 containsAll() > " + hs1.containsAll(hs2));
		
		hs1.addAll(hs2);	//합집합
		//hs1.retainAll(hs2);	//교집합
		//hs1.removeAll(hs2);	//차집합
		
		it = hs1.iterator();	
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("----------------------------------------------------------------");
		
		//1~100사이의 중복되지 않는 정수 5개 만들기
		HashSet<Integer> intRnd = new HashSet();
		while(intRnd.size() < 5) {	//Set의 데이터가 5개 될때 까지 반복
			int num = (int)(Math.random()*(100) +1); //1~100사이 난수
			intRnd.add(num);
		}
		
		System.out.println("만들어진 난수들 > " + intRnd);
		
		//Collection 유형의 객체들은 서로 다른 자료구조로 쉽게 변경해서 사용할 수 있다.
		//다른종류의 객체를 생성할 떼 생성자에 변경 할 데이터를 넣어주면 된다
		ArrayList<Integer> intRndList = new ArrayList<>(intRnd);
		System.out.println("new ArrayList<>(HashMap);");
		System.out.print("List의 자료출력 > ");
		for(Integer num : intRndList) {
			System.out.print(num + " ");
		}
		
		
	}

}
