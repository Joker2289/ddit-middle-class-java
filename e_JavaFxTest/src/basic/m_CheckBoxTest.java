package basic;

import javax.sound.midi.Receiver;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class m_CheckBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Rectangle rect = new Rectangle(90, 30); //사각형 그리기
		rect.setArcHeight(10); 					//꼭지점 둥글게만들기
		rect.setArcWidth(10);
		rect.setFill(Color.rgb(41, 41, 41));	//내부 색넣기
		
		String[] names = new String[] {"Security", "Project", "Chart"};
		Image[] images = new Image[names.length];
		ImageView[] icons = new ImageView[names.length];
		
		CheckBox[] chkBoxs = new CheckBox[names.length];
		
		for(int i=0; i<names.length; i++) {
			//출력할 이미지 읽어오기
			//images[i]에 대입 > images[i]를 img에 대입
			final Image img = images[i] =  new Image(
					getClass().getResourceAsStream("images/" + names[i] + ".png")
					);
			
			//이미지를 출력하는 객체 생성
			//Image 와 ImageView는 세트다
			final ImageView icon = icons[i] = new ImageView();
			final CheckBox cb = chkBoxs[i] = new CheckBox(names[i]);
			
			//CheckBox를 클릭해서 값이 변경되었을 때의 이벤트처리
			cb.selectedProperty().addListener(
					new ChangeListener<Boolean>() {
						
						//oldValue : 체크안된 값, newValue : 체크된 값
						@Override
						public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
							if(newValue == true) {
								icon.setImage(img);
							}else {
								//ImageView에서 이미지 삭제
								icon.setImage(null);
							}
						}
					}
			);
		}
		
		Button btnOk = new Button("OK");
		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(chkBoxs[1].isSelected() == true) {
					showInfo(chkBoxs[1].getText() + "체크");
				} else {
					showInfo(chkBoxs[1].getText() + "체크 해제");
				}
				
				//CheckBox의 check여부 세팅하기
				chkBoxs[0].setSelected(!chkBoxs[1].isSelected());
			}
		});
		
		//spacing = 5
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(chkBoxs);
		vbox.getChildren().add(btnOk);
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(icons);
		hbox.setPadding(new Insets(0, 0, 0, 5));
		
		//StackPane은 컨트롤들을 쌓아놓는 방식으로 배치하는 컨테이너
		StackPane stack = new StackPane();
		stack.getChildren().addAll(rect, hbox);
		StackPane.setAlignment(rect, Pos.TOP_CENTER);
		
		HBox root = new HBox();
		root.setSpacing(40);
		root.setPadding(new Insets(20, 10, 10, 20));
		root.getChildren().addAll(vbox, stack);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("CheckBox 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void showInfo(String msg) {
		Alert alertInformation = new Alert(Alert.AlertType.INFORMATION);
		alertInformation.setTitle("INFORMATION");
		alertInformation.setContentText(msg);
		alertInformation.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
