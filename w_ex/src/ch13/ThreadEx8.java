package ch13;

public class ThreadEx8 {
	public static void main(String[] args) {
		
		ThreadEx8_1 t1 = new ThreadEx8_1();
		ThreadEx8_2 t2 = new ThreadEx8_2();
		
		t1.setPriority(10);
		t2.setPriority(1);
		
		t1.start();
		t2.start();
	}
}

class ThreadEx8_1 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<=300; i++) {
			System.out.print("-");
			for(long x=0; x<=10000000; x++) {}
			if(i%100==0) {
				System.out.println();
			}
		}
	}
}

class ThreadEx8_2 extends Thread {
	@Override
	public void run() {
		for(int i=0; i<=300; i++) {
			System.out.print("|");
			for(long x=0; x<=10000000; x++) {}
			if(i%100==0) {
				System.out.println();
			}
		}
	}
}