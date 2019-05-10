package Test01;

public class Thread_Priority {
/*
 		# Priority(우선순위)
 		- Thread는 Priority(우선순위)라는 속성(멤버변수를 가지고 있다)
 		- Priority 값에 따라 Thread가 얻는 실행시간이 달라진다
 		- Thread가 수행하는 작업으로 중요도에 따라 Priority를 서로 다르게 지정하여 특정 Thread가 
 		  더 많은 작업시간을 갖도록 할 수 있다
 		  
 		 ex) 파일전송기능이 있는 메신저의 경우, 
 		 	* 파일 다운로드를 처리하는 Thread 보다 채팅내용을 전송하는 Thread가 우선순위가 높아야 한다
 		 	* 대신 파일다운로드 작업에 걸리는 시간은 더 길어질 것이다
 		
 		setPriority(value) : value값 만큼 우선순위 저장
 		getPriority() : Thread의 우선순위 반환  
 		
 		MAX_PRIORITY = 10
 		NORM_PRIORITY = 5 Thread 생성시 기본으로 적용
 		MIN_PRIORITY = 1
 */
	
/*
 		그러나 멀티코어에서는 Thread의 우선순위에 따른 차이가 거의 아니 전혀 없다
 	  	Thread에 높은 우선순위를 주면 더많은 실행시간과 실행기회를 갖게 될것이라고 기대할수는 없다
 */
	public static void main(String[] args) {
		output_Char o1 = new output_Char();
		output_Char2 o2 = new output_Char2();
		
		o1.setPriority(10);
		o2.setPriority(1);
		
		System.out.println("o1 Priority > " + o1.getPriority());
		System.out.println("o2 Priority > " + o2.getPriority());
		
		o1.start();
		o2.start();
		
		try {
			o1.join();
			o2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("MAX_Priority > " + Thread.MAX_PRIORITY);
		System.out.println("MIN_Priority > " + Thread.MIN_PRIORITY);
		System.out.println("NORM_Priority > " + Thread.NORM_PRIORITY);
	}
}
//대문자출력 Thread
class output_Char extends Thread {
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++) {
			System.out.println(ch);
			for(long i=0; i<=1000000000L; i++) {} //시간때우기
		}
	}
}
//소문자출력 Thread
class output_Char2 extends Thread {
	@Override
	public void run() {
		for(char ch='a'; ch<='z'; ch++) {
			System.out.println(ch);
			for(long i=0; i<=1000000000L; i++) {} //시간때우기
		}
	}
}