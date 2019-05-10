package MyJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 		# JDBC 데이터 베이스 처리 순서
 * 
 * 		JDBC driver 로딩	 >  해당 DB 접속  >  SQL명령수행(질의)  >  SQL명령 결과 처리   
 * 
 * 		1.JDBC 드라이버 로딩 
 * 		  
 * 		* JDBC 드라이버는 DB를 만든 회사에서 제공	
 * 		- Oracle  = ojdbc6.jar (6버전) 	
 * 		- Class.forName("oracle.jdbc.driver.OracleDriver");
 * 			
 * 		* MySQL	= mysql-connector-java-8.0.13.jar (8.0버전) (connector j)
 * 		- Class.forName("com.mysql.jdbc.Driver");
 * 
 * 
 * 		2.접속하기 : 접속이 성공하면 Connection 객체가 생성된다
 * 		
 * 		* DriverManager.getConnection()메서드를 이용한다
 * 		Connection conn = DriverManager.getConnection(url, user, password);
 * 
 * 
 * 		3.SQL명령(질의)
 * 		  
 * 		* Statemant 객체 또는 PreparedStatement 객체를 이용하여 SQL문장 실행
 * 		
 * 		Statement stmt = conn.createStatement();
 * 		PreparedStatement = conn.prepareStatement();
 * 
 * 		String sql = ""; 
 * 
 * 		ResultSet rs = stmt.executeQuery(sql);
 * 		or
 * 		int cnt = stmt.executeUpdate(sql);		
 * 
 * 
 * 		4.결과
 * 
 * 		* select 
 * 		- ResultSet 객체가 만들어 진다
 * 
 * 		* insert, update, delete
 * 		- Query 문이 성공한 record수를 정수값으로 반환한다
 * 
 * 
 * 		# 자료 불러오기
 * 		- 반복문과 .next() 메서드 이용  
 * 		- rs.getInt("컬럼명"); 		/ rs.getInt(Index 번호) - 1부터시작
 * 		- rs.getString("컬럼명"); 	/ rs.getString(Index 번호) - 1부터시작 
 * 		
 * 
 * @author pjk
 */

public class EMP_select {
	public static void main(String[] args) {
		//DB작업에 필요한 객체변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; //쿼리문 Select일 경우 필요
		
		try {
			//Oracle용(학원 내PC)
	        //1.드라이버 로딩
//	        String driver		= "oracle.jdbc.driver.OracleDriver"; 
//	        Class.forName(driver);
//	         
//	        //2.DB접속(Connection 객체 생성)
//	        String url			= "jdbc:oracle:thin:@192.168.206.229:1521:xe";
//	        String user			= "PC16_PC";
//	        String password		= "java";
	         
	        //MySQL용
	        //1.드라이버 로딩 
	        String driver		= "com.mysql.jdbc.Driver";
	        Class.forName(driver);
			
			//2.DB접속(Connection 객체 생성)
			String url			= "jdbc:mysql://localhost:3306/PATAGONIA?serverTimezone=Asia/Seoul"; 
			String user			= "root";
			String password		= "java2289";
			
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("접속 성공");
			
			//3.Statement객체 생성 > Connection객체를 이용
			stmt = conn.createStatement();
			
			//4.SQL문을 Statement객체를 이용하여 실행하고 결과를 ResultSet객체에 저장한다
			String query = "select * from EMP";	//실행할 SQL문
			rs = stmt.executeQuery(query); 
			// insert, update delect일경우 executeUpdate()매서드 사용
			
			//5.ResultSet 객체에 저장되어 있는 자료를 반복문 next()메서드를 이용하여 차례대로 읽어와 처리
			System.out.println("실행 Query문 " + query);
			System.out.println("------- 실행 결과 -------");
			
			//rs.next()  >  ResultSet 객체의 데이터를 가리키는 포인터를 다음 레코드로 이동 시키고 그 곳에 자료가 있으면 true 없으면 false반환
			while(rs.next()) {
				//컬럼의 자료를 가져오는 방법
				// 1) rs.get자료형이름("컬럼명")
				// 2) rs.get자료형이름(컬럼번호) > 컬럼번호는 1부터 시작
				System.out.println("sawon_id : " + rs.getInt("sawon_id"));
				System.out.println("sawon_name : " + rs.getString("sawon_name"));
				System.out.println("mobile : " + rs.getString("mobile"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("ins_date : " + rs.getString("ins_date"));
				System.out.println("ins_id : " + rs.getString("ins_id"));
				System.out.println("upd_date : " + rs.getString("upd_date"));
				System.out.println("upd_id : " + rs.getString("upd_id"));
				System.out.println("-----------------------");
			}
			System.out.println("출력 끝");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			//6.종료(사용했던 자원을 모두 반납)
			if(rs!=null) try {rs.close();}catch(SQLException e2) {}			// 자원반납은 매우 중요하다
			if(stmt!=null) try {stmt.close();}catch(SQLException e2) {}		// 일반 호스트에선 데이터양이 적어 문제될게 없지만
			if(conn!=null) try {conn.close();}catch(SQLException e2) {}		// 데이터량이 많을때에는 후에 큰문제를 일으킨다
		}
	}
}
