package MyJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/*
			# PreparedStatement 객체를 이용한 예제			
			- 하나의 쿼리를 재활용 할 수 있다
			- 속도가 빠르다 
			- PrepareStatement 객체는 보안이 강하다
			
			- Statement로 쿼리문을 작성한 소스에 해커들이 데이터를 막 집어넣었었던 사건이 있었다 
 */

public class EMP_insert2 {
	
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
			System.out.println("접속 성공!!");
			
			//SQL문 작성(데이터가 들어갈 자리에 ? 를 넣는다)
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
			sql += "?, ";
			sql += "?, ";
			sql += "?, ";
			sql += "?, ";
			sql += "now(), ";
			sql += "?, ";
			sql += "now(), ";
			sql += "? ";
			sql += ") ";
			
			//PreparedStatement 객체를 생성할 때 SQL문을 넣어서 생성한다
			pstmt = conn.prepareStatement(sql);
			
			
			// 쿼리문의 ? 자리에 들어갈 데이터를 세팅한다
			// 형식) pstmt.set자료형이름(물음표순번, 데이터); - 물음표 순번(1번부터 시작)
			pstmt.setInt(1, 2);					//sawon_id
			pstmt.setString(2, "XX");			//sawon_name
			pstmt.setString(3, "XX");			//mobile
			pstmt.setString(4, "XX");			//email
			pstmt.setInt(5, 2);				//ins_id
			pstmt.setInt(6, 2);				//upd_id

			//데이터를 세팅한 후 쿼리문을 실행한다
			int cnt = pstmt.executeUpdate();
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
