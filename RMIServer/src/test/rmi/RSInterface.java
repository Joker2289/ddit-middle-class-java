package test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI 인터페이스
 */
public interface RSInterface extends Remote { // 반드시 Remote 를 상속

	/**
	 * 클라이언트에서 호출할 RMI 메소드. 메시지를 콘솔에 출력한다.
	 * @param msg 프린트할 메시지
	 * @throws RemoteException 반드시 RemoteException 을 던지도록 선언되어야 한다.
	 */
	public void println(String msg) throws RemoteException;
	
}
