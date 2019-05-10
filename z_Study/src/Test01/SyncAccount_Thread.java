package Test01;
/*
		# 은행의 입출금을 Thread로 처리하는 예제
		  (Lock을 이용한 동기화 처리)
		
		# 동기화처리를 안했을때 어떤 문제점이 생기는지 알아보자
 */
public class SyncAccount_Thread {
	public static void main(String[] args) {
		SyncAccount sAcc = new SyncAccount();
		sAcc.setBalance(10000); //미리 10000원 입금
		
		BankThread bt1 = new BankThread(sAcc);
		BankThread bt2 = new BankThread(sAcc);
		
		bt1.start();
		bt2.start();
		
	}
}

//입출금을 관리하는 클래스(계좌)
class SyncAccount {
	private int balance; //잔액

	public synchronized int getBalance() {
		return balance;
	}
	
	public synchronized void setBalance(int balance) {
		this.balance = balance;
	}
	
	//입금처리 메서드
	public synchronized void deposit(int money) {
		balance += money;
	}
	
	//출금처리 메서드(true / false)
	//동기화 영역에서 호출하는 메서드도 동기화 처리를 해야한다
	public boolean withdraw(int money) {
		synchronized(this) {
			if(balance >= money) {
				for(int i=1; i<=1000000000; i++) { } //delay
				balance -= money;
				System.out.println("Method 안에서 balance = " + getBalance());
				return true;
			} else {
				return false;
			}
		}
	}
}

//은행업무 처리 메서드
class BankThread extends Thread {
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000); //6000원 출금
		System.out.println("Thread 안에서 result = " + result + ", balance = " + sAcc.getBalance());
	}
}

	