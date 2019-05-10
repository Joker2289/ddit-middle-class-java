package ch13;

public class TreadEx2 {
/*
 		새로 생성한 쓰레드에서 고의로 예외를 발생시키고 printStackTrace()를 이용하여 예와가 발생한 당시의 호출스택을 출력하는 예제
 		
 		호출스택의 첫번째 메서드가 main메서드가 아니라 run메서드인 것을 확인하자
 		한 쓰레드가 예외가 발생해서 종료 되어도 다른 쓰레드의 실행에는 영향을 미치지 않는다
 */
	public static void main(String[] args) {
		ThreadEx2_1 t1 = new ThreadEx2_1();
		t1.start();
	}

}

class ThreadEx2_1 extends Thread {
	public void run() {
		throwException();
	}
	
	public void throwException() {
		try {
			throw new Exception();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
