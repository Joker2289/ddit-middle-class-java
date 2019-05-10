package Test01;

import javax.swing.JOptionPane;

public class Thread_CountDown {
	//입력 여부를 확인하기 위한 전역변수
	public static boolean input_Check = false;
	public static void main(String[] args) {
		InputData t1 = new InputData();
		CountDown t2 = new CountDown();
		
		t1.start();
		t2.start();
	}
}
//카운트다운 Thread
class CountDown extends Thread {
	@Override
	public void run() {
		for(int i=10; i>=0; i--) {
			if(Thread_CountDown.input_Check == true) { //입력여부 확인
				return; //run()메서드 종료 > Thread 종료
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Time Over");
		System.exit(0); //프로그렘 강제종료
	} 
}
//데이터 입력 Thread
class InputData extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("입력");
		Thread_CountDown.input_Check = true; 	//입력 완료 후 true
		System.out.println("입력값 > " + str);	//입력값 출력	
	}

}
