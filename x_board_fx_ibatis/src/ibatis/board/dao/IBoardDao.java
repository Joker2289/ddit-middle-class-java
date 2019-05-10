package ibatis.board.dao;

import java.util.List;

import ibatis.board.vo.BoardVO;


public interface IBoardDao {
	
	public int insertBoard(BoardVO vo);
	
	public int deleteBoard(String board_no);

	public int updateBoard(BoardVO vo);
	
	public List<BoardVO> getAllBoardList();
	
	public boolean getBoard(String board_no);

	public List<BoardVO> getSearchBoard(BoardVO vo);
	
	public void auto_increment();
	
	public void up_view(BoardVO vo);
	
	public List<BoardVO> getSelectBoard(BoardVO vo);
}
