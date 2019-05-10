package basic.homework;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
	@FXML TableView<member> tb_member;
	@FXML TableColumn<member, String> col_id;
	@FXML TableColumn<member, String> col_name;
	@FXML TableColumn<member, String> col_tel;
	@FXML TableColumn<member, String> col_addr;
	
	//기본 리스트 생성 / 데이터 삽입
	ObservableList<member> list = FXCollections.observableArrayList(
			new member("JK", "박정권", "01044332289", "대전"),
			new member("bin", "빈지노", "01033228899", "뉴저지"),
			new member("kid", "키드밀리", "01058588282", "도쿄"),
			new member("pig", "스윙스", "01033332212", "정육점")
			);
	
	String delivery;				//스위치의 동작을 실행할 메서드에 값을 배달할 변수
	boolean tb_switch;		//테이블 뷰의 활성화를 제어할 변수
	boolean btn_switch;		//버튼의 활설화를 제어할 변수
	boolean txt_switck;
	boolean error = false;			//에러메시지와 관련된 변수
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Table에 데이터 세팅
		tb_member.setItems(list);
		
		//해당 Column에 데이터 입력하기
		col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
		col_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		col_addr.setCellValueFactory(new PropertyValueFactory<>("addr"));
		
		//on_off > 컨트롤들을 제어할 메서드
		// 1번째 파라미터값 : Button 	 제어
		// 2번째 파라미터값 : TableView 제어
		// 3번째 파라미터값 : TextFiel  제어
		on_off(true, false, false);
		
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
			txt_tel.setPromptText("전화번호 입력 (숫자만 가능) '-' 빼고 입력");
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
			on_off(false, false, false);
			
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
			
			on_off(true, true, true);
			txt_id.clear();
			txt_name.clear();
			txt_tel.clear();
			txt_addr.clear();
			
			vbox.requestFocus();
			
		});
		
		
		
		//TableView 클릭시 발생 이벤트 추가
		tb_member.setOnMouseClicked(e->{
			//TableView에서 선택한 줄의 데이터를 얻는다
			member m = tb_member.getSelectionModel().getSelectedItem();
			txt_id.setText(m.getId());
			txt_name.setText(m.getName());
			txt_tel.setText(m.getTel());
			txt_addr.setText(m.getAddr());
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
		
		// 전화번호 text에 숫자가 아닌 값입력시 필터링
		if(!Pattern.matches("^[0-9]+$", txt_tel.getText())) {
			errMsg("Error", "전화번호는 숫자만 입력하세요");
			txt_tel.requestFocus();  // 해당 객체에 Focus주기
			
			return;
		}
		
		//데이터 추가
		list.add(new member(txt_id.getText(),
				txt_name.getText(),
				txt_tel.getText(),
				txt_addr.getText() ));
		
		infoMsg("Sucess!!",  " Add to " + txt_name.getText() + " info ");
		
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
		//전화번호 text에 숫자가 아닌 값입력시 필터링
		if(!Pattern.matches("^[0-9]+$", txt_tel.getText())) {
			errMsg("Error", "전화번호는 숫자만 입력하세요");
			txt_tel.requestFocus();  // 해당 객체에 Focus주기
			
			return;
		}
		list.set(tb_member.getSelectionModel().getSelectedIndex(), 
				new member(txt_id.getText(),
				txt_name.getText(),
				txt_tel.getText(),
				txt_addr.getText() ));
		infoMsg("Sucess!!", " Update to " + txt_name.getText() + " info ");

	}
	
	
	//확인 > delete 실행
	public void delete_run() {
		if(tb_member.getSelectionModel().isEmpty()) {
			errMsg("Error", "삭제할 값을 선택해 주세요");
			return;
		}
		
		list.remove(tb_member.getSelectionModel().getSelectedIndex());
		infoMsg("Sucess",  txt_name.getText() + "님 데이터 삭제");
		
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
	
	
	//멤버 클래스
	public class member {
		
		private String id;
		private String name;
		private String tel;
		private String addr;
		
		//생성자
		public member(String id, String name, String tel, String addr) {
			super();
			this.id = id;
			this.name = name;
			this.tel = tel;
			this.addr = addr;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}
	}
}