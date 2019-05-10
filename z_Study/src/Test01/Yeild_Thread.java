package Test01;
/*
		# yield() 
		- Thread 자신에게 주어진 실행시간을 다음차례의 Thread에게 양보(yield)한다
		
		- 예를들어, 스크쥴러에 의해 1초의 실행시간을 할당받은 Thread가 0.5초동안 작업한 상태에서
		  yield()를 호출하면 나머지 0.5초는 포기하고 다시 실행대기상태가 된다
		
		- yield()와 interrupt()를 적절히 사용하면, 프로그램의 응답성을 높이고 보다 효울적인 실행이 가능하다
 */
public class Yeild_Thread {
	public static void main(String[] args) {
		Yield_Thread t1 = new Yield_Thread("No1");
		Yield_Thread t2 = new Yield_Thread("No2");	
		
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("No1 Yeild--------------------------");
		t1.work = false;
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("No2 Yeild--------------------------");
		t1.work = true;
		t2.work = false;
		
		t1.stop = true;
		t2.stop = true;
	}

}

//yield()
class Yield_Thread extends Thread {
	public boolean stop = false; //반복문(while) 제어변수
	public boolean work = true;	 //작업실행(if) 제어변수 
	
	public Yield_Thread(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while(!stop) {
			if(work) {
				System.out.println(getName() + " 작업중..");
			} else {
				System.out.println(getName() + " 작업 양보..");
				Thread.yield();
			}
		}
		System.out.println(getName() + " 작업 종료..");
	}
}