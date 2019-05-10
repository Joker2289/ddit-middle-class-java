package basic;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class e_ComboBoxTest extends Application{
	
   @Override
   public void start(Stage primaryStage) throws Exception {
      BorderPane root= new BorderPane();
      HBox hbox = new HBox();
      TextArea txtArea = new TextArea();
      
      ComboBox<String> combo = new ComboBox<>();
      combo.getItems().addAll("한강","금강","영산강","낙동강"); //getItems() = get()
      combo.setValue("한강"); //처음에 보이는 부분의 데이터
      
      // ComboBox의 값이 변경될 때의 처리 즉, change이벤트 처리
      //valueProperty() : 
      //addListener() : Listener(이벤트발생을 지켜보는 메서드)를 추가한다
      //ChangeListenner<>() : changed라는 메서드를 가진 함수적 인터페이스
      // ? (wild card) extends String : String 하위객체들만 파라미터 값으로 올수 있다
      
      //방법1
      combo.valueProperty().addListener(new ChangeListener<String>() {
         @Override
         public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            txtArea.setText(newValue);//call back function 역할을 함
         }
      });
      
      //방법2
      ObservableList<String> fruitList = FXCollections.observableArrayList("사과","배","복숭아","포도","감");
      
      // 객체생성 및 데이터 초기화를 동시에 ...
      ComboBox<String> combo2 = new ComboBox<>(fruitList);
      
      // 데이터를 초기화 한 후에 추가하기
      combo2.getItems().addAll("대추","호두");
      combo2.setValue("포도");
      
      Button button = new Button("확인");
      //setOnAction : 버튼에 이벤트를 주는 메서드 
      //EventHandler : 함수적 인터페이스
      button.setOnAction(new EventHandler<ActionEvent>() {
         
         @Override
         public void handle(ActionEvent event) {
            if(combo.getValue()!=null&&combo2.getValue()!=null) {
               txtArea.setText(combo.getValue()+" 지역에는"+
                        combo2.getValue()+"가 유명합니다.");
            }
         }
      });
      
      hbox.setSpacing(10);	//컨트롤 사이의 간격
      hbox.setPadding(new Insets(10));
      hbox.getChildren().addAll(combo,combo2,button);
      
      root.setTop(hbox);
      root.setCenter(txtArea);
      
      Scene scene = new Scene(root,500,400); //500, 400 은 Scene의 사이즈
      
      primaryStage.setTitle("ComboBox 연습");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}