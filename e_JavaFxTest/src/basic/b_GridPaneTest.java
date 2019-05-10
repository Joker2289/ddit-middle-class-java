package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/*
		grid.setPrefSize(260, 150); 				> Pane의 너비 높이 설정
		grid.setPadding(new Insets(10));			> Pane의 안쪽여백 주기 (Padding 은 파라미터값으로  Insets 타입으로만 받을수 있다)
		grid.setHgap(10); 							> 셀들의 좌우 간격
		grid.setVgap(10); 							> 셀들의 상하 간격
		grid.add(label1, 1, 1);						> Pane에 컨트롤을 추가 할때 add메서드를 사용
		grid.setStyle("-fx-background-color:pink");	> 스타일 추가 (컨트롤 들도 가능)
		
 */

public class b_GridPaneTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GridPane grid = new GridPane();
		grid.setPrefSize(260, 150);
		grid.setPadding(new Insets(10));
		grid.setHgap(10); //셀들의 좌우 간격
		grid.setVgap(10); //셀들의 상하 간격
		
		Label label1 = new Label("ID : ");
		Label label2 = new Label("PW : ");
		
		TextField txtField1 = new TextField();
		//컨트롤의 글자색, 배경색, 배경이미지 등은 CSS를 이용하여 설정한다
		txtField1.setStyle("-fx-text-fill:gray; -fx-background-color:lightblue;");
		
		PasswordField passField = new PasswordField();
		passField.setStyle("-fx-text-fill:gray; -fx-background-color:lightgreen;");
		
		Button btn1 = new Button("Login");
		Button btn2 = new Button("Close");
		
		//GridPane에 컨트롤을 추가하는 방법
		//객체변수(grid).add(추가할 컨트롤, 칸번호, 행번호, colspan수, rowspan수);
		grid.add(label1, 1, 1);
		grid.add(label2, 1, 2);
		grid.add(txtField1, 3, 1, 3, 1);
		grid.add(passField, 3, 2, 3, 1);
		grid.add(btn1, 3, 4);
		grid.add(btn2, 4, 4);
		
		grid.setStyle("-fx-background-color:pink");
		
		Scene scene = new Scene(grid);
		
		primaryStage.setTitle("GridPane");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
