package basic;

public class ThreadDemonTest {
	public static void main(String[] args) {
		AutoSaveThread autoSave = new AutoSaveThread();
		
		//데몬쓰레드로 설정하기(start()메서드 호출하기 전에 설정한다)
		autoSave.setDaemon(true);// 데몬쓰레드로 설정하기 > 다른 쓰레드가 종료되면 자동적을 종료된다
		autoSave.start();
		
		try {
			for(int i=1; i<=20; i++) {
				System.out.println("작업 " + i);
				Thread.sleep(1000);
			} 
		}catch (InterruptedException e) {
				e.printStackTrace();
		}
		
		
		System.out.println("메인쓰레드 종료");
	}
}

//자동저장 쓰레드(3초에 한번씩 저장)
class AutoSaveThread extends Thread{
	public void save() {
		System.out.println("작업 내용을 저장..");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			save();
		}
	}
}