package basic;

public class SingletonTest {
	public static void main(String[] args) {
		//MySingleton test1 = new MySingleton(); // 객체 생성 불가
		
		//getnInstance()메서드 이용하여 객체생성
		MySingleton test2 = MySingleton.getInstance();
		
		test2.display();
		
		MySingleton test3 = MySingleton.getInstance();
		System.out.println("test2 > " + test2);
		System.out.println("test3 > " + test3);
	}
}

