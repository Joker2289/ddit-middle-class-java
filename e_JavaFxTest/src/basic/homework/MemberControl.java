package basic.homework;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MemberControl implements Initializable {

	boolean error = false;
	String sel_btn = null;
	
	//테이블 아이디값
	@FXML
	TableView<Member2> mem_table;
	@FXML
	TableColumn<Member2, String> mem_id2;
	@FXML
	TableColumn<Member2, String> mem_name2;
	@FXML
	TableColumn<Member2, String> mem_phone2;
	@FXML
	TableColumn<Member2, String> mem_addr2;
	
	//버튼 아이디값
	@FXML Button add_btn;
	@FXML Button edit_btn;
	@FXML Button del_btn;
	@FXML Button ok_btn;
	@FXML Button no_btn;
	
	//텍스트 아이디값
	@FXML TextField id_txt;
	@FXML TextField name_txt;
	@FXML TextField phone_txt;
	@FXML TextField addr_txt;
	
	//오브저블리스트 변수생성
	ObservableList<Member2> data;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TableView에 나타낼 데이터 구성하기
		
		
		// 테이블 아이디와 Member2 클래스의 변수명 매치시키기
		mem_id2.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		mem_name2.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		mem_phone2.setCellValueFactory(new PropertyValueFactory<>("mem_phone"));
		mem_addr2.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));

		// 오브저블어레이리스트에 항목 초기값으로 등록
		data = FXCollections.observableArrayList(
				new Member2("sys0130", "신유수", "010-9936-1451", "대전"),
				new Member2("sojin87", "소진", "02-123-4567", "서울"),
				new Member2("IU1", "아이유", "02-255-1111", "서울"),
				new Member2("jeni", "제니", "010-2233-4455", "제주"));

		// 테이블에 데이터 셋팅해주기
		mem_table.setItems(data);
	
		// 프로퍼티 표시
		id_txt.setPromptText("아이디 입력");
		name_txt.setPromptText("이름 입력");
		phone_txt.setPromptText("예) 010-1234-5678");
		addr_txt.setPromptText("주소 입력");
		
		// 텍스트상자 사용불가 만들기
		btn_Switch(1);
		txt_Switch(0);
		
		
		// 초기셋팅 끝
		//----------------------------------------------------------------------
		
		
		
		// 수정버튼 클릭이벤트
		edit_btn.setOnAction(e->{
			sel_btn = "edit";
			
			//선택 체크
			if(mem_table.getSelectionModel().getSelectedItem()==null) {
				errMsg("오류", "회원을 선택해 주세요");
				return;
			}
			
			// 텍스트상자 사용허용
				btn_Switch(0);
				txt_Switch(1);
				

						
			// TableView에서 선택한 줄의 데이터를 얻는다.
			Member2 mem = mem_table.getSelectionModel().getSelectedItem();
			
			// 텍스트필드에 데이터 입력
			id_txt.setText(mem.getMem_id());
			name_txt.setText(mem.getMem_name());
			phone_txt.setText(mem.getMem_phone());
			addr_txt.setText(mem.getMem_addr());
			
		});//수정버튼 종료
		
		
		
		// 추가버튼 클릭이벤트
		add_btn.setOnAction(e->{
			sel_btn = "add";
				
			// 텍스트상자 사용허용
				btn_Switch(0);
				txt_Switch(1);
			
		});//추가버튼 종료
		
		
		// 삭제버튼 클릭이벤트
		del_btn.setOnAction(e->{
			sel_btn = "del";
			
			//선택 체크
			if(mem_table.getSelectionModel().getSelectedItem()==null) {
				errMsg("오류", "회원을 선택해 주세요");
				return;
			}
			
			btn_Switch(0);
			//테이블 동결
			mem_table.setDisable(true);
			
			// TableView에서 선택한 줄의 데이터를 얻는다.
			Member2 mem = mem_table.getSelectionModel().getSelectedItem();
			
			// 텍스트필드에 데이터 입력
			id_txt.setText(mem.getMem_id());
			name_txt.setText(mem.getMem_name());
			phone_txt.setText(mem.getMem_phone());
			addr_txt.setText(mem.getMem_addr());
			
		});//삭제버튼 종료
				
				
				
		
		
		
		
		//취소 버튼
		no_btn.setOnAction(e->{
			id_txt.clear();
			name_txt.clear();
			phone_txt.clear();
			addr_txt.clear();
			
			btn_Switch(1);
			txt_Switch(0);
			
			sel_btn = null;
			error = false;
		});
		
		
		// 확인 버튼
		ok_btn.setOnAction(e->{
			
			error = false;
			
			switch(sel_btn) {
			case "edit":
				edit_Ok();
				break;
			case "add":
				add_Ok();
				break;
			case "del":
				del_Ok();
				break;
			}
			
			if(!error) {
				id_txt.clear();
				name_txt.clear();
				phone_txt.clear();
				addr_txt.clear();
				
				btn_Switch(1);
				txt_Switch(0);
				
				sel_btn = null;
			}
			
			
		});

		
	}//initialize 메서드 끝
	
	
	
	//----------------------------------------------
	// 확인버튼 각 메서드
	
	//수정
	public void edit_Ok() {
		if (id_txt.getText().isEmpty() || name_txt.getText().isEmpty() || phone_txt.getText().isEmpty()
				|| addr_txt.getText().isEmpty()) {
			errMsg("작업 오류", "빈 항목이 있습니다.");
			error=true;
			return;
		}

		if (!Pattern.matches("[0-9]{2,4}+[-]+[0-9]{3,4}+\\-+[0-9]{3,4}", phone_txt.getText())) {
			errMsg("데이터 오류", "연락처 형식에 맞춰 입력하세요\n예) 010-1234-1234");
			phone_txt.requestFocus(); // 해당 객체에 Focus주기
			error=true;
			return;
		}

		//data.set(수정할 obArrayList index , 변경할 데이터);
		data.set(mem_table.getSelectionModel().getSelectedIndex(), new Member2(id_txt.getText(),
				name_txt.getText(), phone_txt.getText(), addr_txt.getText()));
		
		infoMsg("수정완료",id_txt.getText()+"회원정보 수정완료");
	}
	
	
	//추가
	public void add_Ok() {
		if (id_txt.getText().isEmpty() || name_txt.getText().isEmpty() || phone_txt.getText().isEmpty()
				|| addr_txt.getText().isEmpty()) {
			errMsg("작업 오류", "빈 항목이 있습니다.");
			error=true;
			return;
		}

		if (!Pattern.matches("[0-9]{2,4}+[-]+[0-9]{3,4}+\\-+[0-9]{3,4}", phone_txt.getText())) {
			errMsg("데이터 오류", "연락처 형식에 맞춰 입력하세요\n예) 010-1234-1234");
			phone_txt.requestFocus(); // 해당 객체에 Focus주기
			error=true;
			return;
		}

		//data.set(수정할 obArrayList index , 변경할 데이터);
		data.add(new Member2(id_txt.getText(), name_txt.getText(), phone_txt.getText(), addr_txt.getText()));
		
		infoMsg("추가완료",id_txt.getText()+"회원정보 추가완료");
	}
	
	
	//삭제
	public void del_Ok() {
		

		//data.set(수정할 obArrayList index , 변경할 데이터);
		data.remove(mem_table.getSelectionModel().getSelectedIndex());
		
		infoMsg("삭제완료",id_txt.getText()+"회원정보 삭제완료");
	}
	
	
	
