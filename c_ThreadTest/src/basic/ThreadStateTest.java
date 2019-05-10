package basic;
/*
 * <쓰레드의 상태>
 * NEW : 쓰레드가 생성되고 아직 start()가 호출되지 않은상태
 * RUNABLE : 실행 중 또는 실행 가능한 상태
 * BLOKED : 동기화 블러겡 의해서 일시정지 된 상태(lock이 풀릴때까지 기다리는 상태)
 * 		WAITING, TIMED_WAITING : 쓰레드의 작업이 종료되지는 않았지만 실행가능하지 않은(unrunable)일시정지 상태
 * 		TIME_WAITING은 일시정지 시간이 지정된 경우임
 * TERMINATED : 쓰레드의 작업이 종료된 상태
 */

public class ThreadStateTest {
	public static void main(String[] args) {
		StatePrintThread spt = new StatePrintThread(new TargetThread()); //NEW상태
		spt.start(); //실행하면 Runable 상태
	}
}

class StatePrintThread extends Thread {
	private Thread targetThread; //상태를 출력할 쓰레드가 저장될 변수

	public StatePrintThread(Thread targetThread) {
		super();
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			//Thread의 상태구하기 (getState()메서드사용)
			Thread.State state = targetThread.getState(); 
			System.out.println("TargetThread State > " + state);
			
			//New 상태인지 검사
			if(state == Thread.State.NEW) {	//NEW - enum
				targetThread.start(); //StatePrintThread 가 직접 TargetThread를 실행 시켜줌
			}
			
			//타겟쓰레가 종료 상태인지 검사
			if(state == Thread.State.TERMINATED) {
				break;
			}
			try {
				Thread.sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
/*
 *  target용 쓰레드
 */
class TargetThread extends Thread {
	public void run() {
		for(long i=1; i<=1000000000L; i++) { } //시간지연용
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(long i=1; i<=1000000000L; i++) { } //시간지연용
	}
}