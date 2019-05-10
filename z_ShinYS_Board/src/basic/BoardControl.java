package basic;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import basic.lib.ShinYS;
import ibatis.service.BoardServiceImpl;
import ibatis.vo.BoardVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;

public class BoardControl implements Initializable {

	Class<? extends BoardControl> B_class = getClass();
	

	@FXML TableView<BoardVO> table_list;
	@FXML TableColumn<BoardVO, String> b_no;
	@FXML TableColumn<BoardVO, String> b_title;
	@FXML TableColumn<BoardVO, String> b_writer;
	@FXML TableColumn<BoardVO, String> b_date;
	@FXML TableColumn<BoardVO, String> b_view;
	@FXML TextField search_fd;
	@FXML Button search_btn;
	@FXML Button write_btn;
	@FXML ComboBox<Scombo> select_combo;
	@FXML Pagination pages;
	
	//오브저블리스트 변수생성
	ObservableList<BoardVO> data;
	BoardServiceImpl db = BoardServiceImpl.getInstance();
	
	int from, to, itemsForPage;
	ObservableList<BoardVO> currentPageData;


		
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		//테이블 뷰 컬럼연결
		b_no.setCellValueFactory(new PropertyValueFactory<>("board_no"));
		b_title.setCellValueFactory(new PropertyValueFactory<>("board_title"));
		b_writer.setCellValueFactory(new PropertyValueFactory<>("board_writer"));
		b_date.setCellValueFactory(new PropertyValueFactory<>("board_date"));
		b_view.setCellValueFactory(new PropertyValueFactory<>("board_view"));

		//테이블 뷰 데이터베이스 연동
		List<BoardVO> list = db.getAllBoardList();
		data = FXCollections.observableArrayList(list);
		
		//table_list.setItems(data);

		
		//페이징 셋팅
		itemsForPage = 14; // 한페이지 보여줄 항목 수 설정
		//나머지가 0이면 몫의 값을 페이지로 사용 ,, 0이 아니면 몫값 + 1 페이지로 사용
		int totPageCount = data.size()%itemsForPage == 0 ? data.size()/itemsForPage : data.size()/itemsForPage + 1;
		pages.setPageCount(totPageCount); // 전체 페이지 수 설정
		
		
		//방법3(메서드 참조) 
				// =>하나의 메서드만 호출하는 람다식은
				//   '클래스이름::메서드이름' 또는 '참조변수::메서드이름' 으로 바꿀 수 있다.
		pages.setPageFactory(this::createPage); // 메서드 참조 
		
		
				
				

		
		//콤보박스 아이템 초기화
		ObservableList<Scombo> list_combo = FXCollections.observableArrayList(
				new Scombo("제목", 1),
				new Scombo("내용", 2),
				new Scombo("제목+내용", 3),
				new Scombo("작성자", 4)
				);
		
			select_combo.setItems(list_combo);
			select_combo.setValue(list_combo.get(0));
		
		
			
		//검색버튼 이벤트
		search_btn.setOnMouseClicked(e->{
			search_method();
		});
		
		//검색어 작성 후 엔터 이벤트
		search_fd.setOnKeyPressed(e->{
			if(e.getCode().equals(KeyCode.ENTER)) {
				search_method();
			}
		});
		
		// 테이블리스트 더블클릭 이벤트
		table_list.setOnMouseClicked(e->{
			if(e.getClickCount() == 2) {
				
				try{
					BoardVO mem = table_list.getSelectionModel().getSelectedItem();

					BoardMain.vo_tmp.setBoard_no(mem.getBoard_no());
					BoardMain.vo_tmp.setBoard_view(mem.getBoard_view());
					db.viewUp(BoardMain.vo_tmp);
					ShinYS.ChangeView(getClass(),"Detail.fxml",false);
				}catch(Exception e2) {
					
				}
			}
		});
		
		//글쓰기 버튼
		write_btn.setOnMouseClicked(e->{
			ShinYS.ChangeView(getClass(),"Write.fxml",false);
		});
		
	
	}//initialize 메서드 끝
	
	
	
	
	//검색기능 메서드
	public void search_method() {
		
		String s1 = search_fd.getText();
		BoardVO vo2 = new BoardVO();
		int s2 = select_combo.getSelectionModel().getSelectedItem().getValue();
		
		switch(s2) {
		case 1:
			vo2.setBoard_title(s1);
			break;
		case 2:
			vo2.setBoard_content(s1);
			break;
		case 3:
			vo2.setBoard_title(s1);
			vo2.setBoard_content(s1);
			break;
		case 4:
			vo2.setBoard_writer(s1);
			break;
		}
		
	    
		
		List<BoardVO> list_search = db.getSearchBoard(vo2);

		data.clear();
		
		
		
		//data.addAll(list_search);


		
		
		//itemsForPage = 5; // 한페이지 보여줄 항목 수 설정
		//나머지가 0이면 몫의 값을 페이지로 사용 ,, 0이 아니면 몫값 + 1 페이지로 사용
		int totPageCount = list_search.size()%itemsForPage == 0 ? list_search.size()/itemsForPage : list_search.size()/itemsForPage + 1;
		
		if(list_search.size()<1) {
			totPageCount = 1;
		}
		
		pages.setPageCount(totPageCount); // 전체 페이지 수 설정
		
		
		//방법2(람다식)
		pages.setPageFactory((Integer pageIndex)->{
			
			from = pageIndex * itemsForPage;
			to = from + itemsForPage - 1;
			table_list.setItems(getTableViewData2(from, to, list_search));
		
			return table_list;
		}); // 페이징처리를 위한 팩토리 메서드 설정
		
	}
	
	
	
	
	
	/**
	 * 페이징 처리를 위한 팩토리 메서드(메서드 참조용)
	 * @param pageIndex
	 * @return
	 */
	private Node createPage(int pageIndex){
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;
		
		table_list.setItems(getTableViewData(from, to));	
		
		return table_list;
	}
	
	/**
	 * TableView에 채워줄 데이터를 가져오는 함수
	 * @param from
	 * @param to
	 * @return
	 */
	private ObservableList<BoardVO> getTableViewData(int from, int to){
		
		currentPageData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = data.size();
		for(int i = from; i <= to && i <totSize; i++){
		
			currentPageData.add(data.get(i));
		}
		
		return currentPageData;
	}
	
	
	private ObservableList<BoardVO> getTableViewData2(int from, int to, List<BoardVO> listvo){
		
		currentPageData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = listvo.size();
		for(int i = from; i <= to && i <totSize; i++){
		
			currentPageData.add(listvo.get(i));
		}
		
		return currentPageData;
	}
	
	
	
	
	//검색 콤보박스 vo형식으로 만들어서 키값과 벨류값 연습
	public class Scombo{
		private String vis;
		private int value;
		public String toString() {
			return vis;
		}
		
		public Scombo(String vis, int value) {
			super();
			this.vis = vis;
			this.value = value;
		}
		public String getVis() {
			return vis;
		}
		public void setVis(String vis) {
			this.vis = vis;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		
	}
	
}
