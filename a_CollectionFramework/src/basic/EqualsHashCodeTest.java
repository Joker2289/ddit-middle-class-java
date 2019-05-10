package basic;	

import java.util.HashSet;

public class EqualsHashCodeTest {

/*
 * HashSet, HashMap, Hastable과 같은 객체들을 사용할 경우 객체가 서로 같은지를 비교하기 위해 > equals()메서드와 hashCode()메서드를 호출한다
 * 그래서 개체가 서로 같은지 여부를 결정하려면 두 메서드를 재정의 해야한다
 * 
 * HashSet, HashMap, Hashtable에서는 개체가 같은지 여부는 데이터를 추가할때 검사한다
 * - equals() 메서드는 두 객체에 내용(값)이 같은지 비교하는 메서드이고,
 * - hashCode() 메서드는 두객체가 같은 객체인지 비교하는 메서드이다
 * 
 * - qquals() 메서드와 hashCode()메서드에 관련된 규칙
 * 1. 두 객체가 같으면 반드시 같은 hashcode를 가져야한다.
 * 2. 두 객체가 같으면 equals()메서드를 호출했을 때 true를 반환해야 한다 		> 	즉, 객체 a, b가 같다면 a.equals(b)와 b.equals(a) 둘다 true이어야 한다
 * 3. 두 객체의 hashcode가 같다고 해서 두 객체가 반드시 같은 객체는 아니다 	>	 하지만, 두 객체가 같으면 반드시 hashcode가 같아야 한다
 * 4. equals()메서드를 override하면 반드시 hashcode()메서드도 override해야 한다
 * 5. hashCode() 기본적으로 Heap에 있는 각 객체에 대한 메모리 주소 맵핑 정보를 기반으로 한 정수 값을 반환한다 
 * 		> 그러므로, 클래스에서 hashCode()메서드를 override하지 않으면 절대로 두 객체가 같은 것으로 간주 될 수 없다
 * 
 * - hashCode()메서드에서 사용하는 '해싱 알고리즘'에서 서로 다른 객체에 대하여 같은 hashcode값을 만들어 낼 수 있다
 * 	그래서, 객체가 같지 않더리도 hashcode가 같을 수 있다
 * 
 * 
 * 검색속도는 Hash가 가장 빠르다
 * 
 */
	public static void main(String[] args) {
		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		System.out.println("p1 > " + p1);
		System.out.println("p2 > " + p2);
		System.out.println("p1 hashcode > " + p1.hashCode());
		System.out.println("p2 hashcode > " + p2.hashCode());
		System.out.println("p1.equals(p2) > " + p1.equals(p2)); 	//true
		System.out.println("p2.equals(p1) > " + p2.equals(p1)); 	//ture
		System.out.println("p1==p2 > " + (p1==p2));					//false - 각각 다른 객체로 주소값이 달라서 // == - 같은 메모리에 있는 값을 비교
		System.out.println("---------------------------------------");
		HashSet<Person> hset = new HashSet<>();
		hset.add(p1);
		hset.add(p2);
		
		System.out.println("hset.add(p1), hesetadd(p2)");
		System.out.println("hset.size() > " + hset.size());
		System.out.println("---------------------------------------");
		System.out.println("변경 전 데이터");
		for(Person p : hset) {
			System.out.println(p.getId() +  " : " + p.getName() + " : " + p.hashCode());
		}
		System.out.println();
		
		p1.setName("일지매");
		System.out.println("p1.setName(일지매)");
		System.out.println("변경 후 데이터");
		for(Person p : hset) {
			System.out.println(p.getId() +  " : " + p.getName() + " : " + p.hashCode());
		}
		System.out.println("---------------------------------------");
		System.out.println("hset.add(p1)");
		System.out.println(hset.add(p1));
		System.out.println("hset.size() > " + hset.size());
		for(Person p : hset) {
			System.out.println(p.getId() +  " : " + p.getName() + " : " + p.hashCode());
		}
		
		System.out.println();
		System.out.println("hset.remave(p2)");
		System.out.println(hset.remove(p2));
		System.out.println("hset.size() > " + hset.size());
		for(Person p : hset) {
			System.out.println(p.getId() +  " : " + p.getName() + " : " + p.hashCode());
		}
	}

}

class Person{
	private int id;
	private String name;
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;	//소수
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		
		return result;
		// return (name+id).hashCode(); // 위 로직말고 이거 한 줄만 써도댐
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) { //this.getClass() = Person / obj.getclass() = Person
			return false;
		}
		if (this == obj) { //ex) p1.equals(p1)
			return true;
		}
		
		Person test = (Person) obj; //형변환(Casting)
		if (this.name == null && test.name != null) {
			return false;
		}
		if (this.id == test.id && this.name.equals(test.name)) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name;
	}
	
	
}
