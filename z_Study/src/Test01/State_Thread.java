package Test01;
/*
 		Thread 프로그래밍이 어려운이유는 동기화(synchronization) 과 스케줄링(scheduling) 때문이다
 		
 		우선순위를 통해 Thread간의 스케줄링 방법을 익혔지만, 
 		이것만으로 효율적인 Multi-Thread 프로그램을 만들기에는 한참 부족하다
 		
 		보다 정교한 스케줄링을 통해 Process에게 주어진 자원과 시간을 여러 Thread가
 		낭비없이 잘 사용하도록 프로그래밍 해야한다
 		
 		위의 표는 Thread 스케줄링과 관련된 Method들이다 참고하자
 		
		# Thread State(쓰레드의 상태)
		- NEW 			: Thread가 생성되고 아직 start()가 호출되지 않든 상태
		- RUNNABLE 		: 실행중 또는 실행가능한 상태
		- BLOCKED		: 동기화 블럭에 의해 일지정지된 상태(lock이 풀릴때 까지 기다리는 상태)
		- WAITING		: Thread 작업이 종료되진 않았지만 실행가능하지 않은 일지정지 상태(unrunnable)
		  TIME WAITING	: 일시정지 시간이 지정된 경우
		- TERMINATED	: Thread 작업이 종료된 상태
		
		.getState() : 대상 Thread의 상태를 가져오는 메서드
 */
public class State_Thread {

	public static void main(String[] args) {
		//NEW 상태
		StatePrint_Thread spt = new StatePrint_Thread(new Target_Thread());
		//RUNNABLE 상태
		spt.start();
	}
}
//상태를 출력할 Thread
class StatePrint_Thread extends Thread {
	private Thread target; //상태를 출력할 Thread가 저장될 변수

	public StatePrint_Thread(Thread target_Thread) {
		super();
		this.target = target_Thread;
	}
	
	@Override
	public void run() {
		while(true) {
			Thread.State state = target.getState(); //Thread의 상태구하기
			System.out.println("Thread State > " + state);
			
			//NEW상태인지 검사 > 맞으면 start()
			if(state == Thread.State.NEW) {	//NEW > enum
				target.start(); 
			}
			//TERMINATED 상태인지 검사
			if(state == Thread.State.TERMINATED) {
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//타겟이 될 Thread
class Target_Thread extends Thread {
	@Override
	public void run() {
		for(long i=0; i<=1000000000L; i++) {} //시간지연용
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(long i=0; i<=1000000000L; i++) {} //시간지연용
	}
}