//----------------------------------------------------------------------
//  일반 호출 메서드들
	
	// 텍스트상자 사용여부 - 0:false   1:ture
	public void txt_Switch(int check) {
		if(check==0) {
			id_txt.setEditable(false);
			name_txt.setEditable(false);
			phone_txt.setEditable(false);
			addr_txt.setEditable(false);
		}else {
			id_txt.setEditable(true);
			name_txt.setEditable(true);
			phone_txt.setEditable(true);
			addr_txt.setEditable(true);
			
			id_txt.requestFocus();
		}	
	}
	
	
	// 버튼 사용여부 - 추가,수정,삭제 ||  0: 사용안함    1 : 사용함
	public void btn_Switch(int check) {
		if(check == 0) {
			add_btn.setDisable(true);
			edit_btn.setDisable(true);
			del_btn.setDisable(true);
			mem_table.setDisable(true);
			//mem_table.setSelectionModel(null);  //테이블뷰 잠그기 다른방법
			
			ok_btn.setDisable(false);
			no_btn.setDisable(false);
		}else {
			add_btn.setDisable(false);
			edit_btn.setDisable(false);
			del_btn.setDisable(false);
			mem_table.setDisable(false);
			//mem_table.setSelectionModel(???);
			
			ok_btn.setDisable(true);
			no_btn.setDisable(true);
		}
	}
	
	
	// 에러 메세지 확인창
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("오류");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}

	
	// 정보 메세지 확인창
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 확인");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}

	
	
	// 멤버클래스 VO로 활용
	public class Member2 {

		private String mem_id;
		private String mem_name;
		private String mem_phone;
		private String mem_addr;

		public Member2(String mem_id, String mem_name, String mem_phone, String mem_addr) {
			super();
			this.mem_id = mem_id;
			this.mem_name = mem_name;
			this.mem_phone = mem_phone;
			this.mem_addr = mem_addr;
		}

		public String getMem_id() {
			return mem_id;
		}

		public void setMem_id(String mem_id) {
			this.mem_id = mem_id;
		}

		public String getMem_name() {
			return mem_name;
		}

		public void setMem_name(String mem_name) {
			this.mem_name = mem_name;
		}

		public String getMem_phone() {
			return mem_phone;
		}

		public void setMem_phone(String mem_phone) {
			this.mem_phone = mem_phone;
		}

		public String getMem_addr() {
			return mem_addr;
		}

		public void setMem_addr(String mem_addr) {
			this.mem_addr = mem_addr;
		}

	}

}
