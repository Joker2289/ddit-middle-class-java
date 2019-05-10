package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
		Vector, Hashtable 등의 예전붜 존재하던 Collection들은 내부에 동기화 처리가 되어있다
		그런데, 최근데 새로 구성된 Collection들은 동기화 처리가 되어 있지 않다
		그래서, 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면,
		동기화 처리를 한 후에 사용해야 한다
		
 */
public class SyncCollectionTest {
	
	//동기화를 하지 않을 경우
	private static List<Integer> list1 = new ArrayList<>();
	
	//동기화를 하는 경우 - Collections 의 정적메서드 중에서 synchronized로 시작하는 메서드 이용
	//synchronized는 용량을 많이 잡아먹어서 동기화처리가 꼭 필요한 경우에만 사용한다
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	
	public static void main(String[] args) {
		//익명 class로 Thread	 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i<=10000; i++) {
					System.out.println(i);
					list1.add(i); //동기화 처리하지 않은 리스트사용
					list2.add(i); //동기화 처리한 리스트 사용
				}
			}
		};
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r),
				new Thread(r), new Thread(r), new Thread(r)
		};
		for(Thread th : ths) {
			th.start();
		}
		for(Thread th : ths) {
			try {
					th.join();
			} catch (Exception e) {
				e.printStackTrace();
			};
		}
		System.out.println("list1의 갯수 : " + list1.size());
		System.out.println("list2의 갯수 : " + list2.size());
	}
}
