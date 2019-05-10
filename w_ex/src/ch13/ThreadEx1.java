package ch13;

public class ThreadEx1 {
	public static void main(String[] args) {
		ThreadEx_1 t1 = new ThreadEx_1();
		Thread t2 = new Thread(new ThreadEx_2()); //생성자 Thread(Runnable Target)
		t1.start();
		t2.start();
	}
}

//Thread클래스를 상속받으면 자손클래스에서 조상의 메서드를 직접호출가능
class ThreadEx_1 extends Thread {
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println(getName()); //조상인 Thread의 getName을 호출
		}
	}
}

//Runable을 구현하면 Thread클래스의 static메서드인 currentThread()를 통하여 호출가능
class ThreadEx_2 implements Runnable {
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			//Thread.currentThread() - 현재 실행중인 Thread를 반환한다
			System.out.println(Thread.currentThread().getName());
		}
	}
	
}