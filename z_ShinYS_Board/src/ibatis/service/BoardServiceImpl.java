package ibatis.service;

import java.util.List;

import ibatis.dao.BoardDaoImpl;
import ibatis.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	// 사용할 DAO의 객체변수를 선언한다.
	private BoardDaoImpl memDao;
	
	private static BoardServiceImpl service;
	
	private BoardServiceImpl() {
		memDao = BoardDaoImpl.getInstance();  // Dao객체 생성
	}
	
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
		return memDao.insert(vo);
	}

	@Override
	public int deleteBoard(BoardVO vo) {
		return memDao.delete(vo);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		return memDao.update(vo);
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		return memDao.getAll();
	}

	@Override
	public boolean getBoard(String memId) {
		return memDao.getId(memId);
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO vo) {
		return memDao.getSearch(vo);
	}

	@Override
	public void viewUp(BoardVO vo) {
		memDao.viewUp(vo);
	}


}
