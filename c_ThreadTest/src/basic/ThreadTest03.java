package basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		System.out.println("main start");
		// .currentTimeMillis(); > 시간 저장 메서드
		
		//쓰레드의 수행시간 체크해보기
		Thread th = new Thread(new Myrunner());
		
		//1970년 1월 1일 0시 0분 0초(표준시)로부터 경과한 시간을 밀리세컨드(1/1000초) 단위로 나타낸다
		long startTime = System.currentTimeMillis(); //시간저장
		th.start();
		try {
			th.join(); //현재 실행중인 쓰레드(main Thread)에서 작업중인 쓰레드('th')가 종료될 때까지 기다린다
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();	//시간저장
		System.out.println("경과시간 > " + (endTime - startTime));
		System.out.println("main end");
	}
}

class Myrunner implements Runnable{
	//1 ~ 1000000000 까지의 합계를 구하는 쓰레드
	@Override
	public void run() {
		long sum = 0;
		for(long i=1L; i<=1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 > " + sum);
	}
}