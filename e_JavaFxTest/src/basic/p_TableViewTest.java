package basic;

import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class p_TableViewTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//TableView에 나타낼 데이터 구성하기
		ObservableList<Member> data = FXCollections.observableArrayList(
				new Member("키드밀리", "KID MILLI", 26, "2222-2222", "New york"),
				new Member("로꼬", "LOCO", 30, "4433-2222", "Chicago"),
				new Member("박재범", "J-PARK", 31, "2222-2222", "New Jersey"),
				new Member("도끼", "DOK2", 35, "2781-0095", "Florida")
				);
		BorderPane root = new BorderPane();
		
		//TableView에 데이터 세팅하기
		//방법1 : TableView의 생성자 이용
		TableView<Member> table = new TableView<>(data);
		
		TableColumn<Member, String> nameCol = new TableColumn<>("Name");
		TableColumn<Member, String> korNameCol = new TableColumn<>("KOR_Name");
		
		//해당 컬럼에 나타날 데이터 입력하기 (출력할 객체의 멤버변수와 출력할 컬럼을 매칭시킨다)
		korNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("korName"));
		
		TableColumn<Member, String> engNameCol = new TableColumn<>("ENG_Name");
		engNameCol.setCellValueFactory(new PropertyValueFactory<Member, String>("engName"));
		
		nameCol.getColumns().addAll(korNameCol, engNameCol);
		
		TableColumn<Member, Integer> ageCol = new TableColumn<>("Age");
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		
		TableColumn<Member, String> telCol = new TableColumn<>("Tel");
		telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
		
		TableColumn<Member, String> addrCol = new TableColumn<>("Adress");
		addrCol.setCellValueFactory(new PropertyValueFactory<>("addr"));
		
		//생성된 각 컬럼들을 TableView에 추가한다
		table.getColumns().addAll(nameCol, ageCol, telCol, addrCol);
		
		//방법2
		//setItems()메서드 이용
		//table.setItems(data);
		//-----------------------------------------------
		GridPane grid = new GridPane();
		Text txt1 = new Text("KOR_Name");
		Text txt2 = new Text("ENG_Name");
		Text txt3 = new Text("Age");
		Text txt4 = new Text("Tel");
		Text txt5 = new Text("Adress");
		
		TextField txtKorName = new TextField();
		TextField txtEngName = new TextField();
		TextField txtAge = new TextField();
		TextField txtTel = new TextField();
		TextField txtAddr = new TextField();
		
		grid.add(txt1, 1, 1);
		grid.add(txt2, 2, 1);
		grid.add(txt3, 3, 1);
		grid.add(txt4, 4, 1);
		grid.add(txt5, 5, 1);
		
		grid.add(txtKorName, 1, 2);
		grid.add(txtEngName, 2, 2);
		grid.add(txtAge, 3, 2);
		grid.add(txtTel, 4, 2);
		grid.add(txtAddr, 5, 2);
		grid.setVgap(5);
		grid.setHgap(10);
		grid.setPadding(new Insets(10, 0, 10 ,0));
		
		
		//---------------------------------------------------------
		
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		
		Button btnAdd = new Button("Add");
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(txtKorName.getText().isEmpty()
					|| txtEngName.getText().isEmpty()
					|| txtAge.getText().isEmpty()
					|| txtTel.getText().isEmpty()
					|| txtAddr.getText().isEmpty()) {
					
					errMsg("ERROR", "DATA is empty");
					return;
				}if(!Pattern.matches("^[0-9]+$", txtAge.getText())) {
					errMsg("ERROR", "DATA is not Integer");
					txtAge.requestFocus(); //해당객체에 포커스 주기
					return;
				}
				data.add(new Member(txtKorName.getText(),
						txtEngName.getText(),
						Integer.parseInt(txtAge.getText()),
						txtTel.getText(),
						txtAddr.getText()));
				infoMsg("Result", "Mr." + txtKorName.getText() + "of info add Sucess");
				
				txtKorName.clear();
				txtEngName.clear();
				txtAge.clear();
				txtTel.clear();
				txtAddr.clear();
				
			}
		});
		
		Button btnEdit = new Button("Edit");
		btnEdit.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(txtKorName.getText().isEmpty()
					|| txtEngName.getText().isEmpty()
					|| txtAge.getText().isEmpty()
					|| txtTel.getText().isEmpty()
					|| txtAddr.getText().isEmpty()) {
					
					errMsg("ERROR", "DATA is empty");
					return;
				}if(!Pattern.matches("^[0-9]+$", txtAge.getText())) {
					errMsg("ERROR", "DATA is not Integer");
					txtAge.requestFocus(); //해당객체에 포커스 주기
					return;
				}
				data.set(
						table.getSelectionModel().getSelectedIndex(),	//수정할 인덱스 정보
						new Member(txtKorName.getText(),
						txtEngName.getText(),
						Integer.parseInt(txtAge.getText()),
						txtTel.getText(),
						txtAddr.getText()));
				infoMsg("Result", "Mr." + txtKorName.getText() + "of info edit Sucess");
				
				txtKorName.clear();
				txtEngName.clear();
				txtAge.clear();
				txtTel.clear();
				txtAddr.clear();
			}
		});
		
		Button btnDel = new Button("Delete");
		btnDel.setOnAction(event->{
			if(table.getSelectionModel().isEmpty()) {
				errMsg("Error", "삭제할 자료를 선택한 후 삭제하세요");
				return;
			}
			
			data.remove(table.getSelectionModel().getSelectedIndex());
			
			infoMsg("Result", "Mr." + txtKorName.getText() + "of info delete Sucess");
			
			txtKorName.clear();
			txtEngName.clear();
			txtAge.clear();
			txtTel.clear();
			txtAddr.clear();
		});
		
		Button btnTest1 = new Button("Attribute1");
		btnTest1.setOnAction(e->{
			//TextField, TextArea등 문자를 입력하는 객체를 ReadyOnly로 설정하는 메서드 > setEdittable()
			//이 메서드에 true를 설정하면 '입력가능' false를 설정하면 '읽기전용'
			txtKorName.setEditable(false);
			txtEngName.setEditable(false);
			
			//객체를 활성화 또는 비활성화 시키는 메서드 > setDisable()
			//이 메서드에 true를 설정하면 '비활설화' false를 설정하면 '활설화'
			btnAdd.setDisable(true);
			btnEdit.setDisable(true);
			
			//입력상자에 흐릿하게 나타내는 메시지
			txtKorName.setPromptText("한글이름 입력"); //입력상자에  흐릿하게 나타나는 메시지
			txtAddr.requestFocus(); //포커스 주기
		});
		
		Button btnTest2 = new Button("Attribute2");
		btnTest2.setOnAction(e->{
			txtKorName.setEditable(true);
			txtEngName.setEditable(true);
			
			btnAdd.setDisable(false);
			btnEdit.setDisable(false);
		});
		
		//TableView를 클릭했을 때의 처리
		table.setOnMouseClicked(e->{
			//TableView에서 선택한 줄의 데이터를 얻는다
			Member mem = table.getSelectionModel().getSelectedItem();
			
			txtKorName.setText(mem.getKorName());
			txtEngName.setText(mem.getEngName());
			txtAge.setText("" + mem.getAge());
			txtTel.setText(mem.getTel());
			txtAddr.setText(mem.getAddr());
		});
		
		vbox.getChildren().addAll(btnAdd, btnEdit, btnDel, btnTest1, btnTest2);
		root.setCenter(table);
		root.setRight(vbox);
		root.setBottom(grid);
		root.setPadding(new Insets(10));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Table View 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	public void errMsg(String headerText,String msg) {
	      Alert errAlert = new Alert(AlertType.ERROR);
	      errAlert.setTitle("Error");
	      errAlert.setHeaderText(headerText);
	      errAlert.setContentText(msg);
	      errAlert.show();
	   }
	   
	   public void infoMsg(String headerText,String msg) {
	      Alert infoAlert = new Alert(AlertType.INFORMATION);
	      infoAlert.setTitle("Info Check");
	      infoAlert.setHeaderText(headerText);
	      infoAlert.setContentText(msg);
	      infoAlert.show();
	   }
	
	public static void main(String[] args) {
		launch(args);
	}
}

class Member {
	private String korName;		//한글이름
	private String engName;		//영문이름
	private int age;			//나이
	private String tel;			//전화번호
	private String addr;		//주소
	
	public Member(String korName, String engName, int age, String tel, String addr) {
		super();
		this.korName = korName;
		this.engName = engName;
		this.age = age;
		this.tel = tel;
		this.addr = addr;
	}

	public String getKorName() {
		return korName;
	}

	public void setKorName(String korName) {
		this.korName = korName;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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