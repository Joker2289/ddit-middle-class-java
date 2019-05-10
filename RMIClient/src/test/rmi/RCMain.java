package test.rmi;

import java.rmi.Naming;

/**
 * Ŭ���̾�Ʈ ���� Ŭ����.
 */
public class RCMain {

	/**
	 * ��ϵ� �̸����� RSInterface �� �������� RMI �޼ҵ� ȣ��
	 */
	public static void main(String[] args) {
		try {
			String url = "rmi://127.0.0.1/rs";
			RSInterface rs = (RSInterface)Naming.lookup(url);
			for (int i = 0; i < 10; i++) {
				rs.println(i + "��° Ŭ���̾�Ʈ ȣ��");
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
