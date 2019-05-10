package Test01;

public class Single_Thread {

	public static void main(String[] args) {
		//싱글쓰레드 프로그램 > main Thread
		for(int i=1; i<10; i++) {
			System.out.println("*");
		}
		
		System.out.println();
		
		for(int i=1; i<=10; i++) {
			System.out.println("$");
		}
	
	}

}
