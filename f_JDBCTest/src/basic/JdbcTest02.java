package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest02 {
/*
		문제1) 사용자로 부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오
		
		문제2) lprod_id 값을 2개 입력 받아서 두 값 중 작은 값부터 큰 값 사이의 자료를 출력하시오
 */
	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; 
		
		try {
			
			
			//Oracle용(학원 내PC)
	        String driver			= "oracle.jdbc.driver.OracleDriver"; 
	        String url				= "jdbc:oracle:thin:@192.168.206.229:1521:xe";
	        String user				= "PC16_PC";
	        String password			= "java";
	        //String query 			= "select * from lprod";	
	        
	        System.out.println("접속 성공 !!");
	        
	        
	        //문제1)
//	        System.out.println("문제1. id값 입력 : " );
//	        Scanner s = new Scanner(System.in);
//	        int num = s.nextInt();
	        
//	        String query 			= "select * from lprod where lprod_id > " + num;	
	        
	        
	        //문제2)
	        Scanner s = new Scanner(System.in);
	        System.out.print("문제2. id max 입력 : " );
	        int max = s.nextInt();
	        System.out.print("문제2. id min 입력 : " );
	        int min = s.nextInt();
	        
	        String query 			= "select * from lprod where lprod_id between " + min + " and " + max;
	        
	        Class.forName(driver); 
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			System.out.println("실행 Query문 " + query);
			System.out.println("------- 실행 결과 -------");
			
			while(rs.next()) {
			
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
			if(rs!=null) try {rs.close();}catch(SQLException e2) {}			
			if(stmt!=null) try {stmt.close();}catch(SQLException e2) {}		
			if(conn!=null) try {conn.close();}catch(SQLException e2) {}		
		}
		
		
	}


}
