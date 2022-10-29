package com.virtusa.bstore.dto;

public class AdminLogin {
	String adminName;
	String adminEmail;
	String adminPassword;
	public AdminLogin(String adminName, String adminEmail, String adminPassword) {
		super();
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmaail(String adminEmaail) {
		this.adminEmail = adminEmaail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "AdminLogin [adminName=" + adminName + ", adminEmail=" + adminEmail + ", adminPassword="
				+ adminPassword + "]";
	}
	

}
