package ibatis.emp.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import ibatis.emp.vo.EMPVO;

public class EMPDaoImpl implements EMPDao{
	//ibatis
	private SqlMapClient smc;
	private static EMPDaoImpl dao;
	
	//생성자 private
	private EMPDaoImpl() { 
		Reader rd;
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch(IOException e) {
			System.out.println("SqlMapClient 객체생성 실패!!");
			e.printStackTrace();
		}
	} 
	
	public static EMPDaoImpl getInstance() {
		if(dao == null) {
			dao = new EMPDaoImpl();
		}
		return dao;
	}

	@Override
	public int insertEMP(EMPVO vo) {
		int cnt = 0;
		try {
			//리턴값 null (insert만)
			Object obj = smc.insert("empTest.insertEMP", vo);
			
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteEMP(String sawon_id) {
		int cnt = 0;
		try {
			
			cnt = smc.delete("empTest.deleteEMP", sawon_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateEMP(EMPVO vo) {
		int cnt = 0;
		try {
			
			cnt = smc.update("empTest.updateEMP", vo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public List<EMPVO> getAllMemberList() {
		List<EMPVO> list = new ArrayList<EMPVO>();
		try {
			
			list = smc.queryForList("empTest.getEMPAll");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return list;
	}

	@Override
	public boolean getEMP(String sawon_id) {
		boolean check = false;
		try {
			
			int count = (int)smc.queryForObject("empTest.getSearchEMP", sawon_id);
			
			if(count>0) {
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			check = false;
		} 
		
		return check;
	}

	@Override
	public List<EMPVO> getSearchEMP(EMPVO vo) {
		List<EMPVO> empList = new ArrayList<EMPVO>();
		try {
			
			empList = smc.queryForList("empTest.getSearchEMP", vo);
			
		} catch (SQLException e) {
			empList = null;
			e.printStackTrace();
		}
		
		return empList;
	}

}
