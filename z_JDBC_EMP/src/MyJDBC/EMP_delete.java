package MyJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EMP_delete {
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt =  null; 
		ResultSet rs = null;
		
		try {
			String driver		= "com.mysql.jdbc.Driver";
			String url			= "jdbc:mysql://localhost:3306/PATAGONIA?serverTimezone=Asia/Seoul"; 
			String user			= "root";
			String password		= "java2289";
			
			Class.forName(driver); 
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("접속 성공!!");
			
			Scanner s = new Scanner(System.in);
			
			System.out.print("삭제할 사원 ID 입력 : ");
			int sawon_id = s.nextInt();
			
			String sql="";
			sql += "delete from EMP ";
			sql += "where ";
			sql += "sawon_id=? ";
			
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setInt(1, sawon_id);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0){
				System.out.println(sawon_id + "번 회원의 정보를 삭제했습니다.");
			}else{
				System.out.println(sawon_id + "번은 존재하지 않는 ID 입니다");
			}
		} catch(SQLException e) {
			e.printStackTrace();	
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e2) {}		
			if(conn!=null) try {conn.close();}catch(SQLException e2) {}		
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e2) {}	
			if(rs!=null) try {rs.close();}catch(SQLException e2) {}
		}
	}
}
