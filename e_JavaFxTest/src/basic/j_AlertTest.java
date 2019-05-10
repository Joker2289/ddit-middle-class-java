package basic;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class j_AlertTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Alert alertInformation = new Alert(AlertType.INFORMATION);
		alertInformation.setTitle("INFORMATION");
		alertInformation.setContentText("INFORMATION 창 입니다");
		alertInformation.showAndWait(); //Alert창 보이기 (창을 보여주고 기다린다)
		
		
		Alert alertErorr = new Alert(AlertType.ERROR);
		alertErorr.setTitle("ERROR");
		alertErorr.setContentText("ERROR 창 입니다");
		alertErorr.showAndWait(); 
		
		
		Alert alertWarning = new Alert(AlertType.WARNING);
		alertWarning.setTitle("WARNING");
		alertWarning.setContentText("WARNING 창 입니다");
		alertWarning.showAndWait();
		
		
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		alertConfirm.setTitle("CONFIRMATION");
		alertConfirm.setContentText("CONFRIMATION 창 입니다");
		
		//Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
		ButtonType confirmResult = alertConfirm.showAndWait().get();
		if(confirmResult == ButtonType.OK) {
			System.out.println("OK 버튼을 눌렀습니다");
		}else if(confirmResult == ButtonType.CANCEL) {
			System.out.println("취소 버튼을 눌렀습니다");
		}
		
		
		//Javascript의 prompt창과 같은 기능
		//'기본값'은 생략 가능
		TextInputDialog javaPrompt = new TextInputDialog("기본값");
		javaPrompt.setTitle("PROMPT");//창제목
		javaPrompt.setHeaderText("TextInputDialog 창 입니다"); //출력
		
		//창을 보이고 입력한 값을 읽어오기
		Optional<String> result = javaPrompt.showAndWait();
		String strResult = null; //입력값이 저장될 변수 선언
		
		//입력한 값이 있는지 검사(값 입력후 'OK'버튼눌렀는지 검사
		if(result.isPresent()) {
			strResult = result.get(); //값가져오기
		}
		System.out.println("값 : " + strResult);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
