package basic;

public class WaitNotifyTest02 {
	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		ProducerThread pth = new ProducerThread(dataBox);
		ConsumerThread cth = new ConsumerThread(dataBox);
		pth.start();
		cth.start();
	}
}
//데이터를 공통으로 사용하는 클래스
class DataBox {
	private String data;
	
	//data가 null이 아닐때 data값을 반환하는 메서드
	public synchronized String getData() {
		if(data == null) {
			try {
				System.out.println(Thread.currentThread().getName() + " 	wait() 호출");
				wait();
				System.out.println(Thread.currentThread().getName() + " 	getData wait() 뒤..");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String returnData = data;
		System.out.println("읽어온 데이터 : " + returnData);
		data = null; //데이터를 저장 후 다시 null로 세팅 
		System.out.println(Thread.currentThread().getName() + " 	notify() 호출");
		notify();
		return returnData;
	}
	
	//data가 null일 경우 자료를 세팅하는 메서드
	public synchronized void setData(String data) {
		if(this.data != null) {
			try {
				System.out.println(Thread.currentThread().getName() +  " 	wait() 호출");
				wait();
				System.out.println(Thread.currentThread().getName() +  " 	setData wait() 뒤..	" + data);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.data = data;
		System.out.println("셋팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + " 	notify() 호출");
		notify();
	}
}

//데이터 세팅만 하는 Thread	
class ProducerThread extends Thread {
	private DataBox dataBox;

	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=4; i++) {
			String data = "Data-" + i;
			System.out.println("data.Box.setData(" + data + ")");
			dataBox.setData(data);
		}
	}
}

//데이터를 읽어만 오는 Thread
class ConsumerThread extends Thread {
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=4; i++) {
			String data = dataBox.getData();
			System.out.println("dataBox.getData() " + data);
		}
	}
}
