package MyJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;

public class EMP_CRUD {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner s = new Scanner(System.in); 
	
	//초기 메뉴화면 출력
	public void Menu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. Data 추가");
		System.out.println("  2. Data 삭제");
		System.out.println("  3. Data 수정");
		System.out.println("  4. Data 출력");
		System.out.println("  5. Data 검색");
		System.out.println("  6. 프로그램 종료");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	//작업을 시작하는 메서드
	public void start(){
			int choice;
			do{
				Menu();
				choice = s.nextInt();
				switch(choice){
					case 1 :  //추가
						insert_EMP();
						break;
					case 2 :  //삭제
						delete_EMP();
						break;
					case 3 :  //수정
						update_EMP();
						break;
					case 4 :  //출력
						selectall_EMP();
						break;
					case 5 :  //1개 데이터 출력
						select_EMP();
						break;
					case 6 :  //프로그램 종료
						System.out.println("프로그램 종료");
						break;
					default :
						System.out.println("번호를 잘못 입력했습니다 다시입력하세요");
				}
			}while(choice!=6);
	}
	
	//추가
	public void insert_EMP() {
		System.out.println("Data 추가");
		System.out.println();
		System.out.print("이름 입력 : ");
		String sawon_name = s.next();
		System.out.print("전화번호 입력 : ");
		String mobile = s.next();
		System.out.print("이메일 입력 : ");
		String email = s.next();
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select max(sawon_id) from EMP";
			rs = stmt.executeQuery(sql);
			//sawon_id의 최대값을 가져와서 1증가
			int num = 0;
			if(rs.next()) {
				num = rs.getInt("max(sawon_id)"); 	//앨리어스가 없을때
			}
			num++;
			
			sql="";
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}
	
	//수정
	public void update_EMP() {
		System.out.println("Data 수정");
		System.out.println();
		
		System.out.print("수정할 사원 ID 입력 : ");
		int sawon_id = s.nextInt();
		
		//변경할 값 입력
		System.out.print("새로운 이름 : ");
		String sawon_name = s.next();
		System.out.print("새로운 전화번호 : ");
		String mobile = s.next();
		System.out.print("새로운 이메일 : ");
		String email = s.next();
		
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			
			//DB에 수정할 ID 존재유무 파악
			String sql = "select count(*) cnt from EMP where sawon_id=?";
			pstmt = conn.prepareStatement(sql);
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
			
			sql="";
			sql += "update EMP set ";
			sql += "sawon_name=?, ";
			sql += "mobile=?, ";
			sql += "email=?, ";
			sql += "upd_date=sysdate() ";
			sql += "where ";
			sql += "sawon_id=? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sawon_name);
			pstmt.setString(2, mobile);
			pstmt.setString(3, email);
			pstmt.setInt(4, sawon_id);
			
			cnt = pstmt.executeUpdate();
			
			System.out.println("\n SQL : " + sql + "\n");
			if(cnt > 0) {
				System.out.println(sawon_id + "를 수정 했습니다");
			}else {
				System.out.println(sawon_id + "를 수정하는데 실패 했습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	//삭제
	public void delete_EMP() {
		System.out.print("삭제할 사원 ID 입력 : ");
		int sawon_id = s.nextInt();
		
		try {
			conn = DBUtil.getConnection();
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	//전체출력
	public void selectall_EMP() {
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			String SQL = "select * from EMP";	//실행할 SQL문
			rs = stmt.executeQuery(SQL); 
			System.out.println("SQL : " + SQL);
			System.out.println("------- 실행 결과 -------");
			
			while(rs.next()) {
				//컬럼의 자료를 가져오는 방법
				// 1) rs.get자료형이름("컬럼명")
				// 2) rs.get자료형이름(컬럼번호) > 컬럼번호는 1부터 시작
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	//검색
	public void select_EMP() {
		System.out.print("검색할 ID 입력 : ");
		int sawon_id = s.nextInt();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from EMP where sawon_id = ?";	
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sawon_id); 
			
			rs = pstmt.executeQuery();
			
			
			System.out.println("SQL : " + sql);
			
			while(rs.next()) {
				
				System.out.println("------- 검색 결과 -------");
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
			if(rs.next() == false) {
				System.out.println("존재하지 않는 ID입니다");
			}
			System.out.println("출력 끝");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	// 사용한 자원을 반납하는 메서드
	public void disConnect(){
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	public static void main(String[] args) {
		EMP_CRUD emp = new EMP_CRUD();
		emp.start();
	}
}
