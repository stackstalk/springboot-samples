package com.stackstalk.democache;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetails implements Serializable {
	private static final long serialVersionUID = -4439114469417994311L;
	
	@Id
	private int userId;
	private String userName;
	
	public UserDetails() {
		super();
	}	
	public UserDetails(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}	
}
