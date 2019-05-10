package basic;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ibatis.board.service.BoardServiceImpl;
import ibatis.board.vo.BoardVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

public class board_controller implements Initializable{

	@FXML TableView<BoardVO> tb_board;
	
	@FXML TableColumn<BoardVO, String> col_no;
	@FXML TableColumn<BoardVO, String> col_writer;
	@FXML TableColumn<BoardVO, String> col_title;
	@FXML TableColumn<BoardVO, String> col_date;
	@FXML TableColumn<BoardVO, String> col_view;
	
	@FXML Button btn_add;
	@FXML Button btn_serch;
	
	// Service객체 변수를 선언한다.
	private BoardServiceImpl db = BoardServiceImpl.getInstance();
	
//	public board_controller() {
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Table에 데이터 세팅
		List<BoardVO> board_list = db.getAllBoardList();
		list = FXCollections.observableArrayList(board_list);
		tb_board.setItems(list);
		
		//해당 Column에 데이터 입력하기
		col_no.setCellValueFactory(new PropertyValueFactory<>("board_no"));
		col_title.setCellValueFactory(new PropertyValueFactory<>("board_title"));
		col_writer.setCellValueFactory(new PropertyValueFactory<>("board_writer")); 
		col_date.setCellValueFactory(new PropertyValueFactory<>("board_date"));
		col_view.setCellValueFactory(new PropertyValueFactory<>("board_view"));	
		
		//글작성
		btn_add.setOnAction(e->{
			//새창 띄우기
			Stage dialog = new Stage(StageStyle.UTILITY);
			
			dialog.initModality(Modality.APPLICATION_MODAL);
			//dialog.initOwner();
			dialog.setTitle("글 작성");
			
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("add.fxml"));
			} catch(IOException ee) {
				ee.printStackTrace();
			}
			
			//텍스트창
//			TextArea txt_add_content = (TextArea) parent.lookup("#txt_add_content");
//			TextField txt_add_title = (TextField) parent.lookup("#txt_add_title");
//			TextField txt_add_writer = (TextField) parent.lookup("#txt_add_writer");
			
			//확인 버튼
//			Button btn_add_ok = (Button) parent.lookup("#btn_add_ok");
//			btn_add_ok.setOnAction(event->{
//				
//				//자동증가 초기화
//				boardService.auto_increment();
//				
//				
//				//DB추가
//				BoardVO vo = new BoardVO();
//				vo.setBoard_writer(txt_add_writer.getText());
//				vo.setBoard_title(txt_add_title.getText());
//				vo.setBoard_content(txt_add_content.getText());
//				
//				boardService.insertBoard(vo);
//				
				//테이블에 추가
//				List<BoardVO> add_list = boardService.getAllBoardList();
//				list = FXCollections.observableArrayList(add_list);
//				tb_board.setItems(list);
//				
//				dialog.close();
//			});
//			
//			
			//취소 버튼
//			Button btn_add_cancel = (Button) parent.lookup("#btn_add_cancel");
//			btn_add_cancel.setOnAction(event->{
//				dialog.close();
//			});

			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.setResizable(false);//크기고정
			dialog.show();
		});
		
		
		tb_board.setOnMouseClicked(e->{
			
			//테이블뷰 두번 클릭
			if(e.getClickCount()==2) {
				
				//TableView에서 선택한 줄의 데이터를 얻는다
				BoardVO vo = tb_board.getSelectionModel().getSelectedItem();
				
				
				
				
				//조회수 증가
				db.up_view(vo);
				

				int board_index = tb_board.getSelectionModel().getSelectedIndex();
				List<BoardVO> vo2 = db.getSelectBoard(vo);
				list.set(board_index, vo2.get(0));
			
				
				
				//새창 띄우기
				Stage dialog = new Stage(StageStyle.UTILITY);
				
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.setTitle("글 작성");
				
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("info.fxml"));
				} catch(IOException ee) {
					ee.printStackTrace();
				}
				
				//텍스트창
				TextArea txt_info_content = (TextArea) parent.lookup("#txt_info_content");
				TextField txt_info_title = (TextField) parent.lookup("#txt_info_title");
				TextField txt_info_writer = (TextField) parent.lookup("#txt_info_writer");
				
				Button btn_info_update = (Button) parent.lookup("#btn_info_update");
				Button btn_info_delete = (Button) parent.lookup("#btn_info_delete");
				Button btn_info_ok = (Button) parent.lookup("#btn_info_ok");
				Button btn_info_cancel = (Button) parent.lookup("#btn_info_cancel");
				
				btn_info_ok.setDisable(true);
				
				
				
				//txt창에 데이터 삽입
				txt_info_writer.setText(vo.getBoard_writer());
				txt_info_title.setText(vo.getBoard_title());
				txt_info_content.setText(vo.getBoard_content());
				
				//텍스트 창 수정 불가
				txt_info_content.setEditable(false);
				txt_info_title.setEditable(false);;
				txt_info_writer.setEditable(false);
				
				//수정 버튼 이벤트
				btn_info_update.setOnAction(event->{
					//텍스트 창 수정가능
					txt_info_content.setEditable(true);
					txt_info_title.setEditable(true);
					txt_info_writer.setEditable(true);
					btn_info_ok.setDisable(false);
					
					txt_info_title.requestFocus();
				});
				
				//삭제 버튼 이벤트
				btn_info_delete.setOnAction(event->{
					
					//테이블뷰 삭제
					//System.out.println(tb_board.getSelectionModel().getSelectedIndex());
					list.remove(board_index);
					//DB삭제
					db.deleteBoard(vo.getBoard_no());
					dialog.close();
					
					infoMsg("Info", vo.getBoard_no() + "번 글 삭제완료");
				});
				
				
				//확인 버튼 이벤트
				btn_info_ok.setOnAction(event->{
					vo.setBoard_title(txt_info_title.getText());
					vo.setBoard_writer(txt_info_writer.getText());
					vo.setBoard_content(txt_info_content.getText());
					vo.setBoard_no(vo.getBoard_no());
					db.updateBoard(vo);
					
					//테이블에 추가
					List<BoardVO> update_list = db.getAllBoardList();
					list = FXCollections.observableArrayList(update_list);
					tb_board.setItems(list);
					
					dialog.close();
					
					infoMsg("Info", vo.getBoard_no() + "번 글 수정완료");
					
				});
				
				//취소 버튼 이벤트
				btn_info_cancel.setOnAction(event->{
					dialog.close();
				});
				
				Scene scene = new Scene(parent);
				dialog.setScene(scene);
				dialog.setResizable(false);//크기고정
				dialog.show();
			}
		});
	}
	
	//에러메시지 메서드
	public void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("Error");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();
	}
		
		
	//알림창 메서드
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("info");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}

}
