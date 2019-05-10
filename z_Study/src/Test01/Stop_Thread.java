package Test01;
/*
	# suspend(), resume()
	- suspend()는 sleep()처럼 Thread를 멈추게 한다
	- suspend()에 의해 멈춘 Thread는 resume()을 호출해야 다시 실행대기 상태가 된다
	
	# stop()
	- stop()은 호출 즉시 Thread가 종료된다
	
	- 이 3개의 메서드는 Thread의 실행을 제어하는 가장 손쉬운 방법 이지만 suspend()와 stop()	이 
	  교착상태(deadlock)을 일으키기 쉽게 작성되어있으므로 사용이 권장되지 않는다
	
	Thread의 stop()메서드를 호출하면 쓰레드가 바로 멈춘다
	이때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서 나중에 실행돠는 프로그램에 영향을 줄 수 있다
	그래서 현재는 stop()메서드는 사용이 권장되지 않는다
*/

public class Stop_Thread {
	public static void main(String[] args) {
		//논리형 변수로 제어
		stop_Thread1 st1 = new stop_Thread1();
		st1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//st1.stop(); 사용 X
		st1.setStop(true);
		
		
		//interrupt 사용
		stop_Thread2 st2 = new stop_Thread2();
		st2.start();
		st2.delay();
		st2.interrupt();
	}
}
//논리형 변수로 제어
class stop_Thread1 extends Thread {
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	@Override
	public void run() {
		while(!stop) {
			System.out.println("쓰레드 처리중");
		}
		System.out.println("자원 정리중..");
		System.out.println("종료");
	}
}

//interrupt()로 제어
class stop_Thread2 extends Thread {
	@Override
	public void run() {
		//방법1 : sleep()메서드 또는 join()메서드 등을 사용하여 interrupt() 메서드를 호출하여 
		//InterruptException을 발생시켜 정지시킨다
		try {
			while(true) {
				System.out.println("쓰레드 처리중");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) { }
		
		//방법2 : interrupt()메서드가 호출되었는지 검사하기
		while(true) {
			System.out.println("쓰레드 처리중");
			
			//검사방법1 > Thread의 인스턴스 객체용 메서드를 이용하는 방법
			if(this.isInterrupted()) { //interrupt()메서드 > 이객체가 인터럽트에 걸렸나 true / false
				System.out.println("인스턴스용 interruped()");
				break;
			}
			
			//검사방법2 > Thread의 정적 메서드를 이용하는 방법
			if(Thread.interrupted()) { //interrupt()메서드가 호출되면 true
				System.out.println("정적 메서드 interrupted()");
				break;
			}
		}
		System.out.println("자원 정리중..");	
		System.out.println("실행종료");
	}
	
	void delay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}