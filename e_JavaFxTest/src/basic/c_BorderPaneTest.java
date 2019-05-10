package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class c_BorderPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPrefSize(300, 200); //Size : width와 heigth를 한번에 세팅
		
		ToolBar toolBar = new ToolBar(
				new Button("One"),
				new Button("Two"),
				new Button("Three")
		);
		
		TextArea txtArea = new TextArea(); //여러줄 입력 가능
		
		root.setTop(toolBar); // Top영역에 ToolBar 추가
		root.setCenter(txtArea); // Center영역에 TextArea 추가
		
		BorderPane bottom = new BorderPane(); //또 다른 BorderPane 생성
		bottom.setPadding(new Insets(10)); //여백
		
		TextField txtField = new TextField(); // 한줄입력
		Button btn1 = new Button("OK");
		bottom.setCenter(txtField);
		bottom.setRight(btn1);
		
		root.setBottom(bottom); //root컨테이너의 bottom영역에 bottom컨테이너 추가
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("BorderPane");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
