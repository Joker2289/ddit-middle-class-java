package basic.lib;

import java.io.IOException;
import java.util.ResourceBundle;

import basic.BoardMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ShinYS {
	
	public static void ChangeView(Class getC, String fxml, boolean blur) {
		
		Parent window1 = null;
	    try {
	        window1 = FXMLLoader.load(getC.getResource(fxml));
	        Stage newStage;
	        Scene newScene = new Scene(window1);
	        if(blur) {
	        	window1.requestFocus();
	        }
	        
	        newStage = BoardMain.StageHome;
	        newStage.setScene(newScene);
	        
	       //newStage.show();
	       //BoardMain.StageHome.getScene().getRoot().lookup("VBox").requestFocus();
	        
	    } catch (IOException e2) {
	        e2.printStackTrace();
	    }
	}


}
