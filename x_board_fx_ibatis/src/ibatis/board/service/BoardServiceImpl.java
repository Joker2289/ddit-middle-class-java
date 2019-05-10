package ibatis.board.service;

import java.util.List;

import ibatis.board.dao.BoardDaoImpl;
import ibatis.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	// 사용할 DAO의 객체변수를 선언한다.
	private BoardDaoImpl boardDao;
	public BoardServiceImpl() { 
		boardDao = BoardDaoImpl.getInstance(); // Dao 객체생성
	} 
	
	//singleton
	private static BoardServiceImpl service;
	public static BoardServiceImpl getInstance() {
		if(service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}
	
	
	// 각 메서드에서는 생성된 Dao객체를 이용하여
	// 작업에 맞는 Dao객체의 메서드를 호출한다.
	
	@Override
	public int insertBoard(BoardVO vo) {
		return boardDao.insertBoard(vo);
	}

	@Override
	public int deleteBoard(String board_no) {
		return boardDao.deleteBoard(board_no);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		return boardDao.updateBoard(vo);
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		return boardDao.getAllBoardList();
	}

	@Override
	public boolean getBoard(String board_no) {
		return boardDao.getBoard(board_no);
	}


	@Override
	public List<BoardVO> getSearchBoard(BoardVO vo) {
		return boardDao.getSearchBoard(vo);
	}


	@Override
	public void auto_increment() {
		boardDao.auto_increment();
	}

	@Override
	public void up_view(BoardVO vo) {
		boardDao.up_view(vo);
	}


	@Override
	public List<BoardVO> getSelectBoard(BoardVO vo) {
		return boardDao.getSelectBoard(vo);
	}

}
