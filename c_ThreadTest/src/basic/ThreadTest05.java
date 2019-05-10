package basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {
	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("아무거나 입력");
		
		for(int i=10; i>=0; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000); //1초 마다 잠시 작업을 멈춤 
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(str);
	}
}
