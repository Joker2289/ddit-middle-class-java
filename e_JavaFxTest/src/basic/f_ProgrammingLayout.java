package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class f_ProgrammingLayout extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//HBox 컨테이너 생성
		HBox hbox = new HBox();
		
		//안쪽여백 설정하기
		//Insets객체는 값을 주는 순서가 위, 오른쪽, 아래, 왼쪽 순으로 설정한다
		//Insets : Padding을 줄때 Padding은 insets 타입만 받을수 있어서 객체로 준다
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.setSpacing(50); //컨트롤과 컨트롤 사이의 간격
		
		//한줄의 데이터 입력하는 컨트롤 : TextField객체
		TextField textField = new TextField();
		textField.setPrefWidth(200); //TextField의 너비 크기 설정
		
		Button button = new Button("눌러"); //버튼객체생성
		//button.setText("눌러");
		
		//HBox에 추가하기
		hbox.getChildren().addAll(textField, button);
		//hbox.getChildren().addAll(button, textField);
		
		//Scene객체 생성
		Scene scene = new Scene(hbox);
		
		primaryStage.setTitle("자바코드를 이용한 레이아웃 설정하기");
		primaryStage.setScene(scene); //스테이지에 scene셋팅
		primaryStage.show();	//스테이지 보여주기
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
