package util;
/*
		JDBC 드라이버를 로딩하고 Connection 객체를 생성하는 메서드로 구성된 클래스
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	// static 블럭 - 객체 생성할 필요없이 메서드명. 으로 호출할 수 있다
	// 싱글톤 패턴 (무분별하게 사용하기엔 권장하지 않는 사용법 이지만 DB접속때는 필요하다)
	static {	
		String driver			= "oracle.jdbc.driver.OracleDriver"; 
		try {
			//드라이버 등록
			Class.forName(driver); 
			System.out.println("드라이버 로딩 성공!!");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
		}
	}
	
	
	// 커넥션 객체를 생성하는 메서드
	//static > 객체생성을 하지않고 사용해야 할때
	//사용법  > conn = DBUtil.getConnection();
	public static Connection getConnection() {
		String url				= "jdbc:oracle:thin:@192.168.206.229:1521:xe";
		String user				= "PC16_PC";
		String password			= "java";
		try {
			return DriverManager.getConnection(url, user, password);
		}catch(SQLException e) {
			System.out.println("DB 연결 실패!!");
			
			return null;
		}
	}
}
