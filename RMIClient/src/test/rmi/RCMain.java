package test.rmi;

import java.rmi.Naming;

/**
 * 클라이언트 메인 클래스.
 */
public class RCMain {

	/**
	 * 등록된 이름으로 RSInterface 를 가져오고 RMI 메소드 호출
	 */
	public static void main(String[] args) {
		try {
			String url = "rmi://127.0.0.1/rs";
			RSInterface rs = (RSInterface)Naming.lookup(url);
			for (int i = 0; i < 10; i++) {
				rs.println(i + "번째 클라이언트 호출");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
