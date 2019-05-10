package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest01 {
/*
		JDBC를 이용한 데이터베이스 처리순서
		
		JDBC드라이버 로딩  >  해당 DB 접속  >  질의(SQL명령 수행)  >  질의 결과를 받아서 처리
		
		 1.JDBC 드라이버 로딩(오라클 기준)
		   * JDBC드라이버는 DB를 만든 회사에서 제공한다
		   * Class.forName("oracle.jdbc.driver.OracleDriver");
		   
		 2.접속하기 : 접속이 성공하면 Connection객체가 생성된다
		 DriverManager.getConnection()메서드를 이용한다
		 
		 3.질의 : Statement객체 또는 PrepareStatement객체를 이용하여 SQL문장을 실행한다
		 
		 4.결과 
		 
		 * SQL문이 select일 경우 > ReseltSet객체가 만들어 진다
		 		ReseltSet객체에는 select한 결과가 저장
		 * SQL문이 insert, update, delete일 경우 > 정수값을 반환한다
		 	 	(정수값은 보통 실행에 성공한 레코드 수를 말한다)
 */
	
	public static void main(String[] args) {
		
		//DB작업에 필요한 객체변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; //쿼리문 Select일 경우 필요
		
		try {
			 //Oracle용(학원 내PC)
	         //1.드라이버 로딩
	         String driver		= "oracle.jdbc.driver.OracleDriver"; 
	         Class.forName(driver);
	         
	         //2.DB접속(Connection 객체 생성)
	         String url				= "jdbc:oracle:thin:@192.168.206.229:1521:xe";
	         String user			= "PC16_PC";
	         String password		= "java";
	         
	        //MySQL용
	        //1.드라이버 로딩 
//	        String driver		= "com.mysql.jdbc.Driver";
//	        Class.forName(driver);
//			
//			//2.DB접속(Connection 객체 생성)
//			String url			= "jdbc:mysql://localhost:3306/JDBC_test?serverTimezone=Asia/Seoul"; 
//			String user			= "root";
//			String password		= "java2289";
			
	         
			conn = DriverManager.getConnection(url, user, password);
			
			System.out.println("접속 성공");
			
			//3.Statement객체 생성 > Connection객체를 이용
			stmt = conn.createStatement();
			
	         
	         
			//4.SQL문을 Statement객체를 이용하여 실행하고 결과를 ResultSet객체에 저장한다
			String query = "select * from lprod";	//실행할 SQL문
			rs = stmt.executeQuery(query); //SQL문이 select일 경우에는 executeQuery()메서드 사용
			// insert, update delect일경우 executeUpdate()매서드 사용
			
			//5.ResultSet 객체에 저장되어 있는 자료를 반복문고ㅓ next()메서드를 이용하여 차례대로 읽어와 처리
			System.out.println("실행 Query문 " + query);
			System.out.println("------- 실행 결과 -------");
			
			//rs.next()  >  ResultSet 객체의 데이터를 가리키는 포인터를 다음 레코드로 이동 시키고 그 곳에 자료가 있으면 true 없으면 false반환
			while(rs.next()) {
				//컬럼의 자료를 가져오는 방법
				// 1) rs.get자료형이름("컬럼명")
				// 2) rs.get자료형이름(컬럼번호) > 컬럼번호는 1부터 시작
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("-----------------------");
			}
			System.out.println("출력 끝");
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			//6.종료(사용했던 자원을 모두 반납)
			if(rs!=null) try {rs.close();}catch(SQLException e2) {}			// 자원반납은 매우 중요하다
			if(stmt!=null) try {stmt.close();}catch(SQLException e2) {}		// 일반 호스트에선 데이터양이 적어 문제될게 없지만
			if(conn!=null) try {conn.close();}catch(SQLException e2) {}		// 데이터량이 많을때에는 후에 큰문제를 일으킨다
		}
		
		
	}
}
