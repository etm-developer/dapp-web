package com.entanmo.dapp.po;

import java.io.Serializable;


/**
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
public class Member implements Serializable {


	/**
	 * 
	 */
	private Integer memberId;

	/**
	 * 
	 */
	private String account;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private String phone;

	/**
	 * 
	 */
	private String email;

	/**
	 * 
	 */
	private Integer userType;
	
	private Integer state;

	/**
	 * 
	 */
	private java.util.Date createTime;

	/**
	 * 
	 */
	private java.util.Date lastLogin;


	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}

	public Integer getMemberId(){
		return this.memberId;
	}

	public void setAccount(String account){
		this.account = account;
	}

	public String getAccount(){
		return this.account;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setUserType(Integer userType){
		this.userType = userType;
	}

	public Integer getUserType(){
		return this.userType;
	}

	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	public void setLastLogin(java.util.Date lastLogin){
		this.lastLogin = lastLogin;
	}

	public java.util.Date getLastLogin(){
		return this.lastLogin;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
