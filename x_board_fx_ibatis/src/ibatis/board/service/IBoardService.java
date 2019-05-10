package ibatis.board.service;

import java.util.List;

import ibatis.board.vo.BoardVO;


public interface IBoardService {
	
	public int insertBoard(BoardVO vo);
	
	public int deleteBoard(String board_no);
	
	public int updateBoard(BoardVO vo);
	
	//게시판 전체 목록 출력
	public List<BoardVO> getAllBoardList(); 

	//게시번호로 중복확인
	public boolean getBoard(String board_no);
	
	//자료 검색
	public List<BoardVO> getSearchBoard(BoardVO vo);
	
	public void auto_increment();
	
	public void up_view(BoardVO vo);
	
	public List<BoardVO> getSelectBoard(BoardVO vo);
}
