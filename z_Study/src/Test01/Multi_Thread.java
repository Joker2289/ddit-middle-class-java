package Test01;

public class Multi_Thread {
	/*
	 		# 멀티 쓰레드 프로그램 방식
	 		
	 		start() > Thread의 실헹 > 한번 실행이 종료된 Thread는 재실행 불가
	 	 	* start()가 호출 되었다고 바로 실행하는 것이 아니라 일단, 실행 대기상태에 있다가 자기 차례가 되어야 실행된다
			
			sleep(시간) >주어진 시간동안 작업을 잠시 멈춘다(Milli_Set 단위) 1000 = 1초
	 */
	public static void main(String[] args) {
		//Thread 상속 
		MultiThread1 mt1 = new MultiThread1(); 			
		mt1.start();
		
		//Runnable 인터페이스 구현
		Thread mt2 = new Thread(new MultiThread2()); 	
		mt2.start();
		
		//Thread 재실행 > consle 출력 내용을 보아 위와 동일한 Thread가 아니다
		mt2 = new Thread(new MultiThread2());
		mt2.start();
		
		//현재 실행된 Thread는 총 3 + 1(main Thread) = 4개 이다
		//메인 메서드의 작업이 끝나도 다른 Thread가 실행중이라면 프로그램은 대기상태에 있다
		//다른 Thread가 종료되야 프로그램도 종료된다
		System.out.println("main 메서드 작업 종료");
	}
}

//Thread클래스를 상속받는 방법	>	다른 Class를 상속 받을 수 없다
class MultiThread1 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println(getName() + "실행중");
			try {
				//sleep() > 주어진 시간동안 작업을 잠시 멈춘다(Milli_Second)
				Thread.sleep(100); //0.1초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(getName() + "종료"); //조상인 Thread의 getName을 직접호출
	}
}


//인터페이스를 구현하는 방법		>	가장 일반적인 사용	
//Runnable 인터페이스는 오로지 run()만 정의 되어있다
//이후 이 인스턴스를 Thread 객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다
class MultiThread2 implements Runnable {
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println(Thread.currentThread().getName() + "실행중");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "종료");
		//Runable을 구현하면 Thread클래스의 static메서드인 currentThread()를 통하여 호출가능
	}
}