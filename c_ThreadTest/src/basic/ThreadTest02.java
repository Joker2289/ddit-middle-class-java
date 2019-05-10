package basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		//멀티 쓰레드 프로그램 방식
		//쓰레드 실행 : start(); > 한번 실행이 종료된 쓰레드는 재실행 불가 > 즉, 하나의 쓰레드에 start()가 한번만 호출할 수 있다
		
		
		//방법1: Thread클래스를 상속한 class의 인스턴스를 생성한 후 이 인스턴스의 start()메서드를 호출한다
		//Thread 클래스를 상속받으면 다른 클래스를 다중상속 받을 수 없어서 잘 안쓰는 방법
		//상속받을 필요가 없는 클래스에만 이방법을 쓴다
		MyThread1 th1 = new MyThread1();
		//th1.start();
		
		//젤많이 씀
		//방법2: Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다
		// 이 때 생성된 Thread객체의 인스턴스의 start() 메서드를 호출한다
		MyThread2 r1 = new MyThread2();
		Thread th2 = new Thread(r1);
		//th2.start();
		
		//방법3 : 익명클래스를 이용하는 방법
		//Runnable인터페이스를 구현한 익명클래스를 Thread인스턴스를 생성할 때 매개변수로 넘겨준다
		//한번 쓰고 버릴때 / class 생성을 굳이 안하고 익명클래스를 이용한다
		Thread th3 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {											
					System.out.println("@");
					try {
						Thread.sleep(100);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});//Thread param end
		System.out.println("main ");
		Thread th4 = new Thread(new MyThread3());
		
		th1.start(); //*	//쓰레드는 총 4개다 > 3개를 실행 시켜주는 메인 쓰레드가 1개 존재한다 
		th2.start(); //$	//메인 메서드가 종료되도 쓰레드가 실행중이라면 프로그램은 대기상태에 있다
		th3.start(); //@	//쓰레드가 종료되야 프로그램도 종료된다
		th4.start(); //#
		System.out.println("main메서드 작업 끝...");
	}
}

class MyThread1 extends Thread {
	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.println("*");
			try {
				//Thread.sleep(시간) > 주어진 시간동안 작업을 잠시 멈춘다 / 시간은 밀리세컨드 단위를 사용한다 / 즉, 1000 = 1초
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {
	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.println("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread3 implements Runnable {
	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.println("#");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}