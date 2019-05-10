package MyJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EMP_select2 {
/*
			문제1) 사용자로 부터 sawon_id 값을 입력받아 입력한 값보다 id값이 큰 자료들을 출력하시오
			
			문제2) id값을 2개 입력받아 작은 값부터 큰 값 사이의 자료를 출력하시오
 */
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; 
		
		try { 
			String driver		= "com.mysql.jdbc.Driver";
			String url			= "jdbc:mysql://localhost:3306/PATAGONIA?serverTimezone=Asia/Seoul"; 
			String user			= "root";
			String password		= "java2289";
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			
			System.out.println("접속 성공!!");
			System.out.println();
			
			Scanner s = new Scanner(System.in);
			String sql = "";
			//문제1) 
//			System.out.print("문제1) ID값 입력 : ");
//			int num = s.nextInt();
//			sql = "select * from EMP where sawon_id > " + num;
			
			//문제2)
			System.out.print("문제2) id max 값 입력 : ");
			int max = s.nextInt();
			System.out.print("문제2) id min 값 입력 : ");
			int min = s.nextInt();
			sql = "select * from EMP where sawon_id between " + min + " and " + max; 			
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("실행 Query문 " + sql);
			System.out.println("------- 실행 결과 -------");
			while(rs.next()) {
				
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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e2) {}			
			if(stmt!=null) try {stmt.close();}catch(SQLException e2) {}		
			if(conn!=null) try {conn.close();}catch(SQLException e2) {}		
		}
	}
}
