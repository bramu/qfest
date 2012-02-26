package com.tb.beans;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private String confirm_pw;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getConfirm_pw() {
		return confirm_pw;
	}
	public void setConfirm_pw(String confirm_pw) {
		this.confirm_pw = confirm_pw;
	}
}
