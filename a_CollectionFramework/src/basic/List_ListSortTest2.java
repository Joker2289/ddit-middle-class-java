package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class List_ListSortTest2 {

	public static void main(String[] args) {
		
		ArrayList<Member> memList = new ArrayList<>();
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-2222"));
		memList.add(new Member(9, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "이순신", "010-1122-1431"));
		memList.add(new Member(6, "강감찬", "010-1111-1111"));
		memList.add(new Member(2, "일지매", "010-1111-1111"));
		
		System.out.println("정렬 전");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		System.out.println("============================");
	
		Collections.sort(memList); //정렬하기
		
		System.out.println("이름의 오름차순으로 정렬 후");

		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		System.out.println("============================");
		
		//외부정렬 기준을 이용한 정렬하기
		Collections.sort(memList, new SortNumDesc()); //정렬하기
		System.out.println("번호의 오름차순으로 정렬 후");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		System.out.println("============================");
		
		Collections.sort(memList, new SortNumDesc());
		System.out.println("번호의 내림차순으로 정렬 후");
		for(Member mem : memList) {
			System.out.println(mem);
		}
	}

}

//정렬 기준의 외부 선언을 위해서는 Comparator 인터페이스를 구현하면 된다
//Member 의 번호 (num)의 내림차순으로 정렬하기
class SortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
		if(mem1.getNum() > mem2.getNum()) {
			return -1;
		}else if(mem1.getNum() == mem2.getNum()) {
			return 0;
		}else {
			return 1;
		}
		//Wrapper 클래스에서 제공하는 메서드를 이용하는 방법1
		// 내림차순일 경우에는 -1을 곱해준다
		//return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
		
		//Wrapper 클래스에서 제공하는 메서드를 이용하는 방법2
		//return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
	}
	
}

//회원의 정보를 저장 할 클래스 만들기
//회원정보는 회원번호(int), 회원이름, 전화번호로 되어 있다
//회원이름을 기준으로 오름차순 정렬이 될 수 있는 클래스 만들기

class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;
	
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	//이름을 기준으로 오름차순 정렬이 되도록 설정한다
	@Override
	public int compareTo(Member mem) {
		return getName().compareTo(mem.getName());
	}
	@Override
	public String toString() {
		return "[num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	
	
}
