package singleton.emp.dao;

import java.util.List;

import singleton.emp.vo.EMPVO;

/**
 * 			실제 DB와 연결해서 SQL문을 수행하여 결과를 작성하여 service에 전달하는 DAO의 interface
 *
 * @author pjk
 *
 */
//Database Access Object
public interface EMPDao {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * DB에 insert할 자료가 저장된 MemberVO객체
	 * DB 작업이 성공하면 1이상의 값이 / 반환 실패면 0이 반환	(성공한 query문 개수)
	 * @param vo
	 * @return
	 */
	public int insertEMP(EMPVO vo);
	/**
	 * 사원 ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	 * sawon_id = 삭제할 사원 ID
	 * 성공 : 1 / 실패 : 0
	 * 
	 * @param memId
	 * @return
	 */
	public int deleteEMP(String sawon_id);
	/**
	 * 하나의 EMPVO자료를 이용하여 DB에 update하는 메서드
	 * vo  = update할 회원 정보가 들어있는 EMP 객체
	 * 성공 : 1 / 실패 : 0
	 * @param vo
	 * @return
	 */
	public int updateEMP(EMPVO vo);
	/**
	 * DB의 EMP 테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * EMPVO객체를 담고있는 List객체
	 * @return
	 */
	public List<EMPVO> getAllMemberList();
	/**
	 * 주어진 사원 ID가 존재하는지 여부를 알아내는 메서드
	 * sawon_id = 검색할 사원 ID
	 * 해당 사원 ID가 있으면 true / 없으면 false
	 * @param memId
	 * @return
	 */
	public boolean getEMP(String sawon_id);
	
	public List<EMPVO> getSearchEMP(EMPVO vo);
}
