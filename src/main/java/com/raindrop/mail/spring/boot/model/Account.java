package com.raindrop.mail.spring.boot.model;

/**
 * @name: com.raindrop.mail.spring.boot.model.Account.java
 * @description: 账号
 * @author: Wang Liang
 * @create Time: 2018/6/28 19:37
 */
public class Account {

	private String email;
	private String password;
	private String isActive;

	public Account(String email, String password, String isActive) {
		this.email = email;
		this.password = password;
		this.isActive = isActive;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Account{" +
				"email='" + email + '\'' +
				", password='" + password + '\'' +
				", isActive='" + isActive + '\'' +
				'}';
	}
}
