package model;

import java.util.Date;

public class User {
  private int userid;
  private String username;
  private String password;
  private Date birthday;
  private String other;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Date getBirthday() {
	return birthday;
}
public void setBirthday(Date birthday) {
	this.birthday = birthday;
}
public String getOther() {
	return other;
}
public void setOther(String other) {
	this.other = other;
}
}
