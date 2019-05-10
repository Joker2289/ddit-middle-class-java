package basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;

public class l_ComboGugudanController implements Initializable{
	
	//각각의 입력한 객체의 값들이 자동으로 저장된다
	@FXML 
	private ComboBox<Integer> cmbDan;
	
	@FXML 
	private Button btnDan;
	
	@FXML 
	private TextArea txtResult;
	
	@Override //마지막에 initialize 를 실행
	public void initialize(URL location, ResourceBundle resources) {
		//이벤트나 초기화 등등의 로직을 넣어주면된다
		ObservableList<Integer> list = FXCollections.observableArrayList(2,3,4,5,6,7,8,9);
		
		cmbDan.setItems(list);
		cmbDan.setValue(2);
		
		//eventhandler를 람다식으로 넣음
		//appendText : 값을 추가
		//setText : 값을 수정 (덮어씀)
		//getValue : 선택된 값을 가져옴
		//방법1
		btnDan.setOnAction(e->{
			int dan = cmbDan.getValue(); //선택 단 가져오기
			txtResult.setText(""); //창을 초기화 시키기 위해 
			for(int i=1; i<10; i++) {
				txtResult.appendText(dan + " * " + i + " = " + (dan*i) + "\n");
			}
		});

	}
	
	//방법2
	@FXML public void buttonClicked(ActionEvent event) {
		int dan = cmbDan.getValue(); //선택 단 가져오기
		txtResult.setText(""); //창을 초기화 시키기 위해 
		for(int i=1; i<10; i++) {
			txtResult.appendText(dan + " * " + i + " = " + (dan*i) + "\n");
		}
	}
}
