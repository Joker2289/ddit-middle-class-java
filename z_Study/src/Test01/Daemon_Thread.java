package Test01;

public class Daemon_Thread {
/*
		# Daemon Thread(데몬 쓰레드)
		- 다른 일반 Thread(Daemon이 아닌 Thread)의 작업을 돕는 보조 역할을 하는 Thread
		- 일반 Thread가 종료되면 자동적으로 강제종료 된다 > 쓸모없어지기 때문
		
		ex) 가비지 컬렉터, 워드프로세서의 자동저장, 화면 자동 갱신등 > Daemon Thread
 		
 		.setDaemon(true / false) : 대상 Thread를 Daemon Thread로 셋팅 하는 메서드
 		.isDaemon() : 대상 Thread가 Daemon Thread인지 여부 확인 true / false 값 반환
 		.join() : 대상 Thread가 종료 될때까지 작업중지 > 대기상태
 */

	public static void main(String[] args) {
		AutoSave as = new AutoSave();
		Thread1 t1 = new Thread1();
		as.setDaemon(true); //Daemon Thread로 셋팅
		as.start();
		System.out.println("AutoSave Thread is Daemon?? > " + as.isDaemon());
		t1.start();
		
		
		try {
			t1.join(); //t1 Thread가 종료될때까지 작업을 중단
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("프로그램 종료");
	}

}
//Daemon Thread(3초에 한번씩 자동저장)
class AutoSave extends Thread{
	public void save() {
		System.out.println("Auto Save !!");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000); //3초에 한번씩 저장
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save();
		}
	}
}
//Normal Thread
class Thread1 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<20; i++) {
			System.out.println("작업 " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Thread1 종료");
	}
}