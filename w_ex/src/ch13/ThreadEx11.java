package ch13;

import java.util.Iterator;
import java.util.Map;

public class ThreadEx11 {

	public static void main(String[] args) {

	}

}

class ThreadEx_11_1 extends Thread {
	ThreadEx_11_1(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadEx11_2 extends Thread {
	ThreadEx11_2(String name){
		super(name);
	}
	
	@Override
	public void run() {
		Map map = getAllStackTraces();
		Iterator it = map.keySet().iterator();
		
		int x=0;
		while(it.hasNext()) {
			Object obj = it.next();
			Thread t = (Thread) obj;
			StackTraceElement[] ste = (StackTraceElement[])(map.get(obj));
			
			
		}
	}
}