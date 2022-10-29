package com.virtusa.bstore.dto;

public class User {
	int userId;
	String userEmail;
	String passWord;
	String location;
	int age;
	String userMobileNo;
	String dob;
	public User(int userId, String userEmail, String passWord, String location, int age, String userMobileNo,
			String dob) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.passWord = passWord;
		this.location = location;
		this.age = age;
		this.userMobileNo = userMobileNo;
		this.dob = dob;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getUserMobileNo() {
		return userMobileNo;
	}
	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userEmail=" + userEmail + ", passWord=" + passWord + ", location="
				+ location + ", age=" + age + ", userMobileNo=" + userMobileNo + ", dob=" + dob + "]";
	}
	

}
