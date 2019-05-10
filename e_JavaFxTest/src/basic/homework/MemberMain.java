package basic.homework;

import java.awt.Container;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MemberMain extends Application{

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader =
				new FXMLLoader(getClass().getResource("Member.fxml"));
			Parent root = loader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setTitle("회원관리");
			primaryStage.setScene(scene);
			primaryStage.show();
		
			//root.lookup("VBox").requestFocus();
			
//			MemberControl ll = loader.getController();
//			ll.mem_table.requestFocus();
			
			root.requestFocus();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


}
