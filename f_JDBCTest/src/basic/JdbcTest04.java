package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {
/*
		LPROD 테이블에 새로운 데이터를 추가하기
		
		lprod_gu와 lprod_nm은 직접 입력받아 처리하고 
		lprod_id는 현재 lprod_id들 중 제일 큰 값 보다 1증가된 값으로 한다
		(번외 : lprod_gu 도 중복 되는지 검사한다)
		
 */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			//Oracle용(학원 내PC)
			String driver			= "oracle.jdbc.driver.OracleDriver"; 
			String url				= "jdbc:oracle:thin:@192.168.206.229:1521:xe";
			String user				= "PC16_PC";
			String password			= "java";
			
			System.out.println("접속 성공 !!");
			
			Class.forName(driver); 
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			
			String sql = "select max(lprod_id) from lprod";
			rs = stmt.executeQuery(sql);
			//lprod_id의 최대값을 가져와서 1증가 시키기
			int num = 0;
			
			if(rs.next()) {
				//num = rs.getInt(1); 	//컬럼위
				//num = rs.getInt("max_lprod"); //앨리어스가 있을때
				num = rs.getInt("max(lprod_id)"); //앨리어스가 없을때
			}
			num++;
			
			int count;
			String sql3 = "select count(*) cnt from lprod where lprod_gu = ? ";	// count(*) as cnt  >  as 생략
			pstmt = conn.prepareStatement(sql3);
			String gu = null;
			
			do{
				System.out.print("상품 분류코드(LPROD_GU) 입력 : ");
				gu = scan.next();
				
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				count=0;
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count > 0 ) {
					System.out.println("상품분류 코드 " + gu + "는 이미 있는 상품입니다");
					System.out.println("다시 입력하세요");
					System.out.println();
				}
			}while(count > 0);
			
			System.out.println("상품 분류명(LPROD_NM) 입력 : ");
			String nm = scan.next();
			
			String sql2 = "insert into lprod (lprod_id, lprod_gu, lprod_nm) values(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql2);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(gu + "를 추가했습니다");
			}else {
				System.out.println(gu + "를 추가하는데 실패 했습니다");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {		
			if(stmt!=null) try {stmt.close();}catch(SQLException e2) {}		
			if(conn!=null) try {conn.close();}catch(SQLException e2) {}		
			if(pstmt!=null) try {conn.close();}catch(SQLException e2) {}
			if(rs!=null) try {conn.close();}catch(SQLException e2) {}
		}
		
	}
}
