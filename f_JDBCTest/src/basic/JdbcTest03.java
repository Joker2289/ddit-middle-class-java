package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest03 {
/*
		lprod_id : 101, lprod_gu : N101, lprod_nm : 농산물
		lprod_id : 102, lprod_gu : N102, lprod_nm : 수산물
		lprod_id : 103, lprod_gu : N103, lprod_nm : 축산물
		
		위 3개의 자료를 추가하기
 */
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt =  null; 
		
		try {
		
			//Oracle용(학원 내PC)
	        String driver			= "oracle.jdbc.driver.OracleDriver"; 
	        String url				= "jdbc:oracle:thin:@192.168.206.229:1521:xe";
	        String user				= "PC16_PC";
	        String password			= "java";
	        String query 			= "";
	        
	        System.out.println("접속 성공 !!");
	        
	        Class.forName(driver); 
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			
			query = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ " values(101, 'N101', '농산물') ";	
			int cnt = stmt.executeUpdate(query);			//executeUpdate()메서드는 실행에 성공한 레코드수를 반환함
			System.out.println("첫번째 반환값 : " + cnt);
			
			
			query = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
	        		+ " values(102, 'N102', '수산물') ";	
			cnt = stmt.executeUpdate(query);
			System.out.println("번째 반환값 : " + cnt);
			
			
			query = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ " values(103, 'N103', '축산물') ";	
			cnt = stmt.executeUpdate(query);
			System.out.println("세번째 반환값 : " + cnt);
				
			System.out.println();
			System.out.println("작업 끝");
			
			
			//PreparedStatement 객체를 이용한 예제			
			//하나의 쿼리를 재활용 할 수 있다
			//속도가 빠르다 
			//PrepareStatement 객체는 보안이 강하다
			
			//Statement로 쿼리문을 작성한 소스에 해커들이 데이터를 막 집어넣었었던 사건이 있었다 
//			
//			//SQL문 작성(데이터가 들어갈 자리에 ? 를 넣는다)
//			query = "insert into lprod(lprod_id, lprod_gu, lprod_nm) "
//					+ " values (?,?,?)";
//			
//			//PreparedStatement 객체를 생성할 때 SQL문을 넣어서 생성한다
//			pstmt = conn.prepareStatement(query);
//			
//			//쿼리문의 ? 자리에 들어가 데이터를 세팅한다
//			// 형식) pstmt.set자료형이름(물음표순번, 데이터);
//			// 		물음표 순번은 1부터 시작
//			pstmt.setInt(1, 101);
//			pstmt.setString(2, "N101");
//			pstmt.setString(3, "농산물");
//			
//			//데이터를 세팅한 후 쿼리문을 실행한다
//			int cnt = pstmt.executeUpdate();
//			System.out.println("첫번째 반환값 : " + cnt);
			
		} catch(SQLException e) {
			System.out.println("Driver 로딩 실패");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {		
			if(stmt!=null) try {stmt.close();}catch(SQLException e2) {}		
			if(conn!=null) try {conn.close();}catch(SQLException e2) {}		
			//if(pstmt!=null) try {pstmt.close();}catch(SQLException e2) {}	//pstmt로 할경우 닫아줘야함
		}
	}
	
}
