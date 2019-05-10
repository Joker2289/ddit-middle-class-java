package MyJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EMP_insert {
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt =  null; 
		
		try {
			String driver		= "com.mysql.jdbc.Driver";
			String url			= "jdbc:mysql://localhost:3306/PATAGONIA?serverTimezone=Asia/Seoul"; 
			String user			= "root";
			String password		= "java2289";
			
			Class.forName(driver); 
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			System.out.println("접속 성공!!");
			
			String sql = "";
			
			sql += "insert into EMP ";
			sql += "( ";
			sql += "sawon_id, ";
			sql += "sawon_name, ";
			sql += "mobile, ";
			sql += "email, ";
			sql += "ins_date, ";
			sql += "ins_id, ";
			sql += "upd_date, ";
			sql += "upd_id ";
			sql += ") ";
			sql += " values ";
			sql += "( ";
			sql += "'11', ";
			sql += "'JY', ";
			sql += "'010-9999-9999', ";
			sql += "'JY@naver.com', ";
			sql += "sysdate(), ";
			sql += "'9', ";
			sql += "sysdate(), ";
			sql += "'9' ";
			sql += ") ";
			
			int cnt = stmt.executeUpdate(sql);
			System.out.println("SQL : " + sql);
			System.out.println(cnt + " 개 행이 삽입 되었습니다");
			
		} catch(SQLException e) {
			e.printStackTrace();	
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e2) {}		
			if(conn!=null) try {conn.close();}catch(SQLException e2) {}		
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e2) {}	//pstmt로 할경우 닫아줘야함
		}
	}
}
