package homework;
import javax.swing.JOptionPane;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
	사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
	
	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
	5초안에 입력이 없으면 게임을 진것으로 처리한다.
	
	5초안에 입력이 완료되면 승패를 출력한다.
	
	결과예시)
		=== 결 과 ===
		컴퓨터 : 가위
		당 신 : 바위
		결 과 : 당신이 이겼습니다.
*/
public class RockPaperScissorsGame {
	public static boolean input_check = false;
	public static void main(String[] args) {
		CountDown cd = new CountDown();
		String[] box = { "바위", "가위", "보"};
		
		int index = (int)(Math.random() * 3); //컴퓨터 Pick 난수 발생 1~3
		String com = box[index]; //com에 저장 
		String user = null;		 //user변수 생성
		
		cd.start();
		do {
			user = JOptionPane.showInputDialog("가위 바위 보...!!"); //user입력
		}while(!user.equals("바위")&&!user.equals("가위")&&!user.equals("보"));
		input_check = true; //입력완료
		
		//판정하기
		String result = "";
		if(user.equals(com)) {
			result = "Draw";
		}else if( (user.equals("바위")&&com.equals("가위")) 
				|| (user.equals("가위")&&com.equals("보")) 
				|| (user.equals("보")&&com.equals("바위"))) {
			result = "Your Win!!!!!!!";
		}else {
			result = "You Lose ㅠㅠ";
		}
		
		System.out.println("------Result------");
		System.out.println("Com : " + com);
		System.out.println("You : " + user);
		System.out.println();
		System.out.println(result);
	}
}

class CountDown extends Thread {
	@Override
	public void run() {
		for(int i=5; i>=1; i--) {
			if(RockPaperScissorsGame.input_check == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);	//1초딜레이
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Time Over");
		System.out.println("You Lose");
		System.exit(0); //프로그램 강제종
	}
	
}