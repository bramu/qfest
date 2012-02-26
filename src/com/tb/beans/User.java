package com.tb.beans;

public class User extends Base {

	private String name;
	private String email;
	private String password;
	private String encryptedPassword;
	private String passwordSalt;
	private String resetPasswordToken;
	private String rememberToken;
	private String rememberCreatedAt;
	private int signInCount;
	private String currentSignInAt;
	private String lastSignInAt;
	private String currentSignInIp;
	private String lastSignInIp;
	private String admin;

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public String getRememberToken() {
		return rememberToken;
	}

	public void setRememberToken(String rememberToken) {
		this.rememberToken = rememberToken;
	}

	public String getRememberCreatedAt() {
		return rememberCreatedAt;
	}

	public void setRememberCreatedAt(String rememberCreatedAt) {
		this.rememberCreatedAt = rememberCreatedAt;
	}

	public int getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(int signInCount) {
		this.signInCount = signInCount;
	}

	public String getCurrentSignInAt() {
		return currentSignInAt;
	}

	public void setCurrentSignInAt(String currentSignInAt) {
		this.currentSignInAt = currentSignInAt;
	}

	public String getLastSignInAt() {
		return lastSignInAt;
	}

	public void setLastSignInAt(String lastSignInAt) {
		this.lastSignInAt = lastSignInAt;
	}

	public String getCurrentSignInIp() {
		return currentSignInIp;
	}

	public void setCurrentSignInIp(String currentSignInIp) {
		this.currentSignInIp = currentSignInIp;
	}

	public String getLastSignInIp() {
		return lastSignInIp;
	}

	public void setLastSignInIp(String lastSignInIp) {
		this.lastSignInIp = lastSignInIp;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

}
