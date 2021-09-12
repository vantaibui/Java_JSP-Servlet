package com.jsp_servlet.model;

public class UserModel extends AbstractModel { // Gọi là java bean (Java bean và java class khác nhau là java bean có
												// getter và
	// setter)
	private String userName;
	private String fullName;
	private String password;
	private int stattus;
	private Long roleId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStattus() {
		return stattus;
	}

	public void setStattus(int stattus) {
		this.stattus = stattus;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
