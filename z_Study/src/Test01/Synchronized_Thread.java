package Test01;

/*
		# 동기화(synchronized) : 한 Thread가 진행중인 작업을 다른 Thread가 간섭하지 못하도록 막는것
		- Multi-Thread 프로세스의 경우 여러 Thread가 
		  같은 프로세스내 자원을 공유해서 작업하기 때문에 서로의 작업에 영향을 주게된다
		- 위와 같은 문제로 '임계영역(critical section)' 과 '잠금(lock)' 이 도입되었다 
		
		
		# synchronized에는 1개의 Thread만 접근가능 > 1개의 작업이 끝나야 접근가능하다(동시수행 불가)
		- synchronized 메서드가 호출된 시점부터 해당 메서드가 포함된 객체의 lock을 얻어 작업을 수행하다가
		  메서드가 종료되면 lock을 반환한다
		
 */
public class Synchronized_Thread {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkThread t1 = new WorkThread("No.1 Thread", sObj);
		WorkThread t2 = new WorkThread("No.2 Thread", sObj);
		
		t1.start();
		t2.start();
	}
}

//공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	//동기화 방법1 : 메서드 자체에 동기화 설정  > 메서드 전체가 동기화됨
	//public synchronized void add() {
	public void add() {
		//동기화 방법2 : 동기화 블럭으로 설정하기 > 참조변수는 lock을 걸고자하는 객체를 참조하는 것이어야 한다
		//synchronized (this) {
			//임계구역
			int n = sum;
			n += 10; //10증가
			sum = n;
			System.out.println(Thread.currentThread().getName() + "  합계 : " + sum);
			//임계구역
		//}
	}
	public int getSum() {
		return sum;
	}	
}

//작업을 수행하는 Thread
class WorkThread extends Thread {
	ShareObject sObj;

	public WorkThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			sObj.add(); // 동기화 메서드
		}
	}
}