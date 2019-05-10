package basic;

public class SyncThreadTest {
	//트랜잭션 : 작업의 최소단위 ex) SQL > commit , roll back
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkerThread th1 = new WorkerThread("1번 쓰레드", sObj);
		WorkerThread th2 = new WorkerThread("2번 쓰레드", sObj);
		
		th1.start();
		th2.start();
	}
}

//공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	//동기화 하는 방법 1 : 메서드 자체에 동기화 설정하기
	//public synchronized void add() {
	public void add() {
		//동기화 하는 방법 2 : 동기화 블럭으로 설정하기			//synchronized 에는 1개의 쓰레드만 접근가능 1개 끝다고 접근해야 함 > 동시수행 불가
		//synchronized (this) {							//1번쓰레드가 먼저 synchronized에 접근하여 수행중일 동안
			int n = sum;								//2번쓰레드는 접근불가 1번쓰레드가 빠져나간후 2번쓰레드 진입가능
			n += 10; // 10증가
			sum = n;
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		//}
	}
	
	public int getSum() {
		return sum;
	}
}
//작업을 수행하는 쓰레드
class WorkerThread extends Thread {
	ShareObject sObj;
	
	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			sObj.add();
		}
	}
}