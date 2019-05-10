package singleton.emp.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import singleton.emp.service.EMPServiceImpl;
import singleton.emp.vo.EMPVO;

/*
	MVC패턴에서 Controller와 View역할을 담당하는 클래스
*/
public class EMPMain {
	// Service객체 변수를 선언한다.
	private EMPServiceImpl empService;
	private Scanner s;
	public EMPMain(){
		empService = new EMPServiceImpl();  // service객체 생성
		s = new Scanner(System.in);
	}
	
	//singleton
	private static EMPMain main;
	public static EMPMain getInstance() {
		if(main == null) {
			main = new EMPMain();
		}
		return main;
	}
	
	// 메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. Data 입력");
		System.out.println("  2. Data 삭제");
		System.out.println("  3. Data 수정");
		System.out.println("  4. 전체 Data 출력");
		System.out.println("  5. Data 검색");
		System.out.println("  6. 프로그램 종료");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	// 작업을 시작하는 메서드
	public void start(){
		int choice;
		do{
			displayMenu();
			choice = s.nextInt();
			switch(choice){
				case 1 :  // 자료 입력
					insertEMP();
					break;
				case 2 :  // 자료 삭제
					deleteEMP();
					break;
				case 3 :  // 자료 수정
					updateEMP();
					break;
				case 4 :  // 전체 자료 출력
					displayEMP();
					break;
				case 5 :  // 자료 검색
					searchEMP();
					break;
				case 6 :  // 작업 끝
					System.out.println("프로그램 종료");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	public void insertEMP() {
		boolean chk = false;
		String sawon_id;
		do{
			System.out.println();
			System.out.println("추가할 사원 정보를 입력하세요.");
			System.out.print("ID : ");
			sawon_id = s.next();
			
			chk = empService.getEMP(sawon_id);
			
			if(chk==true){
				System.out.println("ID가 " + sawon_id + "인 사원은 이미 존재합니다.");
				System.out.println("다시입력하세요");
			}
		}while(chk==true);
	
		System.out.print("이름 : ");
		String sawon_name = s.next();
		
		System.out.print("전화번호 : ");
		String mobile = s.next();
		
		s.nextLine(); // 입력 버퍼 비우기
		System.out.print("이메일 : ");
		String email = s.nextLine();
		
		// 입력받은 정보를 VO객체에 넣는다.
		EMPVO vo = new EMPVO();
		vo.setSawon_id(sawon_id);
		vo.setSawon_name(sawon_name);
		vo.setMobile(mobile);
		vo.setEmail(email);
		
		int cnt = empService.insertEMP(vo);
		
		if(cnt>0){
			System.out.println(sawon_id + "사원 추가 작업 성공");
		}else{
			System.out.println(sawon_id + "사원 추가 작업 실패");
		}
	}
	
	public void deleteEMP() {
		System.out.println();
		System.out.print("삭제할 사원ID를 입력하세요 : ");
		String sawon_id = s.next();
		
		int cnt = empService.deleteEMP(sawon_id);
		
		if(cnt>0){
			System.out.println("ID " + sawon_id + " 삭제 성공!!");
		}else{
			System.out.println(sawon_id + " 는 존재하지 않는 ID입니다");
		}
	}
	
	public void updateEMP() {
		System.out.println();
		String sawon_id = "";
		boolean chk = true;
		do{
			System.out.print("수정할 사원ID : ");
			sawon_id = s.next();
			
			chk = empService.getEMP(sawon_id);
			if(chk==false){
				System.out.println(sawon_id + " 는 존재하지 않는 ID 입니다");
				System.out.println();
			}
		}while(chk==false);
		
		System.out.println("수정할 내용을 입력하세요");
		System.out.print("새로운 이름 >> ");
		String sawon_name = s.next();
		
		System.out.print("새로운 전화번호 >> ");
		String mobile = s.next();
		
		System.out.print("새로운 메일 >> ");
		String email = s.next();
		
		EMPVO vo = new EMPVO();
		vo.setSawon_name(sawon_name);
		vo.setMobile(mobile);
		vo.setEmail(email);
		vo.setSawon_id(sawon_id);
		
		int cnt = empService.updateEMP(vo);
		if(cnt>0){
			System.out.println(sawon_id + "회원 정보 수정 완료");
		}else{
			System.out.println(sawon_id + "회원 정보 수정 실패!!");
		}
	}
	
	public void displayEMP() {
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("ID	이름		전화번호		이메일		추가날짜			추가자		수정날짜		수정자");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		List<EMPVO> empList = empService.getAllEMPList();
		if(empList.size()==0){
			System.out.println(" 출력할 Data가 없습니다.");
		}else{
			for(EMPVO vo : empList){
				System.out.println(vo.getSawon_id() + "\t" + vo.getSawon_name() + "\t" 
					+ "\t" + vo.getMobile() + "\t" + vo.getEmail() + "\t" + vo.getIns_date() + "\t"
					+ vo.getIns_id() + "\t" + vo.getUpd_date() + "\t" + vo.getUpd_id());
			}
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	}
	
	// 사원 정보를 검색하는 메서드
	public void searchEMP() {
		// 검색할 ID, 이름, 전화번호, 이메일 등을 입력하면
		// 입력한 정보만 사용하여 검색하는 기능을 구현하시오.
		// 이메일는 입력한 값이 포함만 되어도 검색 되도록 한다.
		// 입력을 하지 않을 자료는 엔터키로 다음 입력으로 넘긴다.
		s.nextLine();  // 입력버퍼 비우기
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		System.out.print("ID : ");
		String sawon_id = s.nextLine().trim();
		
		System.out.print("이름 : ");
		String sawon_name = s.nextLine().trim();
		
		System.out.print("전화번호 : ");
		String mobile = s.nextLine().trim();
		
		System.out.print("이메일 : ");
		String email = s.nextLine().trim();
		
		EMPVO vo = new EMPVO();
		vo.setSawon_id(sawon_id);
		vo.setSawon_name(sawon_name);
		vo.setMobile(mobile);
		vo.setEmail(email);
		
		// 입력한 정보로 검색한 내용을 출력하는 부분===================
		ArrayList<EMPVO> empList = (ArrayList<EMPVO>) empService.getSearchEMP(vo);

		System.out.println();
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println(" ID     이 름       전화번호        이메일");
		System.out.println("----------------------------------------");
		
		if(empList==null || empList.size()==0){
			System.out.println("검색한 자료가 하나도 없습니다.");
		}else{
			for(EMPVO empvo : empList){
				System.out.println(empvo.getSawon_id() + "    "
						+ empvo.getSawon_name() + "     "
						+ empvo.getMobile() + "     "
						+ empvo.getEmail());
			}
		}
		System.out.println("----------------------------------------");
	}
	public static void main(String[] args) {
		new EMPMain().start();
	}
}
