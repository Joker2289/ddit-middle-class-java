package Test01;

import javax.swing.JOptionPane;

public class Thead_InputData {
	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("입력");
		
		for(int i=10; i>=0; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000); //1초씩 잠시 작업을 멈춤
			} catch(InterruptedException e)	{
				e.printStackTrace();
			}
		}
		System.out.println("입력값 > " + str);
	}

}
