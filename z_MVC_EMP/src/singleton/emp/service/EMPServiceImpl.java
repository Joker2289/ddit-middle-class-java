package singleton.emp.service;

import java.util.List;

import singleton.emp.dao.EMPDaoImpl;
import singleton.emp.vo.EMPVO;

public class EMPServiceImpl implements EMPService{
	// 사용할 DAO의 객체변수를 선언한다.
	private EMPDaoImpl empDao;
	
	//MVC용
//	public EMPServiceImpl() {
//		empDao = new EMPDaoImpl();  // Dao객체 생성
//	}
	
	//Singleton 
	public EMPServiceImpl() {
		empDao = EMPDaoImpl.getInstance();
	}
	
	private static EMPServiceImpl service;
	public static EMPServiceImpl getInstance() {
		if(service == null) {
			service = new EMPServiceImpl();
		}
		return service;
	}
	
	
	
	// 각 메서드에서는 생성된 Dao객체를 이용하여
	// 작업에 맞는 Dao객체의 메서드를 호출한다.
	// 로직을 추가해 커스텀을 할 수 있디
	
	@Override
	public int insertEMP(EMPVO vo) {
		return empDao.insertEMP(vo);
	}

	@Override
	public int deleteEMP(String sawon_id) {
		return empDao.deleteEMP(sawon_id);
	}

	@Override
	public int updateEMP(EMPVO vo) {
		return empDao.updateEMP(vo);
	}

	@Override
	public List<EMPVO> getAllEMPList() {
		return empDao.getAllMemberList();
	}

	@Override
	public boolean getEMP(String sawon_id) {
		return empDao.getEMP(sawon_id);
	}



	@Override
	public List<EMPVO> getSearchEMP(EMPVO vo) {
		return empDao.getSearchEMP(vo);
	}

}
