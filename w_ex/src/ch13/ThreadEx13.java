package ch13;

import javax.swing.JOptionPane;
	/*
			# interrupt(), interrupted()
			- Thread의 작업을 취소한다
			- interrupt()는 Thread에게 작업을 멈추라고 요청한다
			- inturrupted()는 Thread에 대해 interrupt()가 호출되었는지 알려준다 true / false
			
			- void interrupt() 				: Thread의 interrupted상태를 false에서 ture로 변경
			- static boolean interrupted()	: Thread의 interrupted상태를 반환후, false로 변경
			- boolean isinterrupted() 		: Thread의 interrupted상태를 반환 
			
			sleep(), wait(), join()에 의해 '일시정지 상태(WAITING)'에 있을 때,
			해당 Thread에 대해 interrupt()를 호출하면 InterruptedException 이 발생하고 
			Thread는 '실행대기 상태(RUNNABLE)' 로 바뀐다
			
 */
public class ThreadEx13 {
	public static void main(String[] args) {
		ThreadEx13_1 th1 = new ThreadEx13_1();
		th1.start();
		
		String input = JOptionPane.showInputDialog("입력");
		System.out.println("입력값 : " + input);
		th1.interrupt(); //interrupt 호출
		System.out.println("isInterrupted() : " + th1.isInterrupted());
	}
}

class ThreadEx13_1 extends Thread {
	@Override
	public void run() {
		int i=10;
		while(i!=0 && !isInterrupted()) {
			System.out.println(i--);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				interrupt();
			}
		}
		System.out.println("Over");
	}
}
