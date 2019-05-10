package basic;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.SwingUtilities;
//내주석
//import com.sun.webkit.WebPage;

import basic.lib.ShinYS;
import ibatis.service.BoardServiceImpl;
import ibatis.vo.BoardVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;




public class BoardDetail implements Initializable {

		//오브저블리스트 변수생성
		ObservableList<BoardVO> data;
		BoardServiceImpl db = BoardServiceImpl.getInstance();
		
		@FXML Label title_lb;
		@FXML Label date_lb;
		@FXML Label writer_lb;
		@FXML Label view_lb;
		@FXML Label no_lb;
		@FXML Button cancel_btn;
		@FXML Button del_btn;
		@FXML Button edit_btn;
		@FXML WebView contents2;



		

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		List<BoardVO> g = db.getSearchBoard(BoardMain.vo_tmp);

		title_lb.setText(g.get(0).getBoard_title());
		date_lb.setText(g.get(0).getBoard_date());
		writer_lb.setText(g.get(0).getBoard_writer());
		no_lb.setText(g.get(0).getBoard_no());
		view_lb.setText(g.get(0).getBoard_view());

		
		WebEngine webEngine = contents2.getEngine();
		webEngine.loadContent(g.get(0).getBoard_content());

		try {
			Field field = webEngine.getClass().getDeclaredField("page");
			field.setAccessible(true);
			//내주석
			//com.sun.webkit.WebPage page = (WebPage) field.get(webEngine);
			SwingUtilities.invokeLater(() -> {
			//내주석
			//page.setBackgroundColor(new java.awt.Color(0, 0, 0, 0).getRGB());
			});
			

			} catch ( Exception e ) {
			e.printStackTrace();
			}
	
		
		cancel_btn.setOnMouseClicked(e->{
			ShinYS.ChangeView(getClass(),"Board.fxml", true);
		});
		
		
		del_btn.setOnMouseClicked(e->{
			db.deleteBoard(g.get(0));
			ShinYS.ChangeView(getClass(),"Board.fxml", true);
		});
		
		
		edit_btn.setOnMouseClicked(e->{
			ShinYS.ChangeView(getClass(),"Modify.fxml", true);
		});

	}//initialize 메서드 끝
}
