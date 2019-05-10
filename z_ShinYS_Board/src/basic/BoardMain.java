package basic;

import ibatis.vo.BoardVO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BoardMain extends Application{
	public static Stage StageHome;
	public static BoardVO vo_tmp = new BoardVO();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		StageHome = primaryStage;

		FXMLLoader loader =
				new FXMLLoader(getClass().getResource("Board.fxml"));
			Parent root = loader.load();
			
			
			Scene scene = new Scene(root);
			StageHome.setTitle("게시판관리");
			StageHome.setScene(scene);
			StageHome.show();
			
			root.requestFocus();

			//컨트롤러 접근 테스트
			//BoardControl cont = loader.getController(); //controller 접근
			
			//lookup 테스트
			//root.lookup("TableView").requestFocus();	//fxml접근
			//root.lookup("#search_fd").requestFocus();	//fxml접근
			//StageHome.getScene().getRoot().lookup("#search_fd").requestFocus();
			//BoardMain.StageHome.getScene().getRoot().lookup("VBox").requestFocus();
			
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


}
