package basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {
	//입력 여부를 확인하기 위한 변수 선언
	//모든 쓰레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		th1.start();
		th2.start();
	}
}
//데이터 입력하는 쓰레드
class DataInput extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("입력");
		
		//입력이 완료 되면 inputCheck 변수를 true로 변경
		ThreadTest06.inputCheck = true;
		System.out.println(str);
	}
}

//카운트다운 쓰레드
class CountDown extends Thread {
	@Override
	public void run() {
		for(int i=10; i>=1; i--) {
			//입력 되었는지 여부를 검사후 입력이 완료되면 run()메서드를 종료시킨다
			if(ThreadTest06.inputCheck == true) {
				return; // run()메서드 종료 > Thread종료
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		//5초가 경과 되었는데도 입력이 없으면 프로그램울 종료
		System.out.println("Time Over");
		System.exit(0); //프로그램 강제종료 
	}
}