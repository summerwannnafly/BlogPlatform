package domain;

/**
 * 用户类Bean
 * 
 * @author 胡炜财
 *
 */
public class User {
	private String userID;
	private String username;
	private String email;
	private String password;
	private String sex;
	private String birth_year;
	private String birth_month;
	private String birth_day;
	private String self_introduce;
	private boolean isAdmin;
	private String color;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	public String getBirth_month() {
		return birth_month;
	}
	public void setBirth_month(String birth_month) {
		this.birth_month = birth_month;
	}
	public String getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}
	public String getSelf_introduce() {
		return self_introduce;
	}
	public void setSelf_introduce(String self_introduce) {
		this.self_introduce = self_introduce;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String toString() {
		return "userID: "+userID+" usernaem: "+username+" sex: "+sex+" self_introduce: "+self_introduce;
	}
}
