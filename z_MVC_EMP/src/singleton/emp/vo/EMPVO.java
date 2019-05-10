package singleton.emp.vo;
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
public class EMPVO {
	private String sawon_id;
	private String sawon_name;
	private String mobile;
	private String email;
	private String ins_date;
	private String ins_id;
	private String upd_date;
	private String upd_id;
	
	public String getSawon_id() {
		return sawon_id;
	}
	public void setSawon_id(String sawon_id) {
		this.sawon_id = sawon_id;
	}
	public String getSawon_name() {
		return sawon_name;
	}
	public void setSawon_name(String sawon_name) {
		this.sawon_name = sawon_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIns_date() {
		return ins_date;
	}
	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}
	public String getIns_id() {
		return ins_id;
	}
	public void setIns_id(String ins_id) {
		this.ins_id = ins_id;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public String getUpd_id() {
		return upd_id;
	}
	public void setUpd_id(String upd_id) {
		this.upd_id = upd_id;
	}
	
	@Override
	public String toString() {
		return "sawon_id = " + sawon_id + "		sawon_name = " + sawon_name + "		mobile = " + mobile + "		email = " + email
				+ "		ins_date = " + ins_date + "		ins_id = " + ins_id + "		upd_date = " + upd_date + "		upd_id = " + upd_id;
	}
}
