package test.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI ���� ���� Ŭ����.
 * RMI ���� ������ ���ؼ��� UnicastRemoteObject �� ����ϸ� ���� ���� ���� �� �ִٰ� �Ѵ�.
 * ���� ���� RSInterface �� �����ϴ� �͵� �߿�!
 * �� Ŭ������ RSMain.class �� RSMain_Stub.class �� �����.
 */
public class RSMain extends UnicastRemoteObject implements RSInterface {

	private static final long serialVersionUID = 1L;
	private static final String BIND_NAME = "rs";

	/**
	 * ���� �Լ�. ���ε��ϰ� ���� ����Ѵ�.
	 */
	public static void main(String[] args) {
		System.out.println("[RMI-Server] START");
		try {
			Naming.rebind(BIND_NAME, new RSMain());
			while (true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { Naming.unbind(BIND_NAME); } catch (Exception e) {}
		}
		
		System.out.println("[RMI-Server] EXIT");
	}

	/**
	 * UnicastRemoteObject �� ����ߴٸ� �ݵ�� 
	 * RemoteException �� �������� �����ڸ� ����� ��� �Ѵ�.
	 * @throws RemoteException
	 */
	public RSMain() throws RemoteException {
		super();
	}
	
	@Override
	public void println(String msg) throws RemoteException {
		// RMI ȣ���� �Ǹ� �޽����� ����Ʈ�Ѵ�.
		System.out.println("[RMI-SERVER] " + msg);
	}
}
