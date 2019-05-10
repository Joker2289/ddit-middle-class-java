package singleton.emp.service;

import java.util.List;

import singleton.emp.vo.EMPVO;

/**
 * Service객체는 Dao에 설정된 메서드를 원하는 작업에 맞게 호출하여
 * 결과를 받아오고, 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 보통 Dao의 메서드와 같은 구조를 갖는다.
 * 
 * @author pjk
 *
 */
public interface EMPService {
	/**
	 * EMPVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param vo DB에 insert할 자료가 저장된 EMPVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertEMP(EMPVO vo);
	
	/**
	 * 사원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	 * 
	 * @param sawon_id 삭제할 사원ID
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteEMP(String sawon_id);
	
	/**
	 * 하나의 EMPVO자료를 이용하여 DB를 update하는 메서드
	 * 
	 * @param vo update할 사원 정보가 들어있는 EMPVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateEMP(EMPVO vo);
	
	/**
	 * DB의 EMP테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return EMPVO객체를 담고 있는 List객체
	 * 
	 */
	public List<EMPVO> getAllEMPList(); 
	
	/**
	 * 주어진 사원ID가 존재하는지 여부를 알아내는 메서드
	 * 
	 * @param sawon_id 검색할 사원ID
	 * @return 해당 사원ID가 있으면 true, 없으면 false
	 */
	public boolean getEMP(String sawon_id);
	
	/**
	 * EMPVO에 담긴 자료를 이용하여 사원을 검색하는 메서드 
	 * 
	 * @param vo 검색할 자료가 들어있는 EMPVO객체
	 * @return 검색된 결과를 담은 List
	 */
	
	public List<EMPVO> getSearchEMP(EMPVO vo);
}
