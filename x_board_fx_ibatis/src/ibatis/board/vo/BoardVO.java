package ibatis.board.vo;
/**
 *			DB테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스이다
 *
 * @author pjk
 *
 *
 *<p>
 * 			DB 테이블의 '컬럼'이 이 클래스의 '멤버변수'가 된다<br>
 * 			DB 테이블의 컬럼과 클래스의 멤버변수를 맵핑하는 역할을 수행한다<br>
 * </p>
 */
public class BoardVO {
	private String board_no;
	private String board_title;
	private String board_writer;
	private String board_date;
	private	String board_content;
	private String board_view;
	
	public String getBoard_view() {
		return board_view;
	}
	public void setBoard_view(String board_view) {
		this.board_view = board_view;
	}
	
	public String getBoard_no() {
		return board_no;
	}
	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
}
