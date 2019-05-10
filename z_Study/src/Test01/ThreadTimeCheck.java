package Test01;

public class ThreadTimeCheck {
/*
 		1~20억 까지의 합계를 구하는데 걸린 시간 체크하기
		Single Thread VS Multi Thread
*/
	public static void main(String[] args) {
		//Single Thread
		SumThread s = new SumThread(1L, 2000000000L);
		long startTime = System.currentTimeMillis();
		s.start();
		try {
			s.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println();
		System.out.println("Single Thread Time > " + (endTime - startTime));
		System.out.println();
		
		//Multi Thread
		 SumThread[] m = new SumThread[] {
				 new SumThread(1L, 500000000L),
				 new SumThread(500000001L, 1000000000L),
				 new SumThread(1000000001L, 1500000000L),
				 new SumThread(1500000001L, 2000000000L)
		 };
		 startTime = System.currentTimeMillis();
		 for(int i=0; i<m.length; i++) {
			 m[i].start();
		 }
		 for(int i=0; i<m.length; i++) {
			 try {
				 m[i].join();
			 } catch(InterruptedException e) {
				 e.printStackTrace();
			 }
		 }
		 endTime = System.currentTimeMillis();
		 System.out.println();
		 System.out.println("Multi Thread Time > " + (endTime - startTime));
	}
}

class SumThread extends Thread {
	private long max, min;
	public SumThread(long min, long max) {
		super();
		this.max = max;
		this.min = min;
	}
	@Override
	public void run() {
		long sum = 0L;
		for(long i=min; i<=max; i++) {
			sum += i;
		}
		System.out.println(min + " ~ " + max + "\t sum > " + sum);
	}
}