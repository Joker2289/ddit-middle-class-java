package basic;

public class ThreadTest {

	public static void main(String[] args) {
		//싱글 쓰레드 프로그램 > main Thread
		
		for(int i=1; i<=200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for(int i=1; i<=200; i++) {
			System.out.print("$");
		}
	}
}
