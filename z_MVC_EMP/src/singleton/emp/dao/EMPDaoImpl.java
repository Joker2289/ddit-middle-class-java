package singleton.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import singleton.emp.vo.EMPVO;
import util.DBUtil_ResourceBundle;

public class EMPDaoImpl implements EMPDao{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//Singleton
	private static EMPDaoImpl dao;
	
	private EMPDaoImpl() { } //생성자 private
	
	public static EMPDaoImpl getInstance() {
		if(dao == null) {
			dao = new EMPDaoImpl();
		}
		return dao;
	}
	
	
	// 사용한 자원을 반납하는 메서드
	public void disConnect(){
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}

	@Override
	public int insertEMP(EMPVO vo) {
		int cnt = 0;
		try {
			conn = DBUtil_ResourceBundle.getConnection();
			
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
			
			System.out.println("sql : " + sql);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getSawon_id());		//sawon_id
			pstmt.setString(2, vo.getSawon_name());		//sawon_name
			pstmt.setString(3, vo.getMobile());			//mobile
			pstmt.setString(4, vo.getEmail());			//email
			pstmt.setString(5, vo.getSawon_id());		//ins_id
			pstmt.setString(6, vo.getSawon_id());		//upd_id
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally{
			disConnect();
		}
		return cnt;
	}

	@Override
	public int deleteEMP(String sawon_id) {
		int cnt = 0;
		try {
			conn = DBUtil_ResourceBundle.getConnection();
			String sql = "delete from EMP where sawon_id = ?";
			System.out.println("sql : " + sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sawon_id);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int updateEMP(EMPVO vo) {
		int cnt = 0;
		try {
			conn = DBUtil_ResourceBundle.getConnection();
			String sql = "";
			sql += "update EMP set ";
			sql += "sawon_name=?, ";
			sql += "mobile=?, ";
			sql += "email=?, ";
			sql += "upd_date=now() ";
			sql += "where ";
			sql += "sawon_id=? ";
			
			System.out.println("sql : " + sql);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getSawon_name());
			pstmt.setString(2, vo.getMobile());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getSawon_id());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally{
			disConnect();
		}
		return cnt;
	}

	@Override
	public List<EMPVO> getAllMemberList() {
		ArrayList<EMPVO> list = new ArrayList<EMPVO>();
		try {
			conn = DBUtil_ResourceBundle.getConnection();
			String sql = "select * from EMP";
			stmt = conn.createStatement();
			
			System.out.println("sql : " + sql);
			rs = stmt.executeQuery(sql);
			
			// 반복문 안에서는 가져온 레코드 하나 하나를
			// VO에 맵핑하고 이 VO를 List에 추가한다.
			while(rs.next()){
				EMPVO vo = new EMPVO();
				vo.setSawon_id(rs.getString("sawon_id"));
				vo.setSawon_name(rs.getString("sawon_name"));
				vo.setMobile(rs.getString("mobile"));
				vo.setEmail(rs.getString("email"));
				vo.setIns_date(rs.getString("ins_date"));
				vo.setIns_id(rs.getString("ins_id"));
				vo.setUpd_date(rs.getString("upd_date"));
				vo.setUpd_id(rs.getString("upd_id"));
			
				list.add(vo);
			}
			
			
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return list;
	}

	@Override
	public boolean getEMP(String sawon_id) {
		boolean check = false;
		try {
			conn = DBUtil_ResourceBundle.getConnection();
			String sql = "select count(*) cnt from EMP "
					+ "where sawon_id=?";
			
			System.out.println("sql : " + sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sawon_id);
			
			rs = pstmt.executeQuery();
			int count = 0;
			if(rs.next()){
				count = rs.getInt("cnt");
			}
			if(count>0){
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			check = false;
		} finally{
			disConnect();
		}
		return check;
	}

	@Override
	public List<EMPVO> getSearchEMP(EMPVO vo) {
		ArrayList<EMPVO> empList = new ArrayList<EMPVO>();
		try {
			conn = DBUtil_ResourceBundle.getConnection();
			String sql = "select * from EMP where 1=1 ";	//1=1 > true 
			if(vo.getSawon_id()!=null && !vo.getSawon_id().equals("")){
				sql += " and sawon_id = ? "; 
			}
			if(vo.getSawon_name()!=null && !vo.getSawon_name().equals("")){
				sql += " and sawon_name = ? ";
			}
			if(vo.getMobile()!=null && !vo.getMobile().equals("")){
				sql += " and mobile = ? ";
			}
			if(vo.getEmail()!=null && !vo.getEmail().equals("")){
				sql += " and email like '%' || ? || '%' ";
			}
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			if(vo.getSawon_id()!=null && !vo.getSawon_id().equals("")){
				 pstmt.setString(index++, vo.getSawon_id());
				 System.out.println(index);
				 System.out.println(vo.getSawon_id());
			}
			if(vo.getSawon_name()!=null && !vo.getSawon_name().equals("")){
				pstmt.setString(index++, vo.getSawon_name());
				System.out.println(index);
				System.out.println(vo.getSawon_name());
			}
			if(vo.getMobile()!=null && !vo.getMobile().equals("")){
				pstmt.setString(index++, vo.getMobile());
				System.out.println("mobile");
			}
			if(vo.getEmail()!=null && !vo.getEmail().equals("")){
				pstmt.setString(index, vo.getEmail());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				EMPVO empVo = new EMPVO();
				empVo.setSawon_id(rs.getString("sawon_id"));
				empVo.setSawon_name(rs.getString("sawon_name"));
				empVo.setMobile(rs.getString("mobile"));
				empVo.setEmail(rs.getString("email"));
				
				empList.add(empVo);
			}
			
			
		} catch (SQLException e) {
			empList = null;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return empList;
	}

}
