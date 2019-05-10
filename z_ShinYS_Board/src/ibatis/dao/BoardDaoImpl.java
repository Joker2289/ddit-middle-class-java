package ibatis.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import ibatis.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{
	
	//dao 싱글톤으로 생성하기
	private SqlMapClient smc;
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() {	 //생성자 private
		
		Reader rd;
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDaoImpl getInstance() {
		if(dao == null) {
			dao = new BoardDaoImpl();
		}
		
		return dao;	
	}
	//== 싱글톤 생성 종료.
	
	
	@Override
	public int insert(BoardVO vo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("BoardQuery.insert", vo);
			if(obj == null) {
				cnt = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	


	@Override
	public int delete(BoardVO vo) {
		int cnt = 0;
		
		try {
		
			cnt = smc.delete("BoardQuery.delete", vo.getBoard_no());
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	
	
	@Override
	public int update(BoardVO vo) {
		int cnt = 0;
		try {
			
			cnt = smc.update("BoardQuery.update", vo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public List<BoardVO> getAll() {
		List<BoardVO> List = new ArrayList<BoardVO>();
		try {
			List = smc.queryForList("BoardQuery.getAll");
		} catch (SQLException e) {
			List = null;
			e.printStackTrace();
		}	
		return List;
	}

	@Override
	public boolean getId(String memId) {
		boolean check = false;
		try {	
			int count = (int) smc.queryForObject("BoardQuery.getId", memId);
			if(count>0){
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			check = false;
		}
		return check;
	}
	
	
	
	@Override
	public List<BoardVO> getSearch(BoardVO vo) {
		List<BoardVO> List = new ArrayList<BoardVO>();
		try {
			
			List = smc.queryForList("BoardQuery.getSearch", vo);
			
		} catch (SQLException e) {
			List = null;
			e.printStackTrace();
		}
		return List;
	}

	@Override
	public void viewUp(BoardVO vo) {
		try {	
			
			smc.update("BoardQuery.viewup", vo);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
