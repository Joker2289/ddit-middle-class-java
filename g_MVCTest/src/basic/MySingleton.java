package basic;
/*
		# singleton 패턴 > 객체(인스턴스)를 한 개만 만들어지게 하는 프로그래밍 방법
		
		- 외부에서 new 명령을 사용하지 못하고 동일한 객체(인스턴스)를 반환하는 클래스
		
		# singleton class를 구성하는 방법
		1. 자기 자신 class의 참조변수를 멤버변수로 선언한다(이 변수는 private static으로 지정한다)
		2. 생성자를 private로 한다(외부에서 생성자에 접근 하지못하게... 즉 외부에서 new명령을 사용하지 못하게 하려고)
		3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다 (이 메서드의 이름은 보통 getInstance()로 지정 + static으로 지정)
 */

public class MySingleton {
	//자기 자신의 class의 참조값을 저장하는 멤버변수 선언
	private static MySingleton single;
	
	//생성자를 private로 지정한다	> 객체 생성을 할 수 없다 > 자기자신 안에서 객체생성을 함 > 누군가 외부에서 getInstance를 호출하면 생성해놓은 객체를 갖다줌
	private MySingleton() {
		System.out.println("생성자 입니다");
	}
	
	public static MySingleton getInstance() {
		if(single == null) {
			single = new MySingleton();
		}
		
		return single;
	}
	
	//나머지 내용들은 이 클래스로 처리할 내용들을 기술
	public void display() {
		System.out.println("안녕하세요 Singleton 객체 입니다");
	}
}
