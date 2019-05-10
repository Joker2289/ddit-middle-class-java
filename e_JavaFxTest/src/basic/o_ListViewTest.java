package basic;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class o_ListViewTest extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ListView<String> list = new ListView();
		
		Label label = new Label();
		label.setFont(new Font(20));
		
		//ListView에 들어갈 데이터 구성하기
		//FX에서는Observable List를 주로 쓴다
		ObservableList<String> data = FXCollections.observableArrayList(
				"green", "gold", "red", "blue", "black", "brown",
				"blueviolet", "pink", "yellow", "chocolate");
		VBox vbox = new VBox(10);
		list.setItems(data); 	//ListView에 데이터 세팅
		
		//ListView에서 값이 선택되었을때 처리
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				label.setText(newValue);//현재 선택값 > newValue
				label.setTextFill(Color.web(newValue));	//글자색 변경
				System.out.println(oldValue + " > " + newValue); //console창에 출력
				
			}
		});
		
		//ListView의 내용은변경되지 않고 화면에 보이는 부분만 변경하는 방법
		list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						Rectangle rect = new Rectangle(100, 20);
						if(item != null) { //또는 !empty
							rect.setFill(Color.web(item)); //4각형 내부 색칠하기
							
							Label lbTxt = new Label(item);
							lbTxt.setTextFill(Color.web(item));
							
							HBox hb2 = new HBox(10);
							hb2.getChildren().addAll(rect, lbTxt);
							
							setGraphic(hb2);
						}
					}
				};
			}
		});
		
		vbox.getChildren().addAll(list, label);
		
		Scene scene = new Scene(vbox);
		
		primaryStage.setTitle("ListView 연습");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
