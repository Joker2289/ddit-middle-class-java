package basic;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class BoardWrite implements Initializable {

		@FXML TextField title_txt;
		@FXML Button cancel_btn;
		@FXML Button sub_btn;
		@FXML HTMLEditor content_txt;
		
		//오브저블리스트 변수생성
		ObservableList<BoardVO> data;
		BoardServiceImpl db = BoardServiceImpl.getInstance();
		@FXML TextField writer_txt;

		

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		cancel_btn.setOnMouseClicked(e->{
			ShinYS.ChangeView(getClass(),"Board.fxml", true);
		});
		
		sub_btn.setOnMouseClicked(e->{
			//내 주석
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
			//vo.setBoard_no("10"); //시퀀스 or 오토인크리먼트로 자동입력
			vo.setBoard_title(title_txt.getText());
			vo.setBoard_content(content_txt.getHtmlText());
			//내 주석
//			vo.setBoard_date(nowDates + " " +nowTimes);
			vo.setBoard_writer(writer_txt.getText());
			
			db.insertBoard(vo);
			
			ShinYS.ChangeView(getClass(),"Board.fxml", true);
		});

	}//initialize 메서드 끝
}
