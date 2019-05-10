package basic;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import basic.lib.ShinYS;
import ibatis.service.BoardServiceImpl;
import ibatis.vo.BoardVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;

public class BoardModify implements Initializable {

		@FXML TextField title_txt;

		@FXML Button cancel_btn;
		@FXML Button sub_btn;
		@FXML HTMLEditor content_txt;
		@FXML TextField writer_txt;
		
		
		//오브저블리스트 변수생성
		ObservableList<BoardVO> data;
		BoardServiceImpl db = BoardServiceImpl.getInstance();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
		List<BoardVO> g = db.getSearchBoard(BoardMain.vo_tmp);
	
		title_txt.setText(g.get(0).getBoard_title());
		content_txt.setHtmlText(g.get(0).getBoard_content());
		writer_txt.setText(g.get(0).getBoard_writer());
		
		cancel_btn.setOnMouseClicked(e->{
			ShinYS.ChangeView(getClass(),"Detail.fxml", true);
		});
		
		sub_btn.setOnMouseClicked(e->{
			
//			Date today = new Date();
//		    System.out.println(today);
//		        
//		    SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
//		    SimpleDateFormat time = new SimpleDateFormat("hh/mm/ss");
//		        
//		    System.out.println("Date: "+date.format(today));
//		    System.out.println("Time: "+time.format(today));
//
//		    String nowDates = date.format(today);
//		    String nowTimes = time.format(today);

			BoardVO vo = new BoardVO();
			vo.setBoard_no(g.get(0).getBoard_no());
			vo.setBoard_title(title_txt.getText());
			vo.setBoard_content(content_txt.getHtmlText());
			//vo.setBoard_date(nowDates + " " +nowTimes);
			vo.setBoard_writer(writer_txt.getText());

			
			db.updateBoard(vo);
			
			ShinYS.ChangeView(getClass(),"Detail.fxml", true);
		});

	}//initialize 메서드 끝
}
