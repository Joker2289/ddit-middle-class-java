package basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class G_TcpFIleClient {
	//클라이언트는 서버에 접속하여 서버가 보내주는 파일을 설정 위치의 폴더에 저장한다
	private Socket socket;
	private InputStream in;
	private FileOutputStream fout;
	
	public void clientStart() {
		File file = new File("/Users/pjk/Documents/IO_test/dd.txt");	//저장할 파일 설정
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 다운로드 시작!!");
			
			fout = new FileOutputStream(file);
			in = socket.getInputStream();
			
			byte[] tmp = new byte[1024];	//한꺼번에 읽어와 전송할 데이터 저장변수 선언
			int length = 0;					
			while((length=in.read(tmp))!= -1) {
				fout.write(tmp, 0, length);
			}
			fout.flush();
			System.out.println("파일 다운로드 완료!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(in!=null) {
				try {in.close();} catch(IOException e2) {}
			}
			if(fout!=null) {
				try {fout.close();} catch(IOException e2) {}
			}
			if(socket!=null) {
				try {socket.close();} catch(IOException e2) {}
			}
		}
	} 
	
	public static void main(String[] args) {
		new G_TcpFIleClient().clientStart();
	}
}
