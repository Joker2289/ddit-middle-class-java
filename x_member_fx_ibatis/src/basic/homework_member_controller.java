package basic;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import ibatis.member.service.MemberServiceImpl;
import ibatis.member.vo.MemberVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class homework_member_controller implements Initializable {
	
	@FXML VBox vbox;
	
	//textfield
	@FXML TextField txt_id;
	@FXML TextField txt_name;
	@FXML TextField txt_tel;
	@FXML TextField txt_addr;
	
	//button
	@FXML Button btn_add;
	@FXML Button btn_update;
	@FXML Button btn_delete;
	@FXML Button btn_ok;
	@FXML Button btn_cancel;
	
	//tabel / column
	@FXML TableView<MemberVO> tb_member;
	@FXML TableColumn<MemberVO, String> col_id;
	@FXML TableColumn<MemberVO, String> col_name;
	@FXML TableColumn<MemberVO, String> col_tel;
	@FXML TableColumn<MemberVO, String> col_addr;
	
	
	// Service객체 변수를 선언한다.
	private MemberServiceImpl memService;
	
	public homework_member_controller() {
		memService = new MemberServiceImpl();
	}
	
	private static homework_member_controller h;
	public static homework_member_controller getInstance() {
		if(h == null) {
			h = new homework_member_controller();
		}
		return h;
	}
	
	//기본 리스트 생성 / 데이터 삽입
	ObservableList<MemberVO> list;
	
	String delivery;				//스위치의 동작을 실행할 메서드에 값을 배달할 변수
	boolean tb_switch;		//테이블 뷰의 활성화를 제어할 변수
	boolean btn_switch;		//버튼의 활설화를 제어할 변수
	boolean txt_switck;
	boolean error = false;			//에러메시지와 관련된 변수
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Table에 데이터 세팅
		List<MemberVO> mem_list = memService.getAllMemberList();
		list =  FXCollections.observableArrayList(mem_list);
		tb_member.setItems(list);
		
		//해당 Column에 데이터 입력하기
		col_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		col_tel.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		col_addr.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
		
		//on_off > 컨트롤들을 제어할 메서드
		// 1번째 파라미터값 : Button 	 제어
		// 2번째 파라미터값 : TableView 제어
		// 3번째 파라미터값 : TextField  제어
		on_off(true, false, true);
		
		//추가버튼
		btn_add.setOnAction(e->{
			delivery = "add";	
			
			txt_id.clear();
			txt_name.clear();
			txt_tel.clear();
			txt_addr.clear();
			
			on_off(false, true, false);	
			
			txt_id.setPromptText("아이디 입력");
			txt_name.setPromptText("이름 입력");
			txt_tel.setPromptText("전화번호 입력 ex) 010-XXXX-XXXX ");
			txt_addr.setPromptText("주소 입력");
			
		});
		
		//수정버튼
		btn_update.setOnAction(e->{
			//선택값이 없을때 필터링
			if(txt_id.getText().isEmpty()
					||txt_name.getText().isEmpty()
					||txt_tel.getText().isEmpty()
					||txt_addr.getText().isEmpty()) {
				errMsg("Error", "대상을 선택해 주세요");
				
				return;
			}
			
			delivery = "update";
			on_off(false, false, false);
			txt_id.setDisable(true);
			tb_member.requestFocus();
		});
		
		//삭제버튼
		btn_delete.setOnAction(e->{
			//선택값이 없을때 필터링
			if(txt_id.getText().isEmpty()
					||txt_name.getText().isEmpty()
					||txt_tel.getText().isEmpty()
					||txt_addr.getText().isEmpty()) {
				errMsg("Error", "대상을 선택해주세요");
				
				return;
			}
			
			delivery = "delete";
			on_off(false, true, false);
			txt_id.setDisable(true);
			tb_member.requestFocus();
		});
		
		//확인버튼
		btn_ok.setOnAction(e->{
			
			//error변수 초기화
			error=false;
			
			//버튼에서 배달받은 값을 파라미터에 넣어주고
			//해당하는 버튼의 메서드를 실행
			switch(delivery) {
				case "add":
					add_run();
					break;
				case "update":
					update_run();
					break;
				case "delete":
					delete_run();
					break;
				default:
					break;
			}
			
			//에러가 없을때 > 확인버튼이 올바르게 작동했을때
			if(!error) {
				on_off(true, false, true);
				txt_id.clear();
				txt_name.clear();
				txt_tel.clear();
				txt_addr.clear();
			} 
			
			
			
		});
		
		//취소버튼
		btn_cancel.setOnAction(e->{
			
			on_off(true, false, true);
			txt_id.clear();
			txt_name.clear();
			txt_tel.clear();
			txt_addr.clear();
			
			vbox.requestFocus();
			
		});
		
		
		
		//TableView 클릭시 발생 이벤트 추가
		tb_member.setOnMouseClicked(e->{
			//TableView에서 선택한 줄의 데이터를 얻는다
			MemberVO m = tb_member.getSelectionModel().getSelectedItem();
			txt_id.setText(m.getMem_id());
			txt_name.setText(m.getMem_name());
			txt_tel.setText(m.getMem_tel());
			txt_addr.setText(m.getMem_addr());
		});
		
		
		
	}
	
	
	//------------------------------------------------------------------------------------------------
	
	
	//버튼 활성화 switch 메서드 
	public void on_off(boolean btn_switch, boolean tb_switch, boolean txt_switch) {
		//버튼
		if(btn_switch == true) {
			btn_add.setDisable(false);
			btn_delete.setDisable(false);
			btn_update.setDisable(false);
			btn_ok.setDisable(true);
			btn_cancel.setDisable(true);
		} else {
			btn_add.setDisable(true);
			btn_delete.setDisable(true);
			btn_update.setDisable(true);
			btn_ok.setDisable(false);
			btn_cancel.setDisable(false);
		}
		
		//테이블뷰
		tb_member.setDisable(tb_switch);
		
		//텍스트필드
		txt_id.setDisable(txt_switch);
		txt_name.setDisable(txt_switch);
		txt_tel.setDisable(txt_switch);
		txt_addr.setDisable(txt_switch);
		
	}
	
	
	
	//확인 > add실행
	public void add_run() {
		//비어있는 text 필터링
		if(txt_id.getText().isEmpty()
				||txt_name.getText().isEmpty()
				||txt_tel.getText().isEmpty()
				||txt_addr.getText().isEmpty()) {
			errMsg("Error", "빈 항목이 존재합니다");
			return;
		}
		
		//전화번호 정규식 검사
		if(!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", txt_tel.getText())) {
			errMsg("Error", "ex) 010-XXXX-XXXX");
			txt_tel.requestFocus();  // 해당 객체에 Focus주기
			
			return;
		}
		System.out.println(memService.getMember(txt_id.getText()));
		//ID 중복검사
		if(memService.getMember(txt_id.getText()) == true) {
			errMsg("Error", "중복된 ID 입니다");
			txt_id.requestFocus();
			
			return;
		}
		
		//데이터 추가
		MemberVO vo = new MemberVO();
		vo.setMem_id(txt_id.getText());
		vo.setMem_name(txt_name.getText());
		vo.setMem_tel(txt_tel.getText());
		vo.setMem_addr(txt_addr.getText());
		
		//DB추가
		memService.insertMember(vo);
		//테이블추가
		list.add(vo);

		infoMsg("Sucess!!", txt_name.getText() + " 님 회원등록 완료 ");
		
	}
	
	
	//확인 > update실행
	public void update_run() {
		//비어있는 text 필터링
		if(txt_id.getText().isEmpty()
				||txt_name.getText().isEmpty()
				||txt_tel.getText().isEmpty()
				||txt_addr.getText().isEmpty()) {
			errMsg("Error", "빈 항목이 존재합니다");
			
			return;
		}
		
		//전화번호 정규식 검사
		if(!Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", txt_tel.getText())) {
			errMsg("Error", "ex) 010-XXXX-XXXX");
			txt_tel.requestFocus();  // 해당 객체에 Focus주기
			
			return;
		}
		MemberVO vo = new MemberVO();
		vo.setMem_id(txt_id.getText());
		vo.setMem_name(txt_name.getText());
		vo.setMem_tel(txt_tel.getText());
		vo.setMem_addr(txt_addr.getText());
		list.set(tb_member.getSelectionModel().getSelectedIndex(), vo);
		memService.updateMember(vo);
		
		
		infoMsg("Sucess!!", txt_name.getText() + " 님 데이터 수정 완료 ");

	}
	
	
	//확인 > delete 실행
	public void delete_run() {

		memService.deleteMember(txt_id.getText());
		list.remove(tb_member.getSelectionModel().getSelectedIndex());
		
		infoMsg("Sucess!!",  txt_name.getText() + "님 데이터 삭제 완료");
		
	}
	
	
	//에러메시지 메서드
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("Error");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
		
		//에러 발생과 동시에 error 발생여부 체크
		error = true;
	}
	
	
	//알림창
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("info");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
}