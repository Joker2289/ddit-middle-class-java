package test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI �������̽�
 */
public interface RSInterface extends Remote { // �ݵ�� Remote �� ���

	/**
	 * Ŭ���̾�Ʈ���� ȣ���� RMI �޼ҵ�. �޽����� �ֿܼ� ����Ѵ�.
	 * @param msg ����Ʈ�� �޽���
	 * @throws RemoteException �ݵ�� RemoteException �� �������� ����Ǿ�� �Ѵ�.
	 */
	public void println(String msg) throws RemoteException;
	
}
