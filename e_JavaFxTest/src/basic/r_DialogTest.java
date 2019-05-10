package basic;

import java.io.File;
import java.io.IOException;
import java.security.cert.Extension;
import java.util.Collection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class r_DialogTest extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox root = new HBox(10);
		root.setAlignment(Pos.CENTER);
		
		//파일열기 창
		Button btnFileOpen = new Button("Open FileChooser 실행");
		btnFileOpen.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			
			//확장자 별로 파일 구분하는 필터 등록하기
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3"),
					new ExtensionFilter("All Files", "*.*")
					);
			//Diallog 창에서 '열기'버튼을 누르면 선택한 파일 정보가 반환되고 '취소'버튼을 누르면 null이 반환 된다
			File selectFile = fileChooser.showOpenDialog(primaryStage);
			if(selectFile != null) {
				//이 영역에서 파일내용을 읽어오는 작업을 수행한다
				System.out.println("OPEN : " + selectFile.getPath());
			}
		});
		
		Button btnFileSave = new Button("SAVE FileChooser 실행");
		btnFileSave.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Allfiles", "*.*"));
			
			File selFile = fileChooser.showSaveDialog(primaryStage);
			
			if(selFile != null) {
				//이 영역에서 파일내용을 저장하는 작업을 수행한다
				System.out.println("SAVE : " + selFile.getPath());
			}
		});
		
		//폴더(디렉토리)만 선택하는 Dialog창
		Button btnDirectory = new Button("DirectoryChooser 실행");
		btnDirectory.setOnAction(e->{
			DirectoryChooser dirChooser = new DirectoryChooser();
			File selDir = dirChooser.showDialog(primaryStage);
			if(selDir != null) {
				System.out.println("Directory : " + selDir);
			}
		});
		
		Button btnPopup = new Button("Popup 실행");
		btnPopup.setOnAction(e->{
			//Popup창에 나타날 컨트롤들 구성시작
			HBox popRoot = new HBox();
			
			ImageView imgView = new ImageView();
			imgView.setImage(new Image(getClass().getResource("images/ok.png").toString())
			);
			imgView.setFitWidth(30);
			imgView.setFitHeight(30);
			
			Label lbMsg = new Label("메시지가 왔습니다");
			HBox.setMargin(lbMsg, new Insets(0, 5, 0, 5));
			
			popRoot.getChildren().addAll(imgView, lbMsg);
			//구성 끝
			
			//Popup객체 생성 후 위에서 구성한 컨트롤들 추가 후 보이기
			Popup popup = new Popup();
			//popup.setAnchorX(50); //좌표설정
			//popup.setAnchorY(50);
			popup.getContent().add(popRoot);
			popup.setAutoHide(true);
			popup.show(primaryStage);
		});
		
		//사용자가 만든 임의의 창 나타내기
		Button btnCustom = new Button("사용자 정의 창 실행");
		btnCustom.setOnAction(e->{
			//새창띄우기
			//1.Stage객체 생성
			Stage dialog = new Stage(StageStyle.UTILITY);
			
			//2.모달창 여부 설정
			//모달창은 자식창이 나타나면 부모창을 사용할 수 없다 > 그 창이 실행되면 다른 작업을 할수 없다
			dialog.initModality(Modality.APPLICATION_MODAL);
			
			//3.부모창 지정
			dialog.initOwner(primaryStage);
			dialog.setTitle("사용자 정의 창");
			
			//4.자식창에 나타날 컨테이너 객체 생성
			Parent parent = null;
			
			try {
				parent = FXMLLoader.load(getClass().getResource("myDialog.fxml"));
			} catch(IOException ee) {
				ee.printStackTrace();
			}
			
			//부모창에서 FXML로 만든 자식창의 컨트롤 객체 얻기
			TextField txtName = (TextField) parent.lookup("#txtName");
			PasswordField pass = (PasswordField) parent.lookup("#pass");
			Button btnOk = (Button) parent.lookup("#btnOk");
			
			btnOk.setOnAction(event->{
				System.out.println("ID : " + txtName.getText());
				System.out.println("PW : " + pass.getText());
			});
			
			Button btnCancel = (Button) parent.lookup("#btnCancel");
			btnCancel.setOnAction(event->{
				dialog.close();
			});
			
			//5.Scene객체 생성해서 컨테이너 객체 추가하기
			Scene scene = new Scene(parent);
			
			//6.Stage객체에 Scene	객체 추가
			dialog.setScene(scene);
			dialog.setResizable(false);//크기고정
			dialog.show();
		});
		
		root.getChildren().addAll(btnFileOpen, btnFileSave, btnDirectory, btnPopup, btnCustom);
		
		Scene scene = new Scene(root, 800, 100);
		
		primaryStage.setTitle("Dialog 창 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
