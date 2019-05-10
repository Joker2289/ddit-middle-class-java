package basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class board_main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader load = new FXMLLoader(getClass().getResource("board.fxml"));
		Parent root = load.load();
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("ibatis_member");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}


}
