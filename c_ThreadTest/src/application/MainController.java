package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainController implements Initializable{

	@FXML Pane pane_track;
	@FXML ImageView imageView_horse1;
	@FXML ImageView imageView_horse2;
	@FXML ImageView imageView_horse3;
	@FXML ImageView imageView_horse4;
	@FXML ImageView imageView_horse5;
	@FXML ImageView imageView_horse6;
	@FXML ImageView imageView_horse7;
	@FXML ImageView imageView_horse8;
	@FXML ImageView imageView_horse9;
	@FXML ImageView imageView_horse10;
	
	@FXML Button btnStartGame;
	
	private List<Horse> horses; // 경마 목록
	
	private List<String> finishLine; // 결승라인 통과 순위 저장
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void onclickStartRacing(ActionEvent event){
		
		btnStartGame.setDisable(true);
		btnStartGame.setText("경마 진행중...");
		
		horses = new ArrayList<Horse>(); // 초기화
		finishLine = new ArrayList<String>(); // 초기화
		
		horses.add(new Horse(imageView_horse1,"정권",(int)pane_track.getWidth(),this.finishLine,this));
		horses.add(new Horse(imageView_horse2,"유수",(int)pane_track.getWidth(),this.finishLine,this));
		horses.add(new Horse(imageView_horse3,"한수",(int)pane_track.getWidth(),this.finishLine,this));
		horses.add(new Horse(imageView_horse4,"상호",(int)pane_track.getWidth(),this.finishLine,this));
		horses.add(new Horse(imageView_horse5,"재우",(int)pane_track.getWidth(),this.finishLine,this));
		horses.add(new Horse(imageView_horse6,"준호",(int)pane_track.getWidth(),this.finishLine,this));
		horses.add(new Horse(imageView_horse7,"주은",(int)pane_track.getWidth(),this.finishLine,this));
		horses.add(new Horse(imageView_horse8,"현욱",(int)pane_track.getWidth(),this.finishLine,this));
		horses.add(new Horse(imageView_horse9,"도현",(int)pane_track.getWidth(),this.finishLine,this));
		horses.add(new Horse(imageView_horse10,"승섭",(int)pane_track.getWidth(),this.finishLine,this));
		
		for(Horse horse:horses){
			horse.start();
		}
	}
	
	public void showResult(){
		String rank = "";
		for(int i=0;i<finishLine.size();i++){
			rank += (i+1)+"위는 "+finishLine.get(i)+"\n";
		}
		
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER); // 중앙 정렬
		hBox.setPrefWidth(250);
		hBox.setPrefHeight(300);
		
		Label label = new Label(rank);
		label.setFont(new Font(20));
		hBox.getChildren().add(label);
		
		Scene scene = new Scene(hBox);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("경마 순위");
		stage.show();
	}
}
