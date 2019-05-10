package basic;
/*
		synchronized로 동기화해서 공유 데이터를 보호하는 것은 좋지만,
		특정 Thread가 객체의 Lock을 가진상태로 오랜시간을 보내지 않도록 하는 것도 중요하다
		
		동기화된 임계영역의 코드를 수행하다가 작업을 더 이상 진행할 상황이 아니면,
		일단 wait()을 호출하여 Lock을 반납하고 기다리게 한후 다른 Thread가 작업을 수행하다가 
		다시 작업을 진행할 수 있는 상황이 되면 notify()를 호출해서 대기중인 Thread가 Lock을 얻어 임계영역에 들어오는 것을
		'재진입(reentrance)' 이라고 한다
		
		
		# wait(), notify(), notifyAll()
 		- wait() 메서드 : 동기화 영역에서 락을 풀고 wait-set 영역으로 이동시킨다
 		- notify() 또는 notifyAll() : wait-set : 영역에 있는 Thread를 깨워서 실행될 수 있도록 한다
 					(notify()는 하나, notifyAll()은 wait-set에 있는 전부를 깨운다)
 		
 		- wait()과 notify(), notifyAll()	 
 		1) 동기화 영역에서만 실행할 수 있다
 		2) Object객체에 정의되어 있는 메서드들이다
 		3) 보다 효율적인 동기화를 가능하게한다
 		
 		- wait-set에 많은 Thread가 있는경우 notify()를 쓰면 랜덤하게 한개의 Thread 만깨움
 		  > notifyAll()을 써도 임의의 Thread만 Lock을 얻는다 > 못얻은 Thread는 다시 대기상태 
 		
 		# wait()과 notify()를 이용한 두 Thread가 번갈아 가면서 한번 씩 실행하는 예제
 */

public class WaitNotifyTest {
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

//공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA() 작업중..");
		
		notify();
		
		try {
			wait();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB() 작업중..");
		
		notify();
		
		try {
			wait();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//WorkObject의 methodA()메서드만 호출하는 Thread
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workobj) {
		this.workObj = workobj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodA();
		}
	}
}


//WorkObject의 methodB()	메서드만 호출하는 Thread
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workobj) {
		this.workObj = workobj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodB();
		}
	}
}




