package basic;

import java.util.Vector;

public class List_Vector {

	public static void main(String[] args) {
		
		// Vector > List계열 클래스
		Vector v1 = new Vector();
		System.out.println("처음크기 : " + v1.size()); 

		// Vector는 add()메서드를 이용하여 데이터를 추가할 수 있다.
		v1.add("aaa");
		v1.add(111);
		v1.add(new Integer(123)); // Wrapper 클래스로 감싸는 작업 > 박싱 
		v1.add('a');
		v1.add(true);
		v1.add(3.14);			  // 박싱을 안해도 컴파일 과정에서 자바 자체적으로 박싱해주는것 > 오토박싱

		System.out.println("현재 크기 : " + v1.size());

		// vector는 addElement() 메서드를 이용하여 추가할 수도 있지만, 이 메서드는 기본적으로 add() 매서드와 같은 기능을 수행한다
		// add()와 addElement()의 차이점 > addElement()메서드는 데이터를 추가할 때 동기화 작업을 수행한다.
		// addElement는 Vector에만 존재하는 API이다
		v1.addElement("ccc");
		System.out.println("v1 > " + v1.toString());

		// add(index, 데이터) > 벡터의 index번째에 '데이터'를 끼워 넣는다 > index는 0부터 시작한다
		v1.add(1, "kkk");
		System.out.println("v1 > " + v1.toString());

		// set(index, 데이터) > 벡터의 index 번째의 값을 주어진 '데이터'로 덮어쓴다 > 반환값 : 원래의 데이터
		String temp = (String)v1.set(0, "zzz");
		System.out.println("set명령 후 v1 > " + v1);
		System.out.println("원래의 데이터 > " + temp);

		// remove(index) > 벡터의 index번째 자료를 삭제한다 > 자료가 삭제되면 index번째 다음번째 이후의 데이터들이 앞으로
		// 자동으로 당겨져 채워진다 > 반환값 : 삭제된 데이터
		// remove(삭제할 데이터) > '삭제할 데이터'를 찾아 삭제한다 > 만약 '삭제할 데이터'가 여러개 이면 앞에서 부터 삭제한다
		// > 삭제할 데이터가 '정수형' 또는 char형 일 경우 > 삭제할 데이터를 객체로 변환해서 사용해야 한다

		v1.remove(0);
		System.out.println("삭제후 : " + v1);
		System.out.println();

		temp = (String) v1.remove(0);
		System.out.println("삭제된 자료 > " + temp);
		System.out.println("삭제 후 > " + v1);
		System.out.println();

		v1.add(123);
		v1.remove(true);
		System.out.println("삭제 후 > " + v1);
		System.out.println();
		
		v1.remove(new Integer(123));
		System.out.println("삭제 후 > " + v1);
		System.out.println();

		v1.remove(new Character('a'));
		System.out.println("삭제 후 > " + v1);
		System.out.println();

		v1.remove(3.14);
		System.out.println("삭제 후 > " + v1);
		System.out.println();

		/*
		 * 제네릭 타입 > collection 객체를 선언할 때 <> 안에 그 Collection이 저장할 데이터 타입을 정해주는 것을 말한다 
		 * > 이런식으로 선언하게 되면 그 데이터 타입 이외의 데이터를 저장할 수 없다 (제네릭 타입으로 선언할 수 있는 데이터 타입은 클래스 이어야한다) 
		 * ex) int > Integer, boolean > Boolean, char > character > 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다
		 */

		Vector<String> v2 = new Vector<>();
		Vector<Integer> v3 = new Vector<>();

		v2.add("안녕하세요");
		// v2.add(123); // 오류

		String temp2 = v2.get(0);
		System.out.println("temp2 > " + temp2);

		Vector<Vector> vv = new Vector<>();
		Vector<Vector<Vector>> vvv = new Vector<>();

		// -----------------------------------------------

		v2.clear(); // 벡터의 모든데이터를 삭제한다
		System.out.println("v2의 사이즈 > " + v2.size());

		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");

		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");

		System.out.println("삭제되기전 > " + v2);

		// removeAll(Collection객체) > 벡터의 데이터 중에서 'Collection' 객체가 가고 있는 데이터를 모두 삭제한다
		v2.removeAll(v4);
		System.out.println("삭제된 후 v2 > " + v2);
		System.out.println("------------------------------");

		v2.clear();

		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");

		// 벡터의 데이터들을 순서대로 모두 가져와서 사용하고 싶으면 반복문을 사용하면 된다(주로 for문 사용)
		for (int i = 0; i < v2.size(); i++) {
			System.out.println(i + "번째 자료 > " + v2.get(i));
		}
		System.out.println("--------------------------------");

		/*
		 * 향상된 for문
		 * 
		 * for(자료형명 변수명 : 배열변수나 Collection계열 변수){
		 *   처리할 내용들; 
		 * }
		 * > 주어진 '배열변수나 Collection계열 변수'의 데이터 개수만큼 반복한다 > 반복할 때마다 '배열변수나 Collection을 향상
		 */

		for (String s : v2) {
			System.out.println(s);
		}
		System.out.println("---------------------------------");

		// 만약 제네릭을 사용하지 않은 Collection을 향상된 for문으로 처리할 경우에는 Object형으로 처리한다
		for (Object obj : v1) {
			System.out.println(obj);
		}
		
		Vector v = new Vector(5); // 용량이 5인 (사이즈는 0) 벡터생성
		v.add("홍길동");
		v.add("박찬호");
		v.add("3");
		print(v);
		
		v.trimToSize(); // 벡터의 용량을 현재 벡터사이즈로 줄인다
		System.out.println("trimToSize() --------");
		print(v);
		
		v.ensureCapacity(5); // 현재 용량이 설정용량 보다 작다면 (신규용량 = 현재용량 * 2), 그래도 설정용량보다 작다면 (신규용량 = 설정용량)으로 설정한다
		System.out.println("ensureCapacity(5) --------");
		print(v);
		
		v.setSize(7); // 현재용량이 설정사이즈보다 작다면 > (신규용량=현재용량 *2), 그래도 설정사이즈보다 작다면 > (신규용량=설정사이즈)로 설정한다
		System.out.println("setSize(7) --------");
		print(v);
		
		v.clear();
		System.out.println("clear() --------");
		print(v);
	}
	
	private static void print(Vector v) {
		System.out.println(v);
		System.out.println("size > " + v.size());
		System.out.println("capacity > " + v.capacity());
		System.out.println();
	}

}
