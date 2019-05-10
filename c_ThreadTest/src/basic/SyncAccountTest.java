package basic;
/*
		은행의 입출금을 Thread로 처리하는 예제
		(Lock을 이용한 동기화 처리)
*/

//동기화 처리를 안했을때 어떤 문제점이 생기는지 알아보자

public class SyncAccountTest {
	public static void main(String[] args) {
		SyncAccount sAcc = new SyncAccount();
		sAcc.setBalance(10000); // 미리 계좌에 10000원등록
		
		BankThread bth1 = new BankThread(sAcc);
		BankThread bth2 = new BankThread(sAcc);
		
		bth1.start();
		bth2.start();
	}
}

//은행의 입출금을 관리하는 클래스 - Account = 계좌
class SyncAccount {
	private int balance; //잔액이 저장될 변수

	public synchronized int getBalance() {
		return balance;
	}

	public synchronized void setBalance(int balance) {
		this.balance = balance;
	}
	
	//입금 처리 메서드
	public synchronized void deposit(int money) {
		balance += money;
	}
	
	//출금처리 메서드(true / false)
	//동기화 영역에서 호출하는 메서드도 동기화 처리를 해주어야 한다
	public boolean withdraw(int money) {
		synchronized (this) { //이렇게 sychronized 로 감싸거나 메서드 타입 앞에 synchronized 붙인다
			if(balance >= money) {	//잔액이 많을 경우
				for(int i=1; i<=1000000000; i++) { } //delay
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				return true;
			} else {
				return false;
			}
		}
	}
}

//은행 업무를 처리하는 쓰레드
class BankThread extends Thread {
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {
		boolean result = sAcc.withdraw(6000); //6000원 출금
		System.out.println("쓰레드 안에서 result = " +  result + ", balance = " + sAcc.getBalance());
	}
}
