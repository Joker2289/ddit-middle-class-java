package ch13;

public class ThreadEx12 {
/*
 			sleep() - 일정시간동안 Thread를 멈추게 한다
 			
 			staitc void sleep(long millis)
 			static void sleep(long millis, int nanos)
 			
 			sleep()에 의해 일시정지 상태가 된 쓰레드는 지정된 시간이 다되거나,
 			interrupt()가 호출되면 InterruptedException이 발생되어 실행대기 상태가 된다
 			
 			
 			sleep()은 항상 현재 실행 중인(호출했던 쓰레드) Thread에 대해 작동하기 때문에
 			th1.sleep(2000) 으로 호출하였지만 실제로 영향을 받는 것은 main Thread이다
 			
 			참조변수를 이용하여 호출하기 보다는 Thread.sleep(2000)으로 호출해야한다
 */
	public static void main(String[] args) {
		ThreadEx12_1 th1 = new ThreadEx12_1();
		ThreadEx12_2 th2 = new ThreadEx12_2();
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main 종료..");
	}
}

class ThreadEx12_1 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<300; i++) {
			System.out.print("-");
			if(i%100 ==0) {
				System.out.println();
			}
		}
		System.out.println("th1 종료..");
	}
}

class ThreadEx12_2 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<300; i++) {
			System.out.print("|");
			if(i%100 ==0) {
				System.out.println();
			}
		}
		System.out.println("th2 종료..");
	}
}