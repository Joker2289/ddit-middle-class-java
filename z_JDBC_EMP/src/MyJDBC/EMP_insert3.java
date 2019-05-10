package MyJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
		# EMP 테이블에 새로운 데이터 추가하기
		- sawon_id는 현재 DB에 추가된 id값들 중 제일 큰 값에 1증가 된 값으로 한다 -> 	id 자동 증가 기능
		- id를 제외한 나머지 값들은 입력받아 처리
		- sawon_name의 중복검사도 진행한다
		
 */
public class EMP_insert3 {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			String driver		= "com.mysql.jdbc.Driver";
			String url			= "jdbc:mysql://localhost:3306/PATAGONIA?serverTimezone=Asia/Seoul"; 
			String user			= "root";
			String password		= "java2289";
			
			Class.forName(driver); 
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("접속 성공!!");
			stmt = conn.createStatement();
			
			String sql2 = "select max(sawon_id) from EMP";
			rs = stmt.executeQuery(sql2);
			//sawon_id의 최대값을 가져와서 1증가
			int num = 0;
			if(rs.next()) {
				//num = rs.getInt(1); 					// 1 컬럼위
				//num = rs.getInt("max_EMP"); 			//앨리어스가 있을때
				num = rs.getInt("max(sawon_id)"); 	//앨리어스가 없을때
			}
			num++;
			
			//sawon_name의 중복값 체크
			String sql3 = "select count(*) cnt from emp where sawon_name = ?"; //count(*) as cnt > as cnt 생략
			pstmt = conn.prepareStatement(sql3);
			String sawon_name = null;
			
			int count = 0;
			do {
				System.out.print("사원 이름 입력 : ");
				sawon_name = s.next();
				pstmt.setString(1, sawon_name);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count > 0) {
					System.out.println("사원 이름 " + sawon_name + "은 이미 있는 이름 입니다");
					System.out.println("다시 입력하세요");
					System.out.println();
				}
			} while(count > 0);
			
			//나머지 데이터 입력받기
			System.out.print("전화번호(moblie) 입력 : ");
			String mobile = s.next();
			
			System.out.print("이메일(email) 입력 : ");
			String email = s.next();

			
			String sql="";
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
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);					//sawon_id
			pstmt.setString(2, sawon_name);			//sawon_name
			pstmt.setString(3, mobile);				//mobile
			pstmt.setString(4, email);				//email
			pstmt.setInt(5, num);					//ins_id - sawon_id로 대체
			pstmt.setInt(6, num);					//upd_id - sawon_id로 대체
			
			int cnt = pstmt.executeUpdate();
			
			System.out.println("\n SQL : " + sql + "\n");
			if(cnt > 0) {
				System.out.println(sawon_name + "를 추가했습니다");
			}else {
				System.out.println(sawon_name + "를 추가하는데 실패 했습니다");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {		
			if(stmt!=null) try {stmt.close();}catch(SQLException e2) {}		
			if(conn!=null) try {conn.close();}catch(SQLException e2) {}		
			if(pstmt!=null) try {conn.close();}catch(SQLException e2) {}
			if(rs!=null) try {conn.close();}catch(SQLException e2) {}
		}
		
	}
}
