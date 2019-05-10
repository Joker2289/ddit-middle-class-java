package ibatis.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import ibatis.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{
	//ibatis
	private SqlMapClient smc;
	private static BoardDaoImpl dao;
	
	// 생성자 private
	private BoardDaoImpl() {
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
	
	public static BoardDaoImpl getInstance() {
		if(dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}
	
	@Override
	public int insertBoard(BoardVO vo) {
		int cnt = 0;
		try {
			//리턴값 null (insert만)
			Object obj = smc.insert("boardTest.insertBoard", vo);
		
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteBoard(String board_no) {
		int cnt = 0;
		try {
			
			cnt = smc.delete("boardTest.deleteBoard", board_no);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO vo) {
		int cnt = 0;
		try {
			
			cnt = smc.update("boardTest.updateBoard", vo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			
			boardList = smc.queryForList("boardTest.getAllBoardList");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return boardList;
	}

	@Override
	public boolean getBoard(String board_no) {
		boolean check = false;
		try {
			
			int count = (int)smc.queryForObject("boardTest.getBoard", board_no);
			
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
	public List<BoardVO> getSearchBoard(BoardVO vo) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			boardList = smc.queryForList("boardTest.getSearchBoard", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public void auto_increment() {
		try {
			smc.queryForObject("boardTest.autoIncrement");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void up_view(BoardVO vo) {
		try {
			smc.update("boardTest.up_view", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BoardVO> getSelectBoard(BoardVO vo) {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
		 boardList	= smc.queryForList("boardTest.getSelectBoard", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList ;
	}

}
