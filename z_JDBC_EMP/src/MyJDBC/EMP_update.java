package MyJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class EMP_update {
	
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
			
			System.out.print("수정할 사원 ID 입력 : ");
			int sawon_id = s.nextInt();
			
			//DB에 수정할 ID 존재유무 파악
			String sql2 = "select count(*) cnt from EMP where sawon_id=?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, sawon_id);
			rs = pstmt.executeQuery();
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt==0) {
				System.out.println("존재하지 않는 아이디 입니다");
				return;
			}
			
			
			//변경할 값 입력
			System.out.print("새로운 이름 : ");
			String sawon_name = s.next();
			System.out.print("새로운 전화번호 : ");
			String mobile = s.next();
			System.out.print("새로운 이메일 : ");
			String email = s.next();
			
			
			String sql="";
			sql += "update EMP set ";
			sql += "sawon_name=?, ";
			sql += "mobile=?, ";
			sql += "email=?, ";
			sql += "upd_date=sysdate() ";
			sql += "where ";
			sql += "sawon_id=? ";
			
			pstmt = conn.prepareStatement(sql);
			
			System.out.println(sql);
			
			pstmt.setString(1, sawon_name);
			pstmt.setString(2, mobile);
			pstmt.setString(3, email);
			pstmt.setInt(4, sawon_id);
			
			cnt = pstmt.executeUpdate();
			if(cnt>0){
				System.out.println(sawon_id + " 회원의 정보를 수정했습니다.");
			}else{
				System.out.println(sawon_id + " 회원 수정 실패!!");
			}
		} catch(SQLException e) {
			e.printStackTrace();	
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e2) {}		
			if(conn!=null) try {conn.close();}catch(SQLException e2) {}		
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e2) {}	//pstmt로 할경우 닫아줘야함
			if(rs!=null) try {rs.close();}catch(SQLException e2) {}
		}
	}
}
