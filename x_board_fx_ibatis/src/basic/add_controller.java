package basic;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ibatis.board.service.BoardServiceImpl;
import ibatis.board.vo.BoardVO;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class add_controller implements Initializable {

	@FXML TextField txt_add_title;
	@FXML TextField txt_add_writer;
	@FXML TextArea txt_add_content;
	
	@FXML Button btn_add_ok;
	@FXML Button btn_add_cancel;
	
	// Service객체 변수를 선언한다.
	private BoardServiceImpl db = BoardServiceImpl.getInstance();
	
//	public add_controller() {
//		db = new BoardServiceImpl();
//	}
//	
//	private static board_controller b;
//	public static board_controller getInstance() {
//		if(b == null) {
//			b = new board_controller(); 
//		}
//		return b;
//	}
	
	//기본 리스트 생성 / 데이터 삽입
	ObservableList<BoardVO> list;
	
	board_controller bc;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		btn_add_ok.setOnAction(e->{
			
			//자동증가 초기화
			db.auto_increment();
			
			//DB추가
			BoardVO vo = new BoardVO();
			vo.setBoard_writer(txt_add_writer.getText());
			vo.setBoard_title(txt_add_title.getText());
			vo.setBoard_content(txt_add_content.getText());

			db.insertBoard(vo);
			
			
			//테이블 추가
			
//			List<BoardVO> add_list = boardService.getAllBoardList();
//			list = FXCollections.observableArrayList(add_list);
//			bc.tb_board.setItems(list);
			
		});
	}

}
